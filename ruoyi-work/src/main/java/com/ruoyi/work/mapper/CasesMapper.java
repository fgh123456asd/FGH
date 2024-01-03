package com.ruoyi.work.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.work.domain.Cases;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.work.domain.New1;
import org.apache.ibatis.annotations.Param;

/**
 * 案例1Mapper接口
 * 
 * @author SJY
 * @date 2023-12-31
 */
public interface CasesMapper extends BaseMapper<Cases> {
    IPage<Cases> selectCasesPage (IPage<Cases> page, @Param("cases") Cases cases);

}
