package com.example.lzzll.jpa.sys.jpa.user;

import com.example.lzzll.jpa.sys.dto.SysUserDto;
import com.example.lzzll.jpa.sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * The interface User repository.
 *
 */
public interface UserRepository extends JpaRepository<User, Long>
        ,JpaSpecificationExecutor<User>
        ,Serializable {
    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    User findById(long id);

    /**
     * 自定义sql，使用实体类查询不知道如何判断某个字段是否为空
     * @param userDto
     * @return
     */
    @Transactional
//    @Query(value = "update Stu wxpay set stu.name = :name, stu.alias = :alias, stu.age = :age where stu.id = :id")
//    @Query(value = "update Stu wxpay set stu.name = ?1, stu.alias = ?3,stu.age = ?2 where stu.id = ?4")
//    @Query(value = "update Stu s set s.name = :#{#stu.name}, s.age = :#{#stu.age},s.alias = :#{#stu.alias} where s.id = :#{#stu.id} ")
    @Query(value="SELECT * from sys_user WHERE if(:#{#userDto.account} != '',account = :#{#userDto.account},1=1) and if(:#{#userDto.userName} != '',user_name = :#{#userDto.userName},1=1 )",nativeQuery = true)
    List<User> findByEntity1(@Param("userDto") SysUserDto userDto);


    /**
     * 校验参数是否为空的查询，如果不加nativeQuery = true这个条件，就不能使用"*"来做结果集
     * @param account
     * @param userName
     * @return
     */
    @Query(value = "SELECT * from sys_user where if(?1!='',account=?1,1=1) and if(?2!='',user_name=?2,1=1)",nativeQuery = true)
    List<User> findByMany(String account, String userName);

    /**
     * 使用":参数"名的方式需要使用@Param指定参数名
     * @param account
     * @param userName
     * @return
     */
    @Query(value = "SELECT * from sys_user where if(:account!='',account=:account,1=1) and if(:userName!='',user_name=:userName,1=1)",nativeQuery = true)
    List<User> findByMany1(@Param("account")String account, @Param("userName")String userName);



}
