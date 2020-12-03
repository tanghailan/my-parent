package com.kanavi.system.mapper;

import com.kanavi.system.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tanghailan
 * @since 2020-09-09
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> findDeptAndCount();
}
