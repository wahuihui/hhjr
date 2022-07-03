package com.hui.hhjr.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hui 1154437939@qq.com
 * @version 2022/6/10 17:59
 * @since JDK8
 */

@SpringBootApplication
@ComponentScan({"com.hui.hhjr"})
public class ServiceCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class,args);
    }

}
