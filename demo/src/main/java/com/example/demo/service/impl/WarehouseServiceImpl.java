package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Warehouse;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.WarehouseMapper;
import com.example.demo.service.WarehouseService;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.SaleGoodsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {

    @Autowired
    private WarehouseMapper WHM;

    @Autowired
    private GoodsMapper GM;

    @Override
    public DataVO<Warehouse> getwarehouseList() {
        DataVO<Warehouse> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");

        List<Warehouse> warehouseList = WHM.selectList(null);

        dataVO.setCount(warehouseList.size());
        dataVO.setData(warehouseList);
        return dataVO;
    }

    @Override
    public DataVO<SaleGoodsVO> getGoodsListById(Integer warehouseId) {
        DataVO<SaleGoodsVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");

        Warehouse wh = WHM.selectById(warehouseId);
        HashMap<Integer, Integer> goodslistmap = new HashMap<>();
        String[] goodsliststr = wh.getGoodsList().split("-");
        int lengthoflist = (goodsliststr.length) / 2;

        for (int i = 0; i < lengthoflist; i++) {
            int key = Integer.parseInt(goodsliststr[2 * i]);
            int value = Integer.parseInt(goodsliststr[2 * i + 1]);
            goodslistmap.put(key, value);
        }

        List<Goods> goodsList = GM.selectList(null);
        List<SaleGoodsVO> goodsVOList = new ArrayList<>();
        for (Goods goods : goodsList) {
            int goodsId = goods.getGoodsId();
            if (!goodslistmap.containsKey(goodsId))
                continue;
            SaleGoodsVO goodsVO = new SaleGoodsVO(goods, goodslistmap.get(goodsId));
            goodsVOList.add(goodsVO);
        }
        dataVO.setCount(goodsVOList.size());
        dataVO.setData(goodsVOList);
        return dataVO;
    }

    @Override
    public boolean addWarehouse() {
        Warehouse wh = new Warehouse();
        wh.setGoodsList("");
        BigDecimal bd = new BigDecimal(0);
        wh.setNum(bd);
        WHM.insert(wh);
        return true;
    }

    @Override
    public boolean deleteWarehouse(Integer warehouseId) {
        WHM.deleteById(warehouseId);
        return true;
    }

    @Override
    public boolean stock(String stock_list) {
        String[] stockList = stock_list.split("-");
        int len = stockList.length / 3;
        HashMap<String, HashMap<String, String>> stockmap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (!stockmap.containsKey(stockList[3 * i])) {
                stockmap.put(stockList[3 * i], new HashMap<>());
                stockmap.get(stockList[3 * i]).put(stockList[i * 3 + 1], stockList[i * 3 + 2]);
            } else {
                stockmap.put(stockList[3 * i], new HashMap<>());
                if (stockmap.get(stockList[3 * i]).containsKey(stockList[i * 3 + 1])) {
                    int newint = (Integer.parseInt(stockList[i * 3 + 2])
                            + Integer.parseInt(stockmap.get(stockList[3 * i]).get(stockList[3 * 2])));
                    stockmap.get(stockList[3 * i]).replace(stockList[3 * i + 1], String.valueOf(newint));
                } else
                    stockmap.get(stockList[3 * i]).put(stockList[i * 3 + 1], stockList[i * 3 + 2]);
            }
        }

        for (String key : stockmap.keySet()) {
            int whid = Integer.parseInt(key);
            Warehouse wh = WHM.selectById(whid);
            String whlist = wh.getGoodsList();
            String[] temp = whlist.split("-");
            String[][] wh_list = new String[temp.length / 2][2];
            for (int i = 0; i < temp.length / 2; i++) {
                wh_list[i][0] = temp[2 * i];
                wh_list[i][1] = temp[2 * i + 1];
            }
            int lenoflist = wh_list.length;
            HashMap<String, String> value = new HashMap<>(stockmap.get(key));
            for (int i = 0; i < lenoflist; i++) {
                if (value.containsKey(wh_list[i][0])) {
                    int newint = Integer.parseInt(wh_list[i][1]) + Integer.parseInt(value.get(wh_list[i][0]));
                    value.replace(wh_list[i][0], String.valueOf(newint));
                } else {
                    value.put(wh_list[i][0], wh_list[i][1]);
                }
            }
            String newstr = new String();
            int j = 0;
            int totalnum = 0;
            for (String keys : value.keySet()) {
                if (j != 0)
                    newstr += "-";
                newstr += keys + "-" + value.get(keys);
                totalnum += Integer.parseInt(value.get(keys));
                j++;
            }
            wh.setGoodsList(newstr);
            wh.setNum(new BigDecimal(totalnum));
            WHM.updateById(wh);
        }
        return true;
    }

    @Override
    public boolean transfer(Integer goodsId, Integer src_whId, Integer dest_whId, Integer num) {
        Warehouse srcWH = WHM.selectById(src_whId);
        Warehouse destWH = WHM.selectById(dest_whId);
        String[] srcstr = srcWH.getGoodsList().split("-");
        String[] deststr = destWH.getGoodsList().split("-");
        int srclen = srcstr.length / 2;
        int destlen = deststr.length / 2;
        boolean mark = true;
        for (int i = 0; i < srclen; i++) {
            if (srcstr[2 * i].equals(String.valueOf(goodsId))) {
                if (Integer.parseInt(srcstr[2 * i + 1]) < num)
                    return false;
                srcstr[2 * i + 1] = String.valueOf(Integer.parseInt(srcstr[2 * i + 1]) - num);
                mark = false;
            }
        }
        if (mark)
            return false;
        mark = true;
        srcWH.setNum(srcWH.getNum().subtract(new BigDecimal(num)));
        destWH.setNum(srcWH.getNum().add(new BigDecimal(num)));

        for (int i = 0; i < destlen; i++) {
            if (deststr[2 * i].equals(String.valueOf(goodsId))) {
                deststr[2 * i + 1] = String.valueOf(Integer.parseInt(deststr[2 * i + 1]) + num);
                mark = false;
            }
        }
        if (mark)
            destWH.setGoodsList(destWH.getGoodsList() + "-" + goodsId + "-" + num);
        else {
            String Deststr = new String();
            for (int i = 0; i < 2 * destlen; i++) {
                if (i != 0)
                    Deststr += "-";
                Deststr += deststr[i];
            }
            destWH.setGoodsList(Deststr);
        }

        String Srcstr = new String();
        for (int i = 0; i < 2 * srclen; i++) {
            if (i != 0)
                Srcstr += "-";
            Srcstr += srcstr[i];
        }
        srcWH.setGoodsList(Srcstr);

        WHM.updateById(srcWH);
        WHM.updateById(destWH);

        return true;
    }

}
