package com.example.coderlf.sys.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.coderlf.common.entity.ResponseEntity;
import com.example.coderlf.common.util.MD5Util;
import com.example.coderlf.sys.dto.SysUserDto;
import com.example.coderlf.sys.dto.TokenInfoDto;
import com.example.coderlf.sys.entity.SysUserEntity;
import com.example.coderlf.sys.entity.TokenInfoEntity;
import com.example.coderlf.sys.jpa.SysUserJpa;
import com.example.coderlf.sys.jpa.TokenJPA;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author lf
 * @Date 2020/7/6 15:25
 * @Description:
 */
@RestController
@RequestMapping("jwt")
public class TokenController {

    @Autowired
    private TokenJPA tokenJPA;

    @Autowired
    private SysUserJpa sysUserJpa;

    /**
     * 获取token，更新token
     * @param account 用户编号
     * @param secret 用户密码
     * @return
     */
    @RequestMapping(value = "/token", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity token(@RequestParam String account, @RequestParam String secret){
        //appId is null
        if(StringUtils.isEmpty(account)){
            return ResponseEntity.forbidden("appId is not found!");
        }
        //appSecret is null
        else if(StringUtils.isEmpty(secret))
        {
            return ResponseEntity.forbidden("appSecret is not found!");
        }
        // 做其它查询校验
        else
        {
            //根据appId查询用户实体
            SysUserDto userDto = new SysUserDto();
            userDto.setAccount(account);
            SysUserEntity userDbInfo = sysUserJpa.findByEntity(userDto);
            // 用户不存在
            if (userDbInfo == null)
            {
                return ResponseEntity.forbidden("account : " + account + ", is not found!");
            }
            // 验证用户密码是否正确，进行md5加密比较
            else if (!new String(userDbInfo.getSecret()).equals(MD5Util.md5password(secret.trim())))
            {
                return ResponseEntity.forbidden("appSecret is not effective!");
            }
            else
            {
                TokenInfoDto tokenInfoDto = new TokenInfoDto();
                tokenInfoDto.setUserId(userDbInfo.getId());
                TokenInfoEntity tokenEntity = tokenJPA.findByEntity(tokenInfoDto);
                //返回token值
                String tokenStr = null;
                //tokenDBEntity == null -> 生成newToken -> 保存数据库 -> 写入内存 -> 返回newToken
                if(tokenEntity == null)
                {
                    //生成jwt,Token
                    tokenStr = createNewToken(account);
                    //将token保持到数据库
                    TokenInfoEntity tokenInfoEntity = new TokenInfoEntity();
                    tokenInfoEntity.setUserId(userDbInfo.getId());
                    tokenInfoEntity.setBuildTime(String.valueOf(System.currentTimeMillis()));
                    tokenInfoEntity.setUserToken(tokenStr.getBytes());
                    tokenJPA.save(tokenInfoEntity);
                }
                //tokenDBEntity != null -> 验证是否超时 ->
                //不超时 -> 直接返回dbToken
                //超时 -> 生成newToken -> 更新dbToken -> 更新内存Token -> 返回newToken
                else
                {
                    //判断数据库中token是否过期，如果没有过期不需要更新直接返回数据库中的token即可
                    //数据库中生成时间
                    long dbBuildTime = Long.valueOf(tokenEntity.getBuildTime());
                    //当前时间
                    long currentTime = System.currentTimeMillis();
                    //如果当前时间 - 数据库中生成时间 < 7200 证明可以正常使用
                    long second = TimeUnit.MILLISECONDS.toSeconds(currentTime - dbBuildTime);
                    if (second > 0 && second < 7200) {
                        tokenStr = new String(tokenEntity.getUserToken());
                    }
                    //超时
                    else{
                        //生成newToken
                        tokenStr = createNewToken(account);
                        //更新token
                        tokenEntity.setUserToken(tokenStr.getBytes());
                        //更新生成时间
                        tokenEntity.setBuildTime(String.valueOf(System.currentTimeMillis()));
                        //执行更新
                        tokenJPA.save(tokenEntity);
                    }
                }
                //设置返回token
                return ResponseEntity.ok().put("token",tokenStr);
            }
        }
    }
    /**
     * 创建新token
     * @param appId
     * @return
     */
    private String createNewToken(String appId){
        //获取当前时间
        Date now = new Date(System.currentTimeMillis());
        //过期时间-两小时
        Date expiration = new Date(now.getTime() + 7200000);
        return Jwts
                .builder()
                .setSubject(appId)   // 面向用户，抽象主题
                //.claim(YAuthConstants.Y_AUTH_ROLES, userDbInfo.getRoles())
                .setIssuedAt(now)  // 何时签发，设置当前时间
                .setIssuer("Online YAuth Builder")   // token签发者
                .setExpiration(expiration) // 过期时间
                .signWith(SignatureAlgorithm.HS256, "HengYuAuthv1.0.0")  // 加密方法
                .compact();  // 设置序列化，url安全化
    }





}
