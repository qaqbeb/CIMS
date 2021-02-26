package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Goods;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.GoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public DataVO findDataWithoutPriceIn(Integer page, Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");

        // 分页查询
        IPage<Goods> goodsIPage = new Page<>(page, limit);
        IPage<Goods> reuslt = goodsMapper.selectPage(goodsIPage, null);

        dataVO.setCount(reuslt.getTotal());
        List<Goods> goodsList = reuslt.getRecords();
        // List<Goods> goodsList = goodsMapper.selectList(null);
        List<GoodsVO> goodsVOList = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsVO goodsVO = new GoodsVO();
            BeanUtils.copyProperties(goods, goodsVO);
            goodsVOList.add(goodsVO);
        }
        dataVO.setData(goodsVOList);
        return dataVO;
    }

    @Override
    public boolean addGoods(String goodsName, Double priceIn, Double priceWholesale, Double priceRetail) {
        Goods goods = new Goods();
        goods.setGoodsName(goodsName);
        goods.setPriceIn(priceIn);
        goods.setPriceWholesale(priceWholesale);
        goods.setPriceRetail(priceRetail);
        int i = goodsMapper.insert(goods);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public DataVO searchDataWithoutPriceIn(String goodsName, Integer page, Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("goods_name", goodsName);
        IPage<Goods> goodsIPage = new Page<>(page, limit);
        IPage<Goods> result = goodsMapper.selectPage(goodsIPage, queryWrapper);
        List<Goods> goodsList = result.getRecords();
        dataVO.setCount(result.getTotal());
        List<GoodsVO> goodsVOList = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsVO goodsVO = new GoodsVO();
            BeanUtils.copyProperties(goods, goodsVO);
            goodsVOList.add(goodsVO);
        }
        dataVO.setData(goodsVOList);
        return dataVO;
    }

    @Override
    public boolean deleteGoods(Integer GoodsId) {
        goodsMapper.deleteById(GoodsId);
        return true;
    }
}
