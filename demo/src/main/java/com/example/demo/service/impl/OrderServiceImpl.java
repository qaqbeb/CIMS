package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Goods;
import com.example.demo.entity.MyOrder;
import com.example.demo.entity.Warehouse;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.WarehouseMapper;
import com.example.demo.service.OrderService;
import com.example.demo.tools.parseString;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.vo.GoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, MyOrder> implements OrderService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrderMapper OM;

    @Autowired
    private WarehouseMapper WHM;

    @Autowired
    private CustomerMapper CM;

    @Override
    public List<GoodsVO> showGoods() {
        List<Goods> goodsList = goodsMapper.selectList(null);
        List<GoodsVO> goodsVOList = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsVO goodsVO = new GoodsVO();
            BeanUtils.copyProperties(goods, goodsVO);
            goodsVOList.add(goodsVO);
        }
        return goodsVOList;
    }

    @Override
    public boolean addOrder(String goods_list, Integer warehouse_id, Integer customer_id) {
        Customer customer = CM.selectById(customer_id);
        int mode = customer.getType();

        MyOrder order = new MyOrder();
        order.setCustomerId(customer_id);
        order.setWarehouseId(warehouse_id);
        Double price = 0.0;

        String[] goodslist = goods_list.split("-");
        int lengthoflist = (goodslist.length) / 2;
        HashMap<Integer, Integer> goodsmap = new HashMap<>();
        for (int i = 0; i < lengthoflist; i++) {
            int key = Integer.parseInt(goodslist[2 * i]);
            int value = Integer.parseInt(goodslist[2 * i + 1]);
            goodsmap.put(key, value);
        }

        List<Goods> goodsList = goodsMapper.selectList(null);
        for (Goods goods : goodsList) {
            if (goodsmap.containsKey(goods.getGoodsId())) {
                if (mode == 1)
                    price += goods.getPriceRetail() * goodsmap.get(goods.getGoodsId());
                if (mode == 2)
                    price += goods.getPriceWholesale() * goodsmap.get(goods.getGoodsId());
            }
        }
        order.setPrice(price);
        if (mode == 2)
            // mode = 2时为批发模式
            order.setStatus(0);
        if (mode == 1)
            // mode = 1为零售模式
            order.setStatus(1);
        order.setCreateTime((new Date()).toString());
        order.setGoodsList(goods_list);

        int i = OM.insert(order);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<MyOrder> getUncheckList() {
        List<MyOrder> myOrderList = OM.selectList(null);
        List<MyOrder> newOrderList = new ArrayList<>();
        for (MyOrder MO : myOrderList) {
            if (MO.getStatus() == 0 || MO.getStatus() == 1) {
                newOrderList.add(MO);
            }
        }
        return newOrderList;
    }

    @Override
    public List<MyOrder> getCheckedList() {
        List<MyOrder> myOrderList = OM.selectList(null);
        List<MyOrder> newOrderList = new ArrayList<>();
        for (MyOrder MO : myOrderList) {
            if (MO.getStatus() == 2 || MO.getStatus() == 3) {
                newOrderList.add(MO);
            }
        }
        return newOrderList;
    }

    @Override
    public List<MyOrder> getCheckedRetailedList() {
        List<MyOrder> myOrderList = OM.selectList(null);
        List<MyOrder> newOrderList = new ArrayList<>();
        for (MyOrder MO : myOrderList) {
            if (MO.getStatus() == 3) {
                newOrderList.add(MO);
            }
        }
        return newOrderList;
    }

    @Override
    public boolean checkOrder(List<MyOrder> orderList) {
        for (MyOrder MO : orderList) {
            MO.setStatus(MO.getStatus() + 2);
            OM.updateById(MO);
            Warehouse wh = WHM.selectById(MO.getWarehouseId());
            String[] goodsList = MO.getGoodsList().split("-");
            HashMap<String, String> goodsListMap = new HashMap<>();
            int len = goodsList.length / 2;
            for (int i = 0; i < len; i++) {
                goodsListMap.put(goodsList[2 * i], goodsList[2 * i + 1]);
            }
            String whlist = wh.getGoodsList();
            String[] temp = whlist.split("-");
            String[][] wh_list = new String[temp.length / 2][2];
            for (int i = 0; i < temp.length / 2; i++) {
                wh_list[i][0] = temp[2 * i];
                wh_list[i][1] = temp[2 * i + 1];
            }
            int lenoflist = wh_list.length;
            String newstr = new String();
            for (int i = 0; i < lenoflist; i++) {
                if (goodsListMap.containsKey(wh_list[i][0])) {
                    wh_list[i][1] = String.valueOf(
                            Integer.parseInt(wh_list[i][1]) - Integer.parseInt(goodsListMap.get(wh_list[i][0])));
                }
                if (i != 0)
                    newstr += "-";
                newstr += wh_list[i][0] + "-" + wh_list[i][1];
            }
            int ordernum = 0;
            for (String key : goodsListMap.keySet()) {
                ordernum += Integer.parseInt(goodsListMap.get(key));
            }
            BigDecimal bd = new BigDecimal(ordernum);
            wh.setNum(wh.getNum().subtract(bd));
            wh.setGoodsList(newstr);
            WHM.updateById(wh);
        }
        return true;
    }

    @Override
    public boolean checkLastestOrder() {
        List<MyOrder> myOrderList = new ArrayList<>(OM.selectList(null));
        int maxid = 0;

        List<MyOrder> singalOrderList = new ArrayList<>();

        for (MyOrder myOrder : myOrderList) {
            if (myOrder.getStatus() >= 2)
                continue;
            if (maxid < myOrder.getOrderId()) {
                maxid = myOrder.getOrderId();
                singalOrderList.clear();
                singalOrderList.add(myOrder);
            }
        }

        return checkOrder(singalOrderList);
    }

    @Override
    public List<MyOrder> getorderList() {
        return new ArrayList<>(OM.selectList(null));
    }

    @Override
    public double calculatProfit() {
        double inprice = 0;
        double saleprice = 0;
        String str = new String();
        List<MyOrder> orderlist = new ArrayList<>(OM.selectList(null));
        for (MyOrder order : orderlist) {
            if (order.getStatus() >= 2) {
                saleprice += order.getPrice();
                if (orderlist.indexOf(order) != 0)
                    str += "-";
                str += order.getGoodsList();
            }
        }

        HashMap<String, Double> goodsmap = new HashMap<>();
        List<Goods> goodsList = goodsMapper.selectList(null);

        for (Goods goods : goodsList) {
            goodsmap.put(String.valueOf(goods.getGoodsId()), goods.getPriceIn());
            // 获取map信息
        }

        String[] totalgoodslist = str.split("-");
        int len = totalgoodslist.length / 2;
        for (int i = 0; i < len; i++) {
            if (goodsmap.containsKey(totalgoodslist[2 * i])) {
                // 虽然不太可能出错，但还是加上判断以防万一
                inprice += goodsmap.get(totalgoodslist[2 * i]) * Integer.parseInt(totalgoodslist[2 * i + 1]);
            }
        }
        return saleprice - inprice;
    }

    @Override
    public boolean deleteOrder(Integer orderId) {
        OM.deleteById(orderId);
        return true;
    }

    @Override
    public boolean refundOrder(Integer orderId) {
        MyOrder MYO = OM.selectById(orderId);
        Warehouse selectedWH = WHM.selectById(MYO.getWarehouseId());
        String str = parseString.mergeStatment(MYO.getGoodsList(), selectedWH.getGoodsList());
        selectedWH.setGoodsList(str);
        WHM.updateById(selectedWH);
        OM.deleteById(orderId);
        return true;
    }

}
