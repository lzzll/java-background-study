package com.example.lzzll.shiro;

import com.example.lzzll.shiro.dto.RolePermissionDto;
import com.example.lzzll.shiro.entity.User;
import com.example.lzzll.shiro.mapper.RoleMapper;
import com.example.lzzll.shiro.service.RoleService;
import com.example.lzzll.shiro.service.UserService;
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
}
