package com.example.coderlf.sys.jpa;

import com.example.coderlf.sys.dto.SysUserDto;
import com.example.coderlf.sys.dto.TokenInfoDto;
import com.example.coderlf.sys.entity.SysUserEntity;
import com.example.coderlf.sys.entity.TokenInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @Author lf
 * @Date 2020/7/6 15:01
 * @Description:
 */
public interface TokenJPA extends
        JpaRepository<TokenInfoEntity, Long>,
        JpaSpecificationExecutor<TokenInfoEntity>,
        Serializable
{

    /**
     * 查询token对象
     * @param tokenInfoDto
     * @return
     */
    TokenInfoEntity findByEntity(TokenInfoDto tokenInfoDto);
}
