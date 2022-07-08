package com.example.lzzll.swagger.controller;

import com.example.lzzll.swagger.entity.Role;
import com.example.lzzll.swagger.response.R;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/8 10:02
 * @Description:
 */
@Api("获取角色信息")
//@RestController
//@RequestMapping("role")
public class RoleController {

    @ApiOperation(value = "分页显示role数据",notes = "")
    @RequestMapping("findAll")
    public R getPersonPage(@ApiParam(name = "每页数据量",example = "5") @RequestParam int number,
                           @ApiParam(name = "页码",value = "当前页码",example = "1") @RequestParam int page) {
        List<Role> roles = Lists.newArrayList();
        Role role = new Role();
        role.setId("1");
        role.setName("测试角色");
        roles.add(role);
        return (R) R.ok().put("data",roles);
    }

}
