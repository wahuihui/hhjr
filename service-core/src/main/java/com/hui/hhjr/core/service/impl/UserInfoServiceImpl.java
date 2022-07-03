package com.hui.hhjr.core.service.impl;

import com.hui.hhjr.core.pojo.entity.UserInfo;
import com.hui.hhjr.core.mapper.UserInfoMapper;
import com.hui.hhjr.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author AoHui
 * @since 2022-06-10
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
