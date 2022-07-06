package com.example.lzzll.security.service.impl;

import com.example.lzzll.security.entity.SecurityUser;
import com.example.lzzll.security.entity.UserRoleEntity;
import com.example.lzzll.security.mapper.SecurityUserMapper;
import com.example.lzzll.security.mapper.UserRoleMapper;
import com.example.lzzll.security.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/6 15:34
 * @Description:
 */
@Service("SecurityUserServiceImpl")
@Transactional
public class SecurityUserServiceImpl implements SecurityUserService {

    @Autowired
    private SecurityUserMapper securityUserMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SecurityUser user = securityUserMapper.queryByUserName(userName);
        if (user == null){
            user = new SecurityUser();
        }else {
            List<UserRoleEntity> userRoles = userRoleMapper.findByUserId(user.getId());
            if (userRoles != null && userRoles.size() != 0){
                user.setPaths(userRoles);
            }
        }
        return user;
    }
}
