package com.kanavi.system.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kanavi.enums.UserStatusEnum;
import com.kanavi.enums.UserTypeEnum;
import com.kanavi.response.api.ResultCode;
import com.kanavi.response.exception.ApiException;
import com.kanavi.system.entity.Department;
import com.kanavi.system.entity.User;
import com.kanavi.system.mapper.DepartmentMapper;
import com.kanavi.system.mapper.UserMapper;
import com.kanavi.system.service.DepartmentService;
import com.kanavi.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ResponseHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author tanghailan
 * @since 2020-09-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private DepartmentMapper deptMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public IPage<User> findUserPage(Page<User> page, QueryWrapper<User> wrapper) {
        return this.baseMapper.findUserPage(page,wrapper);
    }



    @Override
    @Transactional
    public void insert(User user) {
        String username = user.getUsername();
        Long departmentId = user.getDepartmentId();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("department_id",departmentId);
        //判断是否存在该用户
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count != 0){
            throw new ApiException(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        //判断部门是否存在
        Department department = deptMapper.selectById(departmentId);
        if (department == null){
            throw new ApiException(ResultCode.DEPARTMENT_NOT_EXIST);
        }
        String salt = IdUtil.randomUUID();
        user.setSalt(salt);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setType(UserTypeEnum.SYSTEM_USER.getTypeCode());
        user.setStatus(UserStatusEnum.AVAILABLE.getStatusCode());
        user.setDeleted(0);
        this.baseMapper.insert(user);
    }
}
