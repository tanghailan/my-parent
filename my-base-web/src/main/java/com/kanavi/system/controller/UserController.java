package com.kanavi.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanavi.response.api.ResponseBean;
import com.kanavi.system.entity.User;
import com.kanavi.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author My
 * @since 2020-09-08
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseBean findUserList(@RequestParam(required = true,defaultValue = "1")int current,
                                     @RequestParam(required = true,defaultValue = "7")int size){
        Page<User> userPage = new Page<>(current,size);
        Page<User> page = userService.page(userPage);
        long total = page.getTotal();
        List<User> records = page.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data",records);
        return ResponseBean.success(map);
    }

}

