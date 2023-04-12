package com.example.lzzll.javastudy.redis;//package com.example.lzzll.redis;
//
//import com.canpoint.itembank.factory.common.util.RedisUtil;
//import com.example.lzzll.jdbctemplate.domain.Student;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Iterator;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = com.canpoint.itembank.factory.EduApplication.class)
//public class RedisTest {
//
//	@Autowired
//	private RedisUtil redisUtil;
//
//	@Autowired
//	private RedisTemplate redisTemplate;
//
//    @Autowired
//    private HashOperations<String, String, Object> hashOperations;
//
////	private static RedisUtil redisUtil;
////
////	static {
////		redisUtil = new RedisUtil();
////	}
//
//	@Test
//	public void contextLoads() {
////		SysUserEntity user = new SysUserEntity();
////		user.setEmail("qqq@qq.com");
////		redisUtil.set("user", user);
////
////		System.out.println(ToStringBuilder.reflectionToString(redisUtil.get("user", SysUserEntity.class)));
//	}
//
//	@Test
//	public void setString(){
//		String key = "people";
//		String value = "zhangsan";
//		redisUtil.set(key,value,5*60);
//		String valueResult = redisUtil.get(key);
//		System.out.println(value);
//	}
//
//	@Test
//	public void ObjToJsontest(){
//		Student student = new Student();
//		student.setId(10);
//		student.setName("无名");
//		student.setChinese(90f);
//		student.setMath(80d);
//		student.setEnglish(85f);
//
//	}
//
//	@Test
//	public void setHash(){
//		redisTemplate.boundHashOps("高达seed").put("freedom","jela");
//		redisTemplate.boundHashOps("高达seed").put("justice","asilan");
//		String value = (String) redisTemplate.boundHashOps("高达seed").get("freedom");
//		System.out.println(value);
//		Map map = redisTemplate.boundHashOps("高达seed").entries();
//		Iterator iterator = map.entrySet().iterator();
//		while (iterator.hasNext()){
//			Object obj = iterator.next();
//			System.out.println(obj);
////			String[] strs = obj.split("=");
////			System.out.println("key:"+strs[0]+",value:"+strs[1]);
//		}
//	}
//
//    @Test
//    public void setHashOperations(){
//	    String key = "phone";
//        String smallKey = "apple";
//        String smallKey1 = "xiaomi";
//        String smallValue = "ios";
//        String smallValue2 = "android";
//        Boolean flag = hashOperations.putIfAbsent(key, smallKey, smallKey);
//        System.out.println(flag);
//        String smallValue1 = "ios11";
//        if (hashOperations.hasKey(key,smallKey)){
//            hashOperations.putIfAbsent(key, smallKey, smallValue1);
//        }
//        hashOperations.putIfAbsent(key, smallKey1, smallValue2);
//        redisTemplate.expire(key,30L, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void testDupKey(){
//	    String file = "china:";
//        String key = "phone";
//        String value = "huawei";
//        redisUtil.set(file+key,value,30);
//    }
//
//}
