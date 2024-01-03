package com.ruoyi.work.service;

import java.util.List;
import com.ruoyi.work.domain.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 导师Service接口
 * 
 * @author SJY
 * @date 2023-12-28
 */
public interface ITeacherService extends IService<Teacher> {
    /**
     * 查询导师列表
     * 
     * @param teacher 导师
     * @return 导师集合
     */
    List<Teacher> selectTeacherList(Teacher teacher);
}
