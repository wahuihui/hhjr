package com.hui.hhjr.core.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author AoHui
 * @since 2022-06-10
 */
@Api(tags = "网站积分接口")
@CrossOrigin
@RestController
@RequestMapping("/api/core/integralGrade")
public class IntegralGradeController {

    @GetMapping("/api")
    public void api(){
        return;
    }
}

