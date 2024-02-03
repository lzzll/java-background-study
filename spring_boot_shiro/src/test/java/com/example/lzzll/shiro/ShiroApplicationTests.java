package com.example.lzzll.shiro;

import com.example.lzzll.shiro.dto.RolePermissionDto;
import com.example.lzzll.shiro.entity.User;
import com.example.lzzll.shiro.mapper.RoleMapper;
import com.example.lzzll.shiro.service.RoleService;
import com.example.lzzll.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class ShiroApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Test
	void contextLoads() {
		User user = userService.findByUserId(2L);
		System.out.println(user);
	}

	@Test
	void testRole() {
		ArrayList<Long> roleIds = new ArrayList<>();
		roleIds.add(1L);
		roleIds.add(2L);
		RolePermissionDto roles = roleService.findByRoleIds(roleIds);
		System.out.println(roles);
	}

	@Test
	public void test1() {
		//读取配置文件，相当于在加载数据源
		Factory<SecurityManager> factory =
//				new IniSecurityManagerFactory("classpath/shiro_1.ini");
				new IniSecurityManagerFactory("G:\\java-background-study\\spring_boot_shiro\\src\\test\\resources\\classpath\\shiro_1.ini");
		//SecurityManager 是Shiro内部的底层实现，几乎所有功能都由其实现
		org.apache.shiro.mgt.SecurityManager sm = factory.getInstance();
		//SecurityUtils是一个工具，方便用户调用，它封装了SecurityManager
		SecurityUtils.setSecurityManager(sm);
		//生成一个SecurityManager的门面类，即Subject。
		Subject subject = SecurityUtils.getSubject();
		//封装用户的数据
		UsernamePasswordToken token = new UsernamePasswordToken("jay", "123");
		//Subject接收到的方法参数，最终将会传到SecurityManager中进行验证
		//将用户的数据token 最终传递到Realm中进行对比
		subject.login(token);
		//判断本帐号是否已经被认证
		System.out.println(subject.isAuthenticated());
	}

}
