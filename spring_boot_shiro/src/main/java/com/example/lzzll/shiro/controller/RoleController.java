package com.example.lzzll.shiro.controller;

import com.example.lzzll.shiro.common.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lf
 * @Date 2022/7/12 10:39
 * @Description: 角色控制器，测试权限认证的问题
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @RequestMapping(value = "/helloTest",method = RequestMethod.GET)
    public R helloTest(){
        return R.ok();
    }

    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @RequiresPermissions("role:addRole")
    public R addRole(){
        return R.ok();
    }

    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/roleList")
    @RequiresRoles("manager")//权限管理;
    public String roleList(){
        return "roleList";
    }


}
