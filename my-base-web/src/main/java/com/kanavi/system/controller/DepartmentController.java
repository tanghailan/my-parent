package com.kanavi.system.controller;


import com.kanavi.response.api.ResponseBean;
import com.kanavi.response.api.ResultCode;
import com.kanavi.response.exception.ApiException;
import com.kanavi.system.entity.Department;
import com.kanavi.system.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author My
 * @since 2020-09-09
 */
@Api(value = "DepartmentController", tags = "部门管理")
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "查询部门及人数",notes = "从部门表中查出用户分组人数")
    @GetMapping("/findDeptAndCount")
    public ResponseBean findDeptAndCount(){
        List<Department> deptAndCount = departmentService.findDeptAndCount();
        if (deptAndCount.size()==0){
            throw  new ApiException(ResultCode.DEPARTMENT_NOT_EXIST);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("departments",deptAndCount);
        return ResponseBean.success(map);

    }

}

