package com.kanavi.system.controller;


import com.kanavi.system.entity.TbUser;
import com.kanavi.system.mapper.TbUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author My
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/system/tbUser")
@Api(value = "用户管理")
public class TbUserController {

    @Autowired
    private TbUserMapper tbUserMapper;

    @GetMapping
    @ApiOperation(value = "查询所有的用户信息",notes = "")
    public List<TbUser> findUsers(){
        List<TbUser> tbUsers = tbUserMapper.selectList(null);
        return tbUsers;
    }

}

