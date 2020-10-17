package com.kanavi.system.controller;



import com.kanavi.response.api.ResponseBean;
import com.kanavi.system.entity.Role;
import com.kanavi.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author My
 * @since 2020-09-09
 */
@RestController
@RequestMapping("/system/role")
@Api(value = "RoleController", tags = "角色管理")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping
    @ApiOperation(value = "根据参数查询角色列表", notes = "分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "页数"),
    })
    public ResponseBean findRoleList(@RequestParam(required = true,defaultValue = "1")int current,
                                     @RequestParam(required = true,defaultValue = "7")int size){
        List<Role> list = roleService.list();
        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        return ResponseBean.success(map);
    }

}

