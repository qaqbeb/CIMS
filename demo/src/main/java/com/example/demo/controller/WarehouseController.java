package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Warehouse;
import com.example.demo.service.WarehouseService;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.SaleGoodsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService WHS;

    @RequestMapping(value = "/warehouseList")
    @ResponseBody
    public DataVO<Warehouse> getwarehouseList() {
        return WHS.getwarehouseList();
    }

    @RequestMapping(value = "/goodsList")
    @ResponseBody
    public DataVO<SaleGoodsVO> getGoodsListById(Integer warehouseId) {
        return WHS.getGoodsListById(warehouseId);
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public boolean addWarehouse() {
        return WHS.addWarehouse();
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public boolean deleteWarehouse(Integer warehouseId) {
        return WHS.deleteWarehouse(warehouseId);
    }

    @RequestMapping(value = "/stock")
    @ResponseBody
    public boolean stock(String stock_list) {
        return WHS.stock(stock_list);
    }

    @RequestMapping(value = "/transfer")
    @ResponseBody
    public boolean transfer(Integer goodsId, Integer src_whId, Integer dest_whId, Integer num) {
        return WHS.transfer(goodsId, src_whId, dest_whId, num);
    }

    @RequestMapping(value = "/warehouse_total_number")
    @ResponseBody
    public Integer warehouseTotalNumber() {
        return WHS.warehouseTotalNumber();
    }

    @RequestMapping(value = "/warehouse_total_price")
    @ResponseBody
    public Double warehouseTotalPrice() {
        return WHS.warehouseTotalPrice();
    }

    @RequestMapping(value = "/totalGoodsList")
    @ResponseBody
    public DataVO<SaleGoodsVO> totalGoodsList() {
        return WHS.totalGoodsList();
    }
}
