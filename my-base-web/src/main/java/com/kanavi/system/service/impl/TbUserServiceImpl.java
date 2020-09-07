package com.kanavi.system.service.impl;

import com.kanavi.system.entity.TbUser;
import com.kanavi.system.mapper.TbUserMapper;
import com.kanavi.system.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author My
 * @since 2020-09-07
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

}
