package com.ruoyi.work.service;

import java.util.List;
import com.ruoyi.work.domain.Logo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * logo图Service接口
 * 
 * @author SJY
 * @date 2023-12-28
 */
public interface ILogoService extends IService<Logo> {
    /**
     * 查询logo图列表
     * 
     * @param logo logo图
     * @return logo图集合
     */
    List<Logo> selectLogoList(Logo logo);
}
