package com.example.coderlf.sys.service.impl;

import com.example.coderlf.sys.dto.SysUserDto;
import com.example.coderlf.sys.entity.User;
import com.example.coderlf.sys.jpa.user.UserRepository;
import com.example.coderlf.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User repository
 * <p/>
 * Created in 2018.07.25
 * <p/>
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByEntity(SysUserDto sysUserDto) {
        // 通过多字段查询
//        List<User> users = userRepository.findByMany(sysUserDto.getAccount(),sysUserDto.getUserName());
        // 通过多字段查询2
//        List<User> users = userRepository.findByMany1(sysUserDto.getAccount(),sysUserDto.getUserName());
        // 通过实体类查询，在dao层方法上通过来判断参数是否缺失,不能用这种方法来查询
        List<User> users = userRepository.findByEntity1(sysUserDto);
        return users.size() != 0 ? users.get(0):null;
    }
}
