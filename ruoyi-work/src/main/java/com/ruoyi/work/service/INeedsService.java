package com.ruoyi.work.service;

import java.util.List;
import com.ruoyi.work.domain.Needs;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 需求Service接口
 * 
 * @author SJY
 * @date 2024-01-02
 */
public interface INeedsService extends IService<Needs> {
    /**
     * 查询需求列表
     * 
     * @param needs 需求
     * @return 需求集合
     */
    List<Needs> selectNeedsList(Needs needs);
}
