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
import com.ruoyi.work.mapper.CasesMapper;
import com.ruoyi.work.domain.Cases;
import com.ruoyi.work.service.ICasesService;

/**
 * 案例1Service业务层处理
 * 
 * @author SJY
 * @date 2023-12-31
 */
@Service
public class CasesServiceImpl extends ServiceImpl<CasesMapper, Cases> implements ICasesService {

    public IPage<Cases> selectCasesPage (IPage<Cases> page, Cases cases){
        return this.baseMapper.selectCasesPage(page,cases);
    }

    private static final Logger log = LoggerFactory.getLogger(CasesServiceImpl.class);
    /**
     * 查询案例1列表
     * 
     * @param cases 案例1
     * @return 案例1
     */
    @Override
    public List<Cases> selectCasesList(Cases cases)
    {
        LambdaQueryWrapper<Cases> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(cases.getTitle())){
            lqw.like(Cases::getTitle ,cases.getTitle());
        }
        if (cases.getCreatetime() != null){
            lqw.eq(Cases::getCreatetime ,cases.getCreatetime());
        }
        if (cases.getStatus() != null){
            lqw.eq(Cases::getStatus ,cases.getStatus());
        }
        return this.list(lqw);
    }
}
