package com.example.spring_boot_jpa.jpa;

import com.example.spring_boot_jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @Author lf
 * @Date 2020/7/3 13:32
 * @Description:
 */
public interface UserJpa extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User>,
        Serializable {
}
