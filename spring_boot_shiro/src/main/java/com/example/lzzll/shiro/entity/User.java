package com.example.lzzll.shiro.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/6 15:53
 * @Description:
 */
@Data
@NoArgsConstructor
public class User {

    /**
     * 主键id
     */
    private Long id;

    private String userName;

    private String password;

    private String salt;

    private Integer state;

//    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
//    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<Role> roleList;// 一个用户具有多个角色

    /**
     * 存储用户时可以用用户名和盐一起当作最终生成密码用的盐
     * @return
     */
    public String getCredentialsSalt(){
        return this.userName + this.salt;
    }

}
