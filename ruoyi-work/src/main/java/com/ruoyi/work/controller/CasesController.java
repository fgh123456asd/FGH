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
import com.ruoyi.work.domain.Cases;
import com.ruoyi.work.service.ICasesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 案例1Controller
 * 
 * @author SJY
 * @date 2023-12-31
 */
@Controller
@RequestMapping("/work/cases")
public class CasesController extends BaseController
{
    private String prefix = "work/cases";

    @Autowired
    private ICasesService casesService;

    @RequiresPermissions("work:cases:view")
    @GetMapping()
    public String cases()
    {
        return prefix + "/cases";
    }

    /**
     * 查询案例1列表
     */
    @RequiresPermissions("work:cases:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Cases cases)
    {
        startPage();
        List<Cases> list = casesService.selectCasesList(cases);
        return getDataTable(list);
    }

    /**
     * 导出案例1列表
     */
    @RequiresPermissions("work:cases:export")
    @Log(title = "案例1", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Cases cases)
    {
        List<Cases> list = casesService.selectCasesList(cases);
        ExcelUtil<Cases> util = new ExcelUtil<Cases>(Cases.class);
        return util.exportExcel(list, "cases");
    }

    /**
     * 新增案例1
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存案例1
     */
    @RequiresPermissions("work:cases:add")
    @Log(title = "案例1", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Cases cases)
    {
        return toAjax(casesService.save(cases));
    }

    /**
     * 修改案例1
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Cases cases = casesService.getById(id);
        mmap.put("cases", cases);
        return prefix + "/edit";
    }

    /**
     * 修改保存案例1
     */
    @RequiresPermissions("work:cases:edit")
    @Log(title = "案例1", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Cases cases)
    {
        return toAjax(casesService.updateById(cases));
    }

    /**
     * 删除案例1
     */
    @RequiresPermissions("work:cases:remove")
    @Log(title = "案例1", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(casesService.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
