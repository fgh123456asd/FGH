package com.ruoyi.work.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.work.domain.New1;
import com.ruoyi.work.domain.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 新闻Mapper接口
 * 
 * @author SJY
 * @date 2023-12-28
 */
public interface NewsMapper extends BaseMapper<News>
{
    IPage<News> selectNewsPage (IPage<News> page, @Param("news") News news);
}
