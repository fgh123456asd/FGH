package com.ruoyi.work.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.work.mapper.TeacherMapper;
import com.ruoyi.work.domain.Teacher;
import com.ruoyi.work.service.ITeacherService;

/**
 * 导师Service业务层处理
 * 
 * @author SJY
 * @date 2023-12-28
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService
{
    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);
    /**
     * 查询导师列表
     * 
     * @param teacher 导师
     * @return 导师
     */
    @Override
    public List<Teacher> selectTeacherList(Teacher teacher)
    {
        LambdaQueryWrapper<Teacher> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(teacher.getName())){
            lqw.like(Teacher::getName ,teacher.getName());
        }
        if (StringUtils.isNotBlank(teacher.getPosts())){
            lqw.eq(Teacher::getPosts ,teacher.getPosts());
        }
        if (teacher.getStatus() != null){
            lqw.eq(Teacher::getStatus ,teacher.getStatus());
        }
        return this.list(lqw);
    }
}
