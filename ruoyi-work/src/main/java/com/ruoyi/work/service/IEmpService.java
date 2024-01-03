package com.ruoyi.work.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.work.domain.Emp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 员工Service接口
 * 
 * @author SJY
 * @date 2023-12-28
 */
public interface IEmpService extends IService<Emp> {
    /**
     * 查询员工列表
     * 
     * @param emp 员工
     * @return 员工集合
     */
    IPage<Emp> selectEmpPage(IPage<Emp> page, Emp emp);
    List<Emp> selectEmpList(Emp emp);
}
