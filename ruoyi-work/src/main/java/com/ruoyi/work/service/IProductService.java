package com.ruoyi.work.service;

import java.util.List;
import com.ruoyi.work.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 产品Service接口
 * 
 * @author SJY
 * @date 2023-12-28
 */
public interface IProductService extends IService<Product> {
    /**
     * 查询产品列表
     * 
     * @param product 产品
     * @return 产品集合
     */
    List<Product> selectProductList(Product product);
}
