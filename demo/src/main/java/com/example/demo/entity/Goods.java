package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    private String goodsName;

    private Double priceIn;

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

    public Double getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(Double priceIn) {
        this.priceIn = priceIn;
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
