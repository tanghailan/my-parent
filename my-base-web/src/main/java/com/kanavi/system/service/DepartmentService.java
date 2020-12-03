package com.kanavi.system.service;

import com.kanavi.system.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghailan
 * @since 2020-09-09
 */
public interface DepartmentService extends IService<Department> {
    List<Department> findDeptAndCount();
}
