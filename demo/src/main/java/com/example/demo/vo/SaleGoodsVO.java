package com.example.demo.vo;

import com.example.demo.entity.Goods;

import lombok.Data;

@Data
public class SaleGoodsVO {
    private Integer goodsId;

    private String goodsName;

    private int quantityInWh;

    private Double priceRetail;

    public SaleGoodsVO(Goods goods, int num) {
        this.goodsId = goods.getGoodsId();
        this.goodsName = goods.getGoodsName();
        this.priceRetail = goods.getPriceRetail();
        this.quantityInWh = num;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getQuantityInWh() {
        return quantityInWh;
    }

    public void setQuantityInWh(int quantityInWh) {
        this.quantityInWh = quantityInWh;
    }

    public Double getPriceRetail() {
        return priceRetail;
    }

    public void setPriceRetail(Double priceRetail) {
        this.priceRetail = priceRetail;
    }
}
