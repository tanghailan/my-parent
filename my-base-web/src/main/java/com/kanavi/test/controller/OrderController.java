package com.kanavi.test.controller;


import com.kanavi.test.current.LanLock;
import com.kanavi.test.entity.Order;
import com.kanavi.test.service.OrderService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author My
 * @since 2020-09-10
 */
@RestController
@RequestMapping("/test/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    LanLock lanLock = new LanLock();

    @RequestMapping("/test")
    public String decStockLock(){
        lanLock.lock();
        Integer stock;
        Order order = orderService.getById("1");
        if (order == null || (stock=order.getStock()) <= 0){
            log.info("下单失败，已经没有库存了！");
            lanLock.unlock();
            return "下单失败，已经没有库存了";
        }
        stock--;
        order.setStock(stock);
        orderService.updateById(order);
        log.info("下单成功!当前剩余产品数量为：----》"+stock);

        lanLock.unlock();
        return "下单成功!当前剩余产品数量为：----》"+stock;

    }
}

