package com.example.coderlf.thymeleaf.service.impl;

import com.example.coderlf.thymeleaf.model.User;
import com.example.coderlf.thymeleaf.service.UserRepository;
import com.example.coderlf.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User repository
 * <p/>
 * Created in 2018.07.25
 * <p/>
 *
 * @author <a href="https://github.com/liaozihong" style="background: #55a7e3;">Liaozihong</a>
 */
@Service
public class UserRepositoryImpl implements UserService {
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
}
