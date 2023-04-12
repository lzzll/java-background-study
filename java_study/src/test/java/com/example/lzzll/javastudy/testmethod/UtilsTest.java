package com.example.lzzll.javastudy.testmethod;

import com.example.lzzll.javastudy.common.entity.R;
import com.example.lzzll.javastudy.common.util.DateUtil;
import com.example.lzzll.javastudy.common.util.HttpContextUtil;
import com.example.lzzll.javastudy.common.util.MapUtil;
import com.example.lzzll.javastudy.common.util.RedisUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 方法类，工具类测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UtilsTest.class)
public class UtilsTest {

    @Autowired
//    private TeacherService teacherService;

    @Test
    public void testDateUtil(){
        // 根据周数，获取开始日期、结束日期
        Date[] dates = DateUtil.getWeekStartAndEnd(2);
//        System.out.println(dates.length);
//        for (int i = 0; i < dates.length; i++) {
//            System.out.println(dates[i]);
//        }
        String format = DateUtil.format(dates[0]);
        System.out.println(format);
    }

    @Test
    public void testHttpContextUtil(){
        String domain = HttpContextUtil.getDomain();
        // 获取协议和ip地址
        System.out.println(domain);
        // 获取origin的头信息
        String origin = HttpContextUtil.getOrigin();
        System.out.println(origin);
    }

    @Test
    public void testMapUtil(){
        MapUtil mapUtil = new MapUtil();
        MapUtil map = mapUtil.put("zhangsan", "lisi");
        System.out.println(map);
    }

    @Test
    public void testR(){
//        R r = new R(HttpStatus.SC_INTERNAL_SERVER_ERROR,"系统访问错误");
        R r = R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "系统访问错误");
        System.out.println(r);
    }

    @Test
    public void testReidsUtil(){
        RedisUtil redisUtil = new RedisUtil();
        HashMap<String, String> map = new HashMap<>();
        map.put("zhangsan","zhangsan");
        redisUtil.set("zhangsan",map);
        HashMap mapResult = redisUtil.get("zhangsan", HashMap.class);
        System.out.println(mapResult);  // null point exception
    }

    @Test
    public void testShrioUtil(){
//        Session session = ShiroUtil.getSession();
//        SysUserEntity userEntity = ShiroUtil.getUserEntity();
//        Subject subject = ShiroUtil.getSubject();
//        System.out.println(subject.toString());
//        System.out.println(userEntity.toString());
//        System.out.println(session);  // No SecurityManager accessible to the calling code

    }


//    @Test
//    public void testValidatorUtil(){
//        List<Object> list = new ArrayList<>();
//
////        System.out.println(flag);
//        ValidatorUtil.validateEntity(list);
//    }

    /**
     * 测试controller公共组件
     */
    @Test
    public void testAbstractController(){
//        AbstractController abstractController = new AbstractController();
    }

    /**
     * 测试系统配置信息类
     */
    @Test
    public void testSysConfigController(){
//        SysConfigController sysConfigController = new SysConfigController();
//        sysConfigController.
    }

    /**
     * 测试使用sha256对密码进行加密
     */
    @Test
    public void testSha256Hash(){
        String salt = RandomStringUtils.randomAlphanumeric(20);
        System.out.println(salt);
//        new Sha256Hash("123456",salt).toHex();
        String encodePassword = new Sha256Hash("admin", salt).toHex();
        System.out.println(encodePassword);
    }

    /**
     * 测试加密后的密码匹配
     */
    @Test
    public void testAdaptPassword(){
        String encodePassword = "9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d";
        String salt = "YzcmCZNvbXocrsz9dm8e";
        boolean flag = encodePassword.equals(new Sha256Hash("admin", salt).toHex());
        System.out.println(flag);
    }


}
