package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.ProductMapper;
import com.ruoyi.work.domain.Product;
import com.ruoyi.work.service.IProductService;

/**
 * 产品Service业务层处理
 * 
 * @author SJY
 * @date 2023-12-28
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService
{
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    /**
     * 查询产品列表
     * 
     * @param product 产品
     * @return 产品
     */
    @Override
    public List<Product> selectProductList(Product product)
    {
        LambdaQueryWrapper<Product> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(product.getProName())){
            lqw.like(Product::getProName ,product.getProName());
        }
        if (StringUtils.isNotBlank(product.getContent())){
            lqw.like(Product::getContent ,product.getContent());
        }
        if (product.getStatus() != null){
            lqw.eq(Product::getStatus ,product.getStatus());
        }
        return this.list(lqw);
    }
}
