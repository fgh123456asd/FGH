package com.ruoyi.work.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.work.domain.New1;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 新闻动态Service接口
 * 
 * @author SJY
 * @date 2023-12-31
 */
public interface INew1Service extends IService<New1> {
    /**
     * 查询新闻动态列表
     * 
     * @param new1 新闻动态
     * @return 新闻动态集合
     */



    IPage<New1> selectNew1Page (IPage<New1> page,New1 new1);
    List<New1> selectNew1List(New1 new1);


}
