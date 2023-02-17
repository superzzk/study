package com.zzk.order.controller.customer;

import com.zzk.order.entity.Order;
import com.zzk.order.service.order.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    IOrderService orderService;

    //eg. /order/33
    @ResponseBody
    @RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.GET)
    public Order init(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    /**
     * 模拟下单
     * eg. /order/simulator
     */
    @RequestMapping(value = "/simulator", method = RequestMethod.GET)
    public void simulate() throws InterruptedException {
        logger.info("启动订单模拟器");
        orderService.simulator();
        logger.info("完成模拟下单");
    }

}
