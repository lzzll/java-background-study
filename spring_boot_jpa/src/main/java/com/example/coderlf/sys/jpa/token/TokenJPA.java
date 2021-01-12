package com.example.coderlf.sys.jpa.token;

import com.example.coderlf.sys.dto.TokenInfoDto;
import com.example.coderlf.sys.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * @Author lf
 * @Date 2020/7/6 15:01
 * @Description:
 */
public interface TokenJPA extends
        JpaRepository<Token, Long>
        ,JpaSpecificationExecutor<Token>
        ,Serializable
{

    /**
     * 查询token对象,自定义方法中如果不写sql语句，会报创建Jpa失败的异常
     * @param tokenInfoDto
     * @return
     */
//    @Transactional
//    @Query(value="SELECT * from api_token_infos WHERE id = 1",nativeQuery = true)
//    Token findByEntity(TokenInfoDto tokenInfoDto);


    /**
     * 使用jpa查询时需要在参数中添加@Param注解来标识参数
     * @param userId
     * @return
     */
    @Transactional
    @Query(value="SELECT * from api_token_infos WHERE user_id = :userId",nativeQuery = true)
    Token findByEntity(@Param("userId") Long userId);
}
