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
import com.ruoyi.work.mapper.EmpMapper;
import com.ruoyi.work.domain.Emp;
import com.ruoyi.work.service.IEmpService;

/**
 * 员工Service业务层处理
 * 
 * @author SJY
 * @date 2023-12-28
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {
    private static final Logger log = LoggerFactory.getLogger(EmpServiceImpl.class);
    /**
     * 查询员工列表
     * 
     * @param emp 员工
     * @return 员工
     */
    public IPage<Emp> selectEmpPage(IPage<Emp> page, Emp emp){
        return this.baseMapper.selectEmpPage(page,emp);
    }

    @Override
    public List<Emp> selectEmpList(Emp emp)
    {
        LambdaQueryWrapper<Emp> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(emp.getName())){
            lqw.like(Emp::getName ,emp.getName());
        }
        if (StringUtils.isNotBlank(emp.getPotos())){
            lqw.eq(Emp::getPotos ,emp.getPotos());
        }
        if (emp.getStatus() != null){
            lqw.eq(Emp::getStatus ,emp.getStatus());
        }
        return this.list(lqw);
    }
}
