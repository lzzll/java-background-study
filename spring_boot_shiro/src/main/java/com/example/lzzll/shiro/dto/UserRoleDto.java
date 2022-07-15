package com.example.lzzll.shiro.dto;

import com.example.lzzll.shiro.entity.Role;
import com.example.lzzll.shiro.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/11 16:02
 * @Description:
 */
@Data
public class UserRoleDto extends User{

    private List<Role> roleList;

}
