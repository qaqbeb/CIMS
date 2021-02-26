package com.example.demo.vo;

import lombok.Data;

@Data
public class GoodsVO {
    private Integer goodsId;

    private String goodsName;

    private Double priceWholesale;

    private Double priceRetail;

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

    public Double getPriceWholesale() {
        return priceWholesale;
    }

    public void setPriceWholesale(Double priceWholesale) {
        this.priceWholesale = priceWholesale;
    }

    public Double getPriceRetail() {
        return priceRetail;
    }

    public void setPriceRetail(Double priceRetail) {
        this.priceRetail = priceRetail;
    }
}
