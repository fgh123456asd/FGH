package com.ruoyi.work.controller;

import java.util.List;
import java.util.Arrays;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.work.domain.Teacher;
import com.ruoyi.work.service.ITeacherService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 导师Controller
 * 
 * @author SJY
 * @date 2023-12-28
 */
@Controller
@RequestMapping("/work/teacher")
public class TeacherController extends BaseController
{
    private String prefix = "work/teacher";

    @Autowired
    private ITeacherService teacherService;

    @RequiresPermissions("work:teacher:view")
    @GetMapping()
    public String teacher()
    {
        return prefix + "/teacher";
    }

    /**
     * 查询导师列表
     */
    @RequiresPermissions("work:teacher:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Teacher teacher)
    {
        startPage();
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        return getDataTable(list);
    }

    /**
     * 导出导师列表
     */
    @RequiresPermissions("work:teacher:export")
    @Log(title = "导师", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Teacher teacher)
    {
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        ExcelUtil<Teacher> util = new ExcelUtil<Teacher>(Teacher.class);
        return util.exportExcel(list, "teacher");
    }

    /**
     * 新增导师
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存导师
     */
    @RequiresPermissions("work:teacher:add")
    @Log(title = "导师", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Teacher teacher)
    {
        return toAjax(teacherService.save(teacher));
    }

    /**
     * 修改导师
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Teacher teacher = teacherService.getById(id);
        mmap.put("teacher", teacher);
        return prefix + "/edit";
    }

    /**
     * 修改保存导师
     */
    @RequiresPermissions("work:teacher:edit")
    @Log(title = "导师", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Teacher teacher)
    {
        return toAjax(teacherService.updateById(teacher));
    }

    /**
     * 删除导师
     */
    @RequiresPermissions("work:teacher:remove")
    @Log(title = "导师", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(teacherService.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
