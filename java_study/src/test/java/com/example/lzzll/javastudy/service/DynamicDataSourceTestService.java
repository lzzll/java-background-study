package com.example.lzzll.javastudy.service;//package com.example.lzzll.service;
//
//import com.canpoint.itembank.factory.datasource.annotation.DataSource;
//import com.canpoint.itembank.factory.modules.sys.entity.SysUserEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * 测试多数据源
// *
// * @author Mark sunlightcs@gmail.com
// */
//@Service
////@DataSource("slave1")
//public class DynamicDataSourceTestService {
//    @Autowired
//    private SysUserDao sysUserDao;
//
//    @Transactional
//    public void updateUser(Long id){
//        SysUserEntity user = new SysUserEntity();
//        user.setUserId(id);
//        user.setUsername("admin");
//        user.setMobile("13500000000");
////        sysUserDao.updateById(user);
//        sysUserDao.updateSysUserById(user);
//    }
//
//    @Transactional
//    @DataSource("slave1")
//    public void updateUserBySlave1(Long id){
//        SysUserEntity user = new SysUserEntity();
//        user.setUserId(id);
//        user.setUsername("admin");
//        user.setMobile("13500000001");
////        sysUserDao.updateById(user);
//        sysUserDao.updateSysUserById(user);
//    }
//
//    @DataSource("slave2")
//    @Transactional
//    public void updateUserBySlave2(Long id){
//        SysUserEntity user = new SysUserEntity();
//        user.setUserId(id);
//        user.setUsername("admin");
//        user.setMobile("13500000002");
////        sysUserDao.updateById(user);
//        sysUserDao.updateSysUserById(user);
//
//        //测试事物
//        int i = 1/0;
//    }
//
//    @Transactional
//    @DataSource("slave3")
//    public void updateUserBySlave3(Long id){
//        SysUserEntity user = new SysUserEntity();
//        user.setUserId(id);
//        user.setUsername("admin");
//        user.setMobile("13500000003");
////        sysUserDao.updateById(user);
//        sysUserDao.updateSysUserById(user);
//    }
//}