package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Goods;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.vo.CustomerVO;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.GoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public DataVO findAllCustomer(Integer page, Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Customer> customerIPage = new Page<>(page,limit);
        IPage<Customer> reuslt = customerMapper.selectPage(customerIPage,null);
        dataVO.setCount(reuslt.getTotal());
        List<Customer> customerList = reuslt.getRecords();
        List<CustomerVO> customerVOList = new ArrayList<>();
        for(Customer customer:customerList){
            CustomerVO customerVO = new CustomerVO();
            BeanUtils.copyProperties(customer,customerVO);
            if(customer.getType() == 1){
                customerVO.setType("零售客户");
            }else{
                customerVO.setType("批发客户");
            }
            customerVOList.add(customerVO);
        }
        dataVO.setData(customerVOList);
        return dataVO;
    }

    @Override
    public boolean addCustomer(String customerName, String tel, Integer type) {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setTel(tel);
        customer.setType(type);
        int i = customerMapper.insert(customer);
        if(i == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public DataVO searchCustomerData(String customerName, Integer page, Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("customer_name",customerName);
        IPage<Customer> customerIPage = new Page<>(page,limit);
        IPage<Customer> result = customerMapper.selectPage(customerIPage,queryWrapper);
        List<Customer> customerList = result.getRecords();
        dataVO.setCount(result.getTotal());
        List<CustomerVO> customerVOList = new ArrayList<>();
        for(Customer customer:customerList){
            CustomerVO customerVO = new CustomerVO();
            BeanUtils.copyProperties(customer,customerVO);
            if(customer.getType() == 1){
                customerVO.setType("零售客户");
            }else{
                customerVO.setType("批发客户");
            }
            customerVOList.add(customerVO);
        }
        dataVO.setData(customerVOList);
        return dataVO;
    }

    @Override
    public boolean deleteCustomerData(Integer customerId) {
        customerMapper.deleteById(customerId);
        return true;
    }
}
