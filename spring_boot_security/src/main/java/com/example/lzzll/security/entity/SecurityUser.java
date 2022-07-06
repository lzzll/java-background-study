package com.example.lzzll.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lf
 * @Date 2022/7/6 15:53
 * @Description:
 */
@Data
public class SecurityUser implements UserDetails {

    /**
     * 主键id
     */
    private Long id;

    private String userName;

    private String password;

    private String role;

    private List<String> paths;

    // 写死固定权限
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> auth = new ArrayList<>();
//        auth.add(new SimpleGrantedAuthority("ADMIN"));
//        return auth;
//    }

    /**
     * 从数据库动态获取数据设置
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auth = new ArrayList<>();
        if (this.paths != null && this.paths.size() != 0){
            for (String path : this.paths) {
                auth.add(new SimpleGrantedAuthority(path));
            }
        }
        return auth;
    }

    public void setPaths(List<UserRoleEntity> userRoles){
        this.paths = userRoles.stream().map(UserRoleEntity::getRolePath).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
