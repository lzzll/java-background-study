package com.example.lzzll.javastudy.common.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author zfan
 */
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ValueOperations<String, String> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    private static final DefaultRedisScript<Long> LOCK_SCRIPT;
    private static final DefaultRedisScript<Object> UNLOCK_SCRIPT;
    private static final DefaultRedisScript<Long> REFRESHLOCK_SCRIPT;
    static {
        // 加载释放锁的脚本
        LOCK_SCRIPT = new DefaultRedisScript<>();
        LOCK_SCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("config/lock.lua")));
        LOCK_SCRIPT.setResultType(Long.class);

        // 加载释放锁的脚本
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("config/unlock.lua")));

        // 刷新锁过期时间的脚本
        REFRESHLOCK_SCRIPT = new DefaultRedisScript<>();
        REFRESHLOCK_SCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("config/refreshLock.lua")));
        REFRESHLOCK_SCRIPT.setResultType(Long.class);
    }

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    private final static Gson gson = new Gson();

    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Long deleteKeys(Set<String> keys) {
        return redisTemplate.delete(keys);
    }

    public void deletePrex(String prex) {
        Set<String> keys = redisTemplate.keys(prex);
        if (keys.size() != 0) {
            redisTemplate.delete(keys);
        }
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * Object转成JSON数据
     */
    public String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return gson.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }


    /**
     * 删除premission前缀的keys
     */
    public void deletePermissionsPrefixKeys(){
        //当前所有拥有tiku-premission前缀的keys
        String key = "tiku-permissions-";
        if (hasKey(key)) {
            String permissionsPrefixKeys = get(key);
            Set<String> permissionsPrefixKeySet = new HashSet<>();
            if (permissionsPrefixKeys.contains(",")) {
                String[] premissionsKey = permissionsPrefixKeys.split(",");
                for (String str : premissionsKey) {
                    permissionsPrefixKeySet.add(str);
                }
            } else {
                permissionsPrefixKeySet.add(permissionsPrefixKeys);
            }
            if (deleteKeys(permissionsPrefixKeySet).intValue() == permissionsPrefixKeySet.size()) {
                delete(key);
            }
        }
    }


    /**
     * 树形转集合
     * @param list
     * @return
     */
    public static List<Map> tree2list(List<Map> list) {
        List<Map> result = new ArrayList<>();
        for (Map map : list) {
            List<Map> children = (List<Map>) map.get("children");
            result.add(map);
            if (!CollectionUtils.isEmpty(children)) {
                result.addAll(tree2list(children));
                map.put("children",new ArrayList<>());
            }
        }
        return result;
    }



    /**
     * 获取锁(存储到redis的数据结构为hash)
     * @param lockName 锁名称
     * @param releaseTime 超时时间(单位:秒)
     * @return key 解锁标识
     */
    public String tryLock(String lockName,String releaseTime) {
        // 存入的线程信息的前缀，防止与其它JVM中线程信息冲突
        String key = UUID.randomUUID().toString();

        // 执行脚本
        Long result = redisTemplate.execute(
                LOCK_SCRIPT,
                Collections.singletonList(lockName),
                key + Thread.currentThread().getId(), releaseTime);

        // 判断结果
        if(result != null && result.intValue() == 1) {
            return key;
        }else {
            return null;
        }
    }
    /**
     * 释放锁
     * @param lockName 锁名称
     * @param key 解锁标识
     */
    public void unlock(String lockName,String key) {
        // 执行脚本
        redisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(lockName),
                key + Thread.currentThread().getId(), null);
    }

    /**
     * 获取锁(存储到redis的数据结构为hash)
     * @param lockName 锁名称
     * @param releaseTime 超时时间(单位:秒)
     * @return key 解锁标识
     */
    public String tryLockByRefresh(String lockName,String releaseTime) {
        // 存入的线程信息的前缀，防止与其它JVM中线程信息冲突
        String key = UUID.randomUUID().toString();
        long threadId = Thread.currentThread().getId();
        // 执行脚本
        Long result = redisTemplate.execute(
                LOCK_SCRIPT,
                Collections.singletonList(lockName),
                key + threadId, releaseTime);

        // 判断结果
        if(result != null && result.intValue() == 1) {
            //开启定时刷新过期时间
            final boolean[] isOpenExpirationRenewal = {true};
            ExecutorService executorService = Executors.newFixedThreadPool(1,new ThreadFactoryBuilder().setNameFormat("tiku-factory-redis-lock").build());
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        int sleepTime = 1;
                        while (isOpenExpirationRenewal[0]) {
                            TimeUnit.SECONDS.sleep(sleepTime);
                            if (sleepTime != 5) {
                                sleepTime++;
                            }
                            Long execute = redisTemplate.execute(
                                    REFRESHLOCK_SCRIPT,
                                    Collections.singletonList(lockName),
                                    key + threadId, releaseTime);
                            if (execute == null || execute == 0) {
                                isOpenExpirationRenewal[0] = false;
                            }
                        }
//                        executorService.shutdown();
                    } catch (Exception e) {
                        log.error("刷新redis锁过期时间失败:" + lockName);
                        log.error(e.getMessage());
                        isOpenExpirationRenewal[0] = false;
                    }finally {
                        if (executorService != null){
                            executorService.shutdown();
                        }
                    }
                }
            });
            return key;
        }else {
            return null;
        }
    }
    /**
     * 释放锁
     * @param lockName 锁名称
     * @param key 解锁标识
     */
    public void unlockByRefresh(String lockName,String key) {
        // 执行脚本
        redisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(lockName),
                key + Thread.currentThread().getId(), null);
    }
}