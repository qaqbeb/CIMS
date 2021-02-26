package com.example.demo.controller;

import com.example.demo.entity.MyOrder;
import com.example.demo.service.OrderService;
import com.example.demo.vo.GoodsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/showGoods")
    @ResponseBody
    public List<GoodsVO> showGoods() {
        return orderService.showGoods();
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public boolean addOrder(String goods_list, Integer warehouse_id, Integer customer_id) {
        return orderService.addOrder(goods_list, warehouse_id, customer_id);
    }

    @RequestMapping(value = "/getUncheckList")
    @ResponseBody
    public List<MyOrder> getUncheckList() {
        return orderService.getUncheckList();
    }

    @RequestMapping(value = "/getCheckedList")
    @ResponseBody
    public List<MyOrder> getCheckedList() {
        return orderService.getCheckedList();
    }

    @RequestMapping(value = "/getCheckedRetailedList")
    @ResponseBody
    public List<MyOrder> getCheckedRetailedList() {
        return orderService.getCheckedRetailedList();
    }

    @RequestMapping(value = "/check")
    @ResponseBody
    public boolean checkOrder(@RequestBody List<MyOrder> orderList) {
        return orderService.checkOrder(orderList);
    }

    @RequestMapping(value = "/checkLastestOrder")
    @ResponseBody
    public boolean checkLastestOrder() {
        return orderService.checkLastestOrder();
    }

    @RequestMapping(value = "/orderList")
    @ResponseBody
    public List<MyOrder> getorderList() {
        return orderService.getorderList();
    }

    @RequestMapping(value = "/profit")
    @ResponseBody
    public double calculatProfit() {
        return orderService.calculatProfit();
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public boolean deleteOrder(Integer orderId) {
        return orderService.deleteOrder(orderId);
    }

    @RequestMapping(value = "/refund")
    @ResponseBody
    public boolean refundOrder(Integer orderId) {
        return orderService.refundOrder(orderId);
    }

}
