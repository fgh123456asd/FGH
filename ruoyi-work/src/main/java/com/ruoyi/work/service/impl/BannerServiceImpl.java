package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.BannerMapper;
import com.ruoyi.work.domain.Banner;
import com.ruoyi.work.service.IBannerService;

/**
 * 轮播图Service业务层处理
 * 
 * @author SJY
 * @date 2023-12-27
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService
{
    private static final Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);
    /**
     * 查询轮播图列表
     * 
     * @param banner 轮播图
     * @return 轮播图
     */
    @Override
    public List<Banner> selectBannerList(Banner banner)
    {
        LambdaQueryWrapper<Banner> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(banner.getTitle())){
            lqw.like(Banner::getTitle ,banner.getTitle());
        }
        if (banner.getStatus() != null){
            lqw.eq(Banner::getStatus ,banner.getStatus());
        }
        return this.list(lqw);//.subList(0,banner.getNum());
    }
}
