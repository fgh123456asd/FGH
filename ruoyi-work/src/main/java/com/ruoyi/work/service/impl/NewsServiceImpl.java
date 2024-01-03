package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.work.domain.New1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.NewsMapper;
import com.ruoyi.work.domain.News;
import com.ruoyi.work.service.INewsService;

/**
 * 新闻Service业务层处理
 * 
 * @author SJY
 * @date 2023-12-28
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService
{
    public IPage<News> selectNewsPage (IPage<News> page, News news){
        return this.baseMapper.selectNewsPage(page,news);
    }
    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
    /**
     * 查询新闻列表
     * 
     * @param news 新闻
     * @return 新闻
     */
    @Override
    public List<News> selectNewsList(News news)
    {
        LambdaQueryWrapper<News> lqw = Wrappers.lambdaQuery();
        if (news.getStatus() != null){
            lqw.eq(News::getStatus ,news.getStatus());
        }
        if (news.getNewsTiem() != null){
            lqw.eq(News::getNewsTiem ,news.getNewsTiem());
        }
        return this.list(lqw);
    }
}
