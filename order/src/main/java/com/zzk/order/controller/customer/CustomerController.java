package com.zzk.order.controller.customer;

import com.zzk.order.entity.Customer;
import com.zzk.order.entity.Order;
import com.zzk.order.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @ResponseBody
    @RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.GET)
    public Customer get(@PathVariable Long id) {
        return customerService.getById(id);
        //return new Customer();
    }

    //eg. /customer/33/orders
    @ResponseBody
    @RequestMapping(value = "/{code}/orders", method = RequestMethod.GET)
    public List<Order> orders(@PathVariable String code) {

        return customerService.getOrdersByCustomerCode(code);
    }

    //eg. /customer/accd84fe-bbeb-4bd7-b98f-61494ebb9a89/ordersEnhance
    @ResponseBody
    @RequestMapping(value = "/{code}/ordersEnhance", method = RequestMethod.GET)
    public List<Order> ordersEnhance(@PathVariable String code) {
        return customerService.getOrdersByCustomerCodeEnhance(code);
    }

}
