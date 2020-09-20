package com.kanavi.system.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanavi.response.api.ResponseBean;
import com.kanavi.system.entity.User;
import com.kanavi.system.service.UserService;
import com.kanavi.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@Api(tags ="UserController",description = "用户管理模块")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation("根据参数查询用户列表")
    public ResponseBean findUserList(@RequestParam(required = true,defaultValue = "1")int current,
                                     @RequestParam(required = true,defaultValue = "7")int size,
                                     @RequestParam(required = false)int departMentId){
        Page<User> userPage = new Page<>(current,size);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getDepartmentId,departMentId);
        Page<User> page = userService.page(userPage,queryWrapper);
        long total = page.getTotal();
        List<User> records = page.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data",records);
        return ResponseBean.success(map);
    }


    @PostMapping("/findUserPage")
    @ApiOperation("根据参数查询用户列表")
    public ResponseBean findUserPage(@RequestParam(required = true,defaultValue = "1")int current,
                                     @RequestParam(required = true,defaultValue = "7")int size,
                                     @RequestBody UserVo userVo){
        Page<User> userPage = new Page<>(current,size);
        QueryWrapper<User> wrapper = getWrapper(userVo);
        IPage<User> iPage = userService.findUserPage(userPage, wrapper);
        long total = iPage.getTotal();
        List<User> records = iPage.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data",records);
        return ResponseBean.success(map);
    }

    @PostMapping("/addUser")
    @ApiOperation("新增用户")
    public ResponseBean addUser(@RequestBody User user){
        int insert = userService.insert(user);
        if (insert > 0){
            return ResponseBean.success(insert);
        }
        return ResponseBean.error("新增失败！");
    }


    private QueryWrapper<User> getWrapper(UserVo userVo){
        QueryWrapper<User> queryWrapper = null;
        if (userVo != null){
            queryWrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(userVo.getDepartmentId())){
                queryWrapper.eq("department_id",userVo.getDepartmentId());
            }
            if (!StringUtils.isEmpty(userVo.getEmail())){
                queryWrapper.eq("email",userVo.getEmail());
            }
            if (!StringUtils.isEmpty(userVo.getNickname())){
                queryWrapper.eq("nickname",userVo.getNickname());
            }
            if (!StringUtils.isEmpty(userVo.getUsername())){
                queryWrapper.eq("username",userVo.getUsername());
            }
            if (!StringUtils.isEmpty(userVo.getSex())){
                queryWrapper.eq("sex",userVo.getSex());
            }
        }
        return queryWrapper;
    }

}

