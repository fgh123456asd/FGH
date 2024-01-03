package com.ruoyi.work.service;

import java.util.List;
import com.ruoyi.work.domain.Exanmple;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 案例Service接口
 * 
 * @author SJY
 * @date 2023-12-28
 */
public interface IExanmpleService extends IService<Exanmple> {
    /**
     * 查询案例列表
     * 
     * @param exanmple 案例
     * @return 案例集合
     */
    List<Exanmple> selectExanmpleList(Exanmple exanmple);
}
