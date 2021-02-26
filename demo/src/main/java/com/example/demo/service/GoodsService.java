package com.example.demo.service;

import com.example.demo.entity.Goods;
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
public interface GoodsService extends IService<Goods> {
    public DataVO findDataWithoutPriceIn(Integer page, Integer limit);

    public boolean addGoods(String goodsName, Double priceIn, Double priceWholesale, Double priceRetail);

    public DataVO searchDataWithoutPriceIn(String goodsName, Integer page, Integer limit);

    public boolean deleteGoods(Integer GoodsId);
}
