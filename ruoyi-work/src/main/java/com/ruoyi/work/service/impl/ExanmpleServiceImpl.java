package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.ExanmpleMapper;
import com.ruoyi.work.domain.Exanmple;
import com.ruoyi.work.service.IExanmpleService;

/**
 * 案例Service业务层处理
 * 
 * @author SJY
 * @date 2023-12-28
 */
@Service
public class ExanmpleServiceImpl extends ServiceImpl<ExanmpleMapper, Exanmple> implements IExanmpleService
{
    private static final Logger log = LoggerFactory.getLogger(ExanmpleServiceImpl.class);
    /**
     * 查询案例列表
     * 
     * @param exanmple 案例
     * @return 案例
     */
    @Override
    public List<Exanmple> selectExanmpleList(Exanmple exanmple)
    {
        LambdaQueryWrapper<Exanmple> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(exanmple.getCaseName())){
            lqw.like(Exanmple::getCaseName ,exanmple.getCaseName());
        }
        if (StringUtils.isNotBlank(exanmple.getContent())){
            lqw.like(Exanmple::getContent ,exanmple.getContent());
        }
        if (exanmple.getTime() != null){
            lqw.like(Exanmple::getTime ,exanmple.getTime());
        }
        if (exanmple.getStatus() != null){
            lqw.eq(Exanmple::getStatus ,exanmple.getStatus());
        }
        return this.list(lqw);
    }
}
