package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.LogoMapper;
import com.ruoyi.work.domain.Logo;
import com.ruoyi.work.service.ILogoService;

/**
 * logo图Service业务层处理
 * 
 * @author SJY
 * @date 2023-12-28
 */
@Service
public class LogoServiceImpl extends ServiceImpl<LogoMapper, Logo> implements ILogoService
{
    private static final Logger log = LoggerFactory.getLogger(LogoServiceImpl.class);
    /**
     * 查询logo图列表
     * 
     * @param logo logo图
     * @return logo图
     */
    @Override
    public List<Logo> selectLogoList(Logo logo)
    {
        LambdaQueryWrapper<Logo> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(logo.getName())){
            lqw.like(Logo::getName ,logo.getName());
        }
        if (StringUtils.isNotBlank(logo.getPic())){
            lqw.eq(Logo::getPic ,logo.getPic());
        }
        return this.list(lqw);
    }
}
