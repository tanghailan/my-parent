package com.kanavi.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanavi.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author My
 * @since 2020-09-08
 */
public interface UserService extends IService<User> {
    IPage<User> findUserPage(Page<User> page,QueryWrapper<User> wrapper);
}
