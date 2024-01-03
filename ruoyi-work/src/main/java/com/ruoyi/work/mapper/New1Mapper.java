package com.ruoyi.work.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.work.domain.Emp;
import com.ruoyi.work.domain.New1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 新闻动态Mapper接口
 * 
 * @author SJY
 * @date 2023-12-31
 */
public interface New1Mapper extends BaseMapper<New1> {
    IPage<New1> selectNew1Page (IPage<New1> page,@Param("new1") New1 new1);


}
