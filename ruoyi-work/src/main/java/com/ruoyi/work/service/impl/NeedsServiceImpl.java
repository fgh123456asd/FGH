package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.NeedsMapper;
import com.ruoyi.work.domain.Needs;
import com.ruoyi.work.service.INeedsService;

/**
 * 需求Service业务层处理
 * 
 * @author SJY
 * @date 2024-01-02
 */
@Service
public class NeedsServiceImpl extends ServiceImpl<NeedsMapper, Needs> implements INeedsService
{
    private static final Logger log = LoggerFactory.getLogger(NeedsServiceImpl.class);
    /**
     * 查询需求列表
     * 
     * @param needs 需求
     * @return 需求
     */
    @Override
    public List<Needs> selectNeedsList(Needs needs)
    {
        LambdaQueryWrapper<Needs> lqw = Wrappers.lambdaQuery();
        return this.list(lqw);
    }
}
