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
import com.ruoyi.work.domain.Exanmple;
import com.ruoyi.work.service.IExanmpleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 案例Controller
 * 
 * @author SJY
 * @date 2023-12-28
 */
@Controller
@RequestMapping("/work/exanmple")
public class ExanmpleController extends BaseController
{
    private String prefix = "work/exanmple";

    @Autowired
    private IExanmpleService exanmpleService;

    @RequiresPermissions("work:exanmple:view")
    @GetMapping()
    public String exanmple()
    {
        return prefix + "/exanmple";
    }

    /**
     * 查询案例列表
     */
    @RequiresPermissions("work:exanmple:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Exanmple exanmple)
    {
        startPage();
        List<Exanmple> list = exanmpleService.selectExanmpleList(exanmple);
        return getDataTable(list);
    }

    /**
     * 导出案例列表
     */
    @RequiresPermissions("work:exanmple:export")
    @Log(title = "案例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Exanmple exanmple)
    {
        List<Exanmple> list = exanmpleService.selectExanmpleList(exanmple);
        ExcelUtil<Exanmple> util = new ExcelUtil<Exanmple>(Exanmple.class);
        return util.exportExcel(list, "exanmple");
    }

    /**
     * 新增案例
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存案例
     */
    @RequiresPermissions("work:exanmple:add")
    @Log(title = "案例", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Exanmple exanmple)
    {
        return toAjax(exanmpleService.save(exanmple));
    }

    /**
     * 修改案例
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Exanmple exanmple = exanmpleService.getById(id);
        mmap.put("exanmple", exanmple);
        return prefix + "/edit";
    }

    /**
     * 修改保存案例
     */
    @RequiresPermissions("work:exanmple:edit")
    @Log(title = "案例", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Exanmple exanmple)
    {
        return toAjax(exanmpleService.updateById(exanmple));
    }

    /**
     * 删除案例
     */
    @RequiresPermissions("work:exanmple:remove")
    @Log(title = "案例", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(exanmpleService.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
