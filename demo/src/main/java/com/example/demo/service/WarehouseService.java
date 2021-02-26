package com.example.demo.service;

import com.example.demo.entity.Warehouse;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.SaleGoodsVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 7264
 * @since 2020-12-01
 */
public interface WarehouseService extends IService<Warehouse> {
    public DataVO<Warehouse> getwarehouseList();

    public DataVO<SaleGoodsVO> getGoodsListById(Integer warehouseId);

    public boolean addWarehouse();

    public boolean deleteWarehouse(Integer warehouseId);

    public boolean stock(String stock_list);

    public boolean transfer(Integer goodsId, Integer src_whId, Integer dest_whId, Integer num);

    public Integer warehouseTotalNumber();

    public Double warehouseTotalPrice();

	public DataVO<SaleGoodsVO> totalGoodsList();
}
