package com.example.demo.service;

import com.example.demo.entity.MyOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.vo.GoodsVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
public interface OrderService extends IService<MyOrder> {
    public List<GoodsVO> showGoods();

    public boolean addOrder(String goods_list, Integer warehouse_id, Integer customer_id);

    public List<MyOrder> getUncheckList();

    public boolean checkOrder(List<MyOrder> orderList);

    public List<MyOrder> getorderList();

    public double calculatProfit();

    public boolean deleteOrder(Integer orderId);

    public List<MyOrder> getCheckedList();

    public boolean checkLastestOrder();

    public List<MyOrder> getCheckedRetailedList();

    public boolean refundOrder(Integer orderId);

}
