package com.example.demo.controller;

import com.example.demo.service.GoodsService;
import com.example.demo.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/goodsList")
    @ResponseBody
    public DataVO findDataWithoutPriceIn(Integer page, Integer limit) {
        return goodsService.findDataWithoutPriceIn(page, limit);
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public boolean addGoods(String goodsName, Double priceIn, Double priceWholesale, Double priceRetail) {
        return goodsService.addGoods(goodsName, priceIn, priceWholesale, priceRetail);
    }

    @RequestMapping(value = "/searchGoods")
    @ResponseBody
    public DataVO searchDataWithoutPriceIn(String goodsName, Integer page, Integer limit) {
        return goodsService.searchDataWithoutPriceIn(goodsName, page, limit);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public boolean deleteGoods(Integer goodsId) {
        return goodsService.deleteGoods(goodsId);
    }

}
