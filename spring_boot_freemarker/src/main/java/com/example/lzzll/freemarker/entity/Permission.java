package com.example.lzzll.freemarker.entity;

    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
* 
* </p>
* @author lzzll
* @since 2022-07-26
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

            /**
            * 主键id
            */
    private Integer id;

            /**
            * 是否可用 0 不可用 1 可用
            */
    private Boolean available;

            /**
            * 权限名称
            */
    private String name;

            /**
            * 权限的父级id
            */
    private Long parentId;

            /**
            * 权限id 按“父级/子级...”格式存储
            */
    private String parentIds;

            /**
            * 权限对应的页面
            */
    private String permission;

            /**
            * 权限对应的类型，菜单、按钮等
            */
    private Integer resourceType;

            /**
            * 权限对应的路径
            */
    private String url;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
