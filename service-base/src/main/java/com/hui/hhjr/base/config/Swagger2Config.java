package com.hui.hhjr.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swagger配置类
 *
 * @author hui 1154437939@qq.com
 * @version 2022/6/10 20:35
 * @since JDK8
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("辉辉金融后台管理系统API文档")
                .description("本文档描述了辉辉金融后台管理系统的各个功能模块的接口调用方式")
                .version("1.0")
                .contact(new Contact("AoHui",null,"1154437939@qq.com"))
                .build();
    }

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("辉辉金融网站API文档")
                .description("本文档描述了辉辉金融网站的各个功能模块的接口调用方式")
                .version("1.0")
                .contact(new Contact("AoHui",null,"1154437939@qq.com"))
                .build();
    }

}
