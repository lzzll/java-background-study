package com.example.lzzll.shiro.config.shiro;

import com.example.lzzll.shiro.common.exception.CpException;
import com.example.lzzll.shiro.dto.RolePermissionDto;
import com.example.lzzll.shiro.entity.Permission;
import com.example.lzzll.shiro.entity.Role;
import com.example.lzzll.shiro.entity.User;
import com.example.lzzll.shiro.service.RoleService;
import com.example.lzzll.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/8 15:43
 * @Description:
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userInfo  = (User)principals.getPrimaryPrincipal();
        // 根据用户id查询用户角色
        User user = userService.findByUserId(userInfo.getId());
        if (user.getRoleList() != null && user.getRoleList().size() != 0){
            List<Long> roleIds = new ArrayList<>();
            for(Role role:user.getRoleList()){
                authorizationInfo.addRole(role.getRoleName());
                roleIds.add(role.getId());
                // 根据用户所有的角色查询用户所有的权限
                if (roleIds != null && roleIds.size() != 0){
                    RolePermissionDto rolePermissionDto =  roleService.findByRoleIds(roleIds);
                    if (rolePermissionDto.getPermissions() != null && rolePermissionDto.getPermissions().size() != 0){
                        for(Permission p:role.getPermissions()){
                            authorizationInfo.addStringPermission(p.getPermission());
                        }
                    }
                }
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)throws AuthenticationException {
        System.out.println("用户认证-->MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        if (username == null){
            throw new CpException("参数不能为空");
        }
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        // todo 后面可优化从redis中获取缓存数据
        User userInfo = userService.findByUsername(username);
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getSalt()), // 此处校验的盐需要和用户注册时数据库中存储的盐保持一致 salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }


    public static void main(String[] args) {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("123456");
//        SimpleAuthenticationInfo simpleAuthen = new SimpleAuthenticationInfo(user,user.getPassword(),user.getUserName());
//        System.out.println(simpleAuthen);

        String newPs = new SimpleHash("MD5", user.getPassword(), "", 2).toHex();
        System.out.println(newPs);
        System.out.println("8eeffc1552b97b661705563a70b0e827".equals(newPs));
    }
}
