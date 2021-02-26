package com.example.demo.entity;

import java.math.BigDecimal;

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
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "warehouse_id", type = IdType.AUTO)
    private Integer warehouseId;

    private String goodsList;

    private BigDecimal num;

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public String getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(String goodsList) {
        this.goodsList = goodsList;
    }

}
