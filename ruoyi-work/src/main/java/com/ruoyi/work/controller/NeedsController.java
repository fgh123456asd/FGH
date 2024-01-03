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
import com.ruoyi.work.domain.Needs;
import com.ruoyi.work.service.INeedsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 需求Controller
 * 
 * @author SJY
 * @date 2024-01-02
 */
@Controller
@RequestMapping("/work/needs")
public class NeedsController extends BaseController
{
    private String prefix = "work/needs";

    @Autowired
    private INeedsService needsService;

    @RequiresPermissions("work:needs:view")
    @GetMapping()
    public String needs()
    {
        return prefix + "/needs";
    }

    /**
     * 查询需求列表
     */
    @RequiresPermissions("work:needs:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Needs needs)
    {
        startPage();
        List<Needs> list = needsService.selectNeedsList(needs);
        return getDataTable(list);
    }

    /**
     * 导出需求列表
     */
    @RequiresPermissions("work:needs:export")
    @Log(title = "需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Needs needs)
    {
        List<Needs> list = needsService.selectNeedsList(needs);
        ExcelUtil<Needs> util = new ExcelUtil<Needs>(Needs.class);
        return util.exportExcel(list, "needs");
    }

    /**
     * 新增需求
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存需求
     */
    @RequiresPermissions("work:needs:add")
    @Log(title = "需求", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Needs needs)
    {
        return toAjax(needsService.save(needs));
    }

    /**
     * 修改需求
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Needs needs = needsService.getById(id);
        mmap.put("needs", needs);
        return prefix + "/edit";
    }

    /**
     * 修改保存需求
     */
    @RequiresPermissions("work:needs:edit")
    @Log(title = "需求", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Needs needs)
    {
        return toAjax(needsService.updateById(needs));
    }

    /**
     * 删除需求
     */
    @RequiresPermissions("work:needs:remove")
    @Log(title = "需求", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(needsService.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
