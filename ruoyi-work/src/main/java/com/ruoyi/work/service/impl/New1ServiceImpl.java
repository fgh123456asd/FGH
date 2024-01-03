package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.New1Mapper;
import com.ruoyi.work.domain.New1;
import com.ruoyi.work.service.INew1Service;

/**
 * 新闻动态Service业务层处理
 * 
 * @author SJY
 * @date 2023-12-31
 */
@Service
public class New1ServiceImpl extends ServiceImpl<New1Mapper, New1> implements INew1Service {

  public  IPage<New1> selectNew1Page (IPage<New1> page,New1 new1){
      return this.baseMapper.selectNew1Page(page,new1);
  }
    private static final Logger log = LoggerFactory.getLogger(New1ServiceImpl.class);
    /**
     * 查询新闻动态列表
     * 
     * @param new1 新闻动态
     * @return 新闻动态
     */
    @Override
    public List<New1> selectNew1List(New1 new1)
    {
        LambdaQueryWrapper<New1> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(new1.getTitle())){
            lqw.like(New1::getTitle ,new1.getTitle());
        }
        if (new1.getCreatime() != null){
            lqw.eq(New1::getCreatime ,new1.getCreatime());
        }
        if (new1.getStatus() != null){
            lqw.eq(New1::getStatus ,new1.getStatus());
        }
        return this.list(lqw);
    }
}
