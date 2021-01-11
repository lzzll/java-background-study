package com.example.coderlf.sys.jpa;

import com.example.coderlf.sys.dto.SysUserDto;
import com.example.coderlf.sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * The interface User repository.
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    User findById(long id);

    /**
     *
     * @param userDto
     * @return
     */
    @Transactional
    @Query(value="SELECT * from sys_user WHERE id = 1",nativeQuery = true)
    User findByEntity(SysUserDto userDto);

}
