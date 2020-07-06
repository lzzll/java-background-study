package com.example.coderlf.sys.jpa;

import com.example.coderlf.sys.dto.SysUserDto;
import com.example.coderlf.sys.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @Author lf
 * @Date 2020/7/3 13:32
 * @Description:
 */
public interface SysUserJpa extends
        JpaRepository<SysUserEntity, Long>,
        JpaSpecificationExecutor<SysUserEntity>,
        Serializable
{

    /**
     * 根据实体类查询用户对象
     * @param userDto
     * @return
     */
    SysUserEntity findByEntity(SysUserDto userDto);



}
