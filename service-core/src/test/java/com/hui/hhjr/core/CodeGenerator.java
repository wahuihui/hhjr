package com.hui.hhjr.core;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * 代码生成器
 *
 * @author hui 1154437939@qq.com
 * @version 2022/6/10 17:02
 * @since JDK8
 */

public class CodeGenerator {

    @Test
    public void genCode(){

        //1、创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //2、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("AoHui");
        globalConfig.setOpen(false);//生成后是否打开资源管理器
        globalConfig.setServiceName("%sService");//去掉Service接口的首字母
        globalConfig.setIdType(IdType.AUTO);//主键策略
        globalConfig.setSwagger2(true);//开启Swagger2模式
        autoGenerator.setGlobalConfig(globalConfig);

        //3、数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/hhjr_core?serverTimezone=GMT%2B8&characterEncoding=utf-8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("mysql1234");
        dataSourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);

        //4、包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.hui.hhjr.core");
        packageConfig.setEntity("pojo.entity");//此对象与数据库表结构一一对应，通过DAO层向上传输数据源对象
        autoGenerator.setPackageInfo(packageConfig);

        //5、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体表的命名策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategyConfig.setEntityLombokModel(true);//lombok
        strategyConfig.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀(确保tinyint(1))
        strategyConfig.setRestControllerStyle(true);//restful api风格控制器
        autoGenerator.setStrategy(strategyConfig);

        //6、执行
        autoGenerator.execute();
    }
}
