package com.example.lzzll.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体类") // 实体类注释，在swagger页面下面model会显示实体类
public class User implements Serializable {
	public static final long serialVersionUID = 42L;

	@ApiModelProperty(value = "用户名", example = "miku")
	 String username;

	@ApiModelProperty(value = "密码", example = "123456")
	 String password;
}
