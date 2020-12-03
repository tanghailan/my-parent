package com.kanavi.system.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanavi.response.api.ResponseBean;
import com.kanavi.system.entity.User;
import com.kanavi.system.service.UserService;
import com.kanavi.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author tanghailan
 * @since 2020-09-08
 */
@RestController
@RequestMapping("/system/user")
@Api(value = "UserController", tags = "用户管理模块")
public class UserController {
    @Resource
    private UserService userService;

//    @GetMapping
//    @ApiOperation(value = "根据参数查询用户列表", notes = "分页查询列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "current", value = "当前页"),
//            @ApiImplicitParam(name = "size", value = "页数"),
//            @ApiImplicitParam(name = "departMentId", value = "部门id"),
//    })
//    public ResponseBean findUserList(@RequestParam(required = true,defaultValue = "1")int current,
//                                     @RequestParam(required = true,defaultValue = "7")int size,
//                                     @RequestParam(required = false)int departMentId){
//        Page<User> userPage = new Page<>(current,size);
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getDepartmentId,departMentId);
//        Page<User> page = userService.page(userPage,queryWrapper);
//        long total = page.getTotal();
//        List<User> records = page.getRecords();
//        Map<String,Object> map = new HashMap<>();
//        map.put("total",total);
//        map.put("data",records);
//        return ResponseBean.success(map);
//    }


    @PostMapping("/findUserPage")
    @ApiOperation(value = "根据参数查询用户列表", notes = "分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "页数"),
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "nickname", value = "昵称"),
            @ApiImplicitParam(name = "email", value = "邮箱"),
            @ApiImplicitParam(name = "sex", value = "性别"),
            @ApiImplicitParam(name = "departmentId", value = "部门id"),
    })
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
    @ApiOperation(value = "新增用户", notes = "分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "nickname", value = "昵称"),
            @ApiImplicitParam(name = "email", value = "邮箱"),
            @ApiImplicitParam(name = "sex", value = "性别"),
            @ApiImplicitParam(name = "departmentId", value = "部门id"),
    })
    public ResponseBean addUser(@RequestBody User user){
        try {
            userService.insert(user);
        } catch (Exception e) {
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.success("新增成功！");
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

