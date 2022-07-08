package com.example.lzzll.swagger.controller;

import com.example.lzzll.swagger.entity.User;
import com.example.lzzll.swagger.response.Res;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import org.apache.http.protocol.HTTP;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/8 10:02
 * @Description:
 */
@Api(tags = "获取用户信息")
@RestController
@RequestMapping("user")
public class UserController {


    @ApiOperation(value = "分页显示Person数据",notes = "user列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页码",dataType = "int",example = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数据条数",dataType = "int",example = "5")
    })
//    @RequestMapping("findAll") // 不指定会生成多个接口文档
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public Res getPersonPage(@ApiParam(name = "每页数据量",example = "5") @RequestParam(required = false,defaultValue = "5") int pageSize,
                             @ApiParam(name = "页码",value = "当前页码",example = "1") @RequestParam(required = false,defaultValue = "1") int currentPage) {
        Res<Object> result = new Res<>(200,"success");
        List<User> users = Lists.newArrayList();
        User user = new User();
        user.setUsername("测试名字");
        user.setPassword("123456");
        users.add(user);
        result.setData(users);
        return result;
    }

}
