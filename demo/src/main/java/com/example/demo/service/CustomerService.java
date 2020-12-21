package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.vo.DataVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
public interface CustomerService extends IService<Customer> {
    public DataVO findAllCustomer(Integer page, Integer limit);

    public boolean addCustomer(String customerName, String tel, Integer type);

    public DataVO searchCustomerData(String customerName, Integer page, Integer limit);

    public boolean deleteCustomerData(Integer customerId);
}
