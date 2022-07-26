package com.example.lzzll.freemarker.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {


    public static void main(String[] args) {
        String projectName = "spring_boot_freemarker";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        final String projectPath = path.substring(0, path.indexOf(projectName) + projectName.length() + 1);
        System.out.println(projectPath);
        AutoGenerator generator = new AutoGenerator();
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("lzzll");
        gc.setOutputDir(projectPath + "src/main/java");
        gc.setFileOverride(false);// 是否覆盖同名文件，默认是false
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        /* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
        gc.setMapperName("%sDao");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
//        gc.setControllerName("%sController");
        generator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/shiro?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8");
        dsc.setTypeConvert(new MySqlTypeConvert());
        generator.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setTablePrefix(new String[] { "wa" });// 表前缀
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude("permission");
//        strategy.setInclude("role");
//        strategy.setInclude("role_permission");
        strategy.setEntityLombokModel(true);
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.example.lzzll.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.example.lzzll.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.example.lzzll.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.example.lzzll.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.example.lzzll.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        generator.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example.lzzll.freemarker");
        //控制器可选生成
//        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mappers");
        pc.setEntity("entity");
//        pc.setXml(null);
        generator.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        //
        //        // 自定义 xxList.jsp 生成
        List<FileOutConfig> focList = new ArrayList<>();
        //        focList.add(new FileOutConfig("/template/list.jsp.vm") {
        //            @Override
        //            public String outputFile(TableInfo tableInfo) {
        //                // 自定义输入文件名称
        //                return "D://my_" + tableInfo.getEntityName() + ".jsp";
        //            }
        //        });
        //        cfg.setFileOutConfigList(focList);
        //        generator.setCfg(cfg);
        //
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "src/main/resources/mappers/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        //
        //        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        generator.setTemplate(tc);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
//         TemplateConfig tc = new TemplateConfig();
//         tc.setController("");
//         tc.setEntity("...");
//         tc.setMapper("...");
//         tc.setXml("...");
//         tc.setService("...");
//         tc.setServiceImpl("...");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
//         generator.setTemplate(tc);

        // 执行生成
        generator.execute();

    }
}
