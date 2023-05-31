-- 创建一张用户token表
CREATE TABLE `api_token_infos` (
  `token_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `user_token` blob,
  `token_build_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '生成token时间（秒单位）',
  PRIMARY KEY (`token_id`) USING BTREE,
  KEY `ati_app_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='api请求token信息表，根据appId保存';

-- 创建一张用户密码表
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `user_age` int(5) DEFAULT NULL COMMENT '用户年龄',
  `phone` varchar(32) DEFAULT NULL COMMENT '用户手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '用户邮箱',
  `account` varchar(64) DEFAULT NULL COMMENT '用户账户',
  `secret` varchar(255) DEFAULT NULL COMMENT '用户密码，采用MD5进行加密',
  `status` tinyint(1) DEFAULT NULL COMMENT '用户状态,1：正常，0：无效',
  `mark` varchar(255) DEFAULT NULL COMMENT '用户备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;