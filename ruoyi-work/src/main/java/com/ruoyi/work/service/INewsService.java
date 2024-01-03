package com.ruoyi.work.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.work.domain.New1;
import com.ruoyi.work.domain.News;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 新闻Service接口
 * 
 * @author SJY
 * @date 2023-12-28
 */
public interface INewsService extends IService<News> {
    /**
     * 查询新闻列表
     * 
     * @param news 新闻
     * @return 新闻集合
     */
    IPage<News> selectNewsPage (IPage<News> page, News news);
    List<News> selectNewsList(News news);
}
