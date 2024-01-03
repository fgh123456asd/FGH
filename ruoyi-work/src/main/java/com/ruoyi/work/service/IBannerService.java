package com.ruoyi.work.service;

import java.util.List;
import com.ruoyi.work.domain.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 轮播图Service接口
 * 
 * @author SJY
 * @date 2023-12-27
 */
public interface IBannerService extends IService<Banner> {
    /**
     * 查询轮播图列表
     * 
     * @param banner 轮播图
     * @return 轮播图集合
     */
    List<Banner> selectBannerList(Banner banner);
}
