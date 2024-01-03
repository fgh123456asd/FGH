package com.ruoyi.work.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.work.domain.Emp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 员工Mapper接口
 * 
 * @author SJY
 * @date 2023-12-28
 */
public interface EmpMapper extends BaseMapper<Emp> {

    //@Select("select * from emp where name like concat('%',#{name},'%') and potos =  #{potos}")
    IPage<Emp> selectEmpPage(IPage<Emp> page, @Param("emp") Emp emp);
}
