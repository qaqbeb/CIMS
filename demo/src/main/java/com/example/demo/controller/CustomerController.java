package com.example.demo.controller;


import com.example.demo.service.CustomerService;
import com.example.demo.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customerList")
    @ResponseBody
    public DataVO findAllCustomer(Integer page,Integer limit){
        return customerService.findAllCustomer(page, limit);
    }

    @RequestMapping(value = "/addCustomer")
    @ResponseBody
    public boolean addCustomer(String customerName,String tel,Integer type){
        return customerService.addCustomer(customerName, tel, type);
    }

    @RequestMapping(value = "/searchCustomer")
    @ResponseBody
    public DataVO searchCustomerData(String customerName,Integer page,Integer limit){
        return customerService.searchCustomerData(customerName,page, limit);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public boolean deleteCustomerData(Integer customerId){
        return customerService.deleteCustomerData(customerId);
    }


}

