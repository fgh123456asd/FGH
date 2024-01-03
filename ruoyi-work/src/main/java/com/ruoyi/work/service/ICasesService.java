package com.ruoyi.work.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.work.domain.Cases;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.work.domain.New1;

/**
 * 案例1Service接口
 * 
 * @author SJY
 * @date 2023-12-31
 */
public interface ICasesService extends IService<Cases> {
    /**
     * 查询案例1列表
     * 
     * @param cases 案例1
     * @return 案例1集合
     */
    IPage<Cases> selectCasesPage (IPage<Cases> page, Cases cases);
    List<Cases> selectCasesList(Cases cases);
}
