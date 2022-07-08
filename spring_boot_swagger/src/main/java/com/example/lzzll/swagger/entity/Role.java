package com.example.lzzll.swagger.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel("角色实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable
{
	public static final long serialVersionUID = 1L;

	@ApiModelProperty("角色名")
	String name;

	@ApiModelProperty("角色id")
	String id;
}
