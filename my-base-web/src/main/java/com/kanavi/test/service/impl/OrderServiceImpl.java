package com.kanavi.test.service.impl;

import com.kanavi.test.entity.Order;
import com.kanavi.test.mapper.OrderMapper;
import com.kanavi.test.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author My
 * @since 2020-09-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
