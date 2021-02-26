package com.example.demo.tools;

import java.util.HashMap;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Warehouse;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.WarehouseMapper;

public class parseString extends ServiceImpl<WarehouseMapper, Warehouse> {

    public static HashMap<Goods, Integer> parseToGoodsList(String goodsStatment, GoodsMapper GM) {
        // 将a-b-c-d格式 字符串转化为商品表
        String goodsList[] = goodsStatment.split("-");
        int len = goodsList.length / 2;

        HashMap<Goods, Integer> goodsMap = new HashMap<>();

        for (int i = 0; i < len; i++) {
            int id = Integer.parseInt(goodsList[2 * i]);
            Goods good = GM.selectById(id);
            int num = Integer.parseInt(goodsList[2 * i + 1]);
            if (goodsMap.containsKey(good)) {
                goodsMap.replace(good, goodsMap.get(good) + num);
            } else
                goodsMap.put(good, num);
        }

        return goodsMap;
    }

    public static HashMap<Goods, Integer> mergeMap(HashMap<Goods, Integer> map1, HashMap<Goods, Integer> map2) {
        // 合并商品表
        HashMap<Goods, Integer> map = new HashMap<>(map1);
        for (Goods good : map2.keySet()) {
            if (map.containsKey(good))
                map.replace(good, map.get(good) + map2.get(good));
            else
                map.put(good, map2.get(good));
        }
        return map;
    }

    public static String mergeStatment(String str1, String str2) {
        HashMap<String, String> statmentMap = new HashMap<>();
        String slist1[] = str1.split("-");
        String slist2[] = str2.split("-");
        int len1 = slist1.length / 2;
        int len2 = slist2.length / 2;
        for (int i = 0; i < len1; i++) {
            String key = slist1[2 * i];
            String value = slist1[2 * i + 1];
            if (statmentMap.containsKey(key)) {
                int num = Integer.parseInt(value);// 这里先等于新的语句中的数值
                num += Integer.parseInt(statmentMap.get(key));
                statmentMap.replace(key, String.valueOf(num));
            } else
                statmentMap.put(key, value);
        }

        for (int i = 0; i < len2; i++) {
            String key = slist2[2 * i];
            String value = slist2[2 * i + 1];
            if (statmentMap.containsKey(key)) {
                int num = Integer.parseInt(value);// 这里先等于新的语句中的数值
                num += Integer.parseInt(statmentMap.get(key));
                statmentMap.replace(key, String.valueOf(num));
            } else
                statmentMap.put(key, value);
        }

        String str = new String();
        boolean noinit = false;
        for (String key : statmentMap.keySet()) {
            if (noinit)
                str += "-";
            else
                noinit = true;
            str += key + "-" + statmentMap.get(key);
        }

        return str;
    }

}
