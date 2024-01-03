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
import com.ruoyi.work.domain.New1;
import com.ruoyi.work.service.INew1Service;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新闻动态Controller
 * 
 * @author SJY
 * @date 2023-12-31
 */
@Controller
@RequestMapping("/work/new1")
public class New1Controller extends BaseController
{
    private String prefix = "work/new1";

    @Autowired
    private INew1Service new1Service;

    @RequiresPermissions("work:new1:view")
    @GetMapping()
    public String new1()
    {
        return prefix + "/new1";
    }

    /**
     * 查询新闻动态列表
     */
    @RequiresPermissions("work:new1:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(New1 new1)
    {
        startPage();
        List<New1> list = new1Service.selectNew1List(new1);
        return getDataTable(list);
    }

    /**
     * 导出新闻动态列表
     */
    @RequiresPermissions("work:new1:export")
    @Log(title = "新闻动态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(New1 new1)
    {
        List<New1> list = new1Service.selectNew1List(new1);
        ExcelUtil<New1> util = new ExcelUtil<New1>(New1.class);
        return util.exportExcel(list, "new1");
    }

    /**
     * 新增新闻动态
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存新闻动态
     */
    @RequiresPermissions("work:new1:add")
    @Log(title = "新闻动态", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(New1 new1)
    {
        return toAjax(new1Service.save(new1));
    }

    /**
     * 修改新闻动态
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        New1 new1 = new1Service.getById(id);
        mmap.put("new1", new1);
        return prefix + "/edit";
    }

    /**
     * 修改保存新闻动态
     */
    @RequiresPermissions("work:new1:edit")
    @Log(title = "新闻动态", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(New1 new1)
    {
        return toAjax(new1Service.updateById(new1));
    }

    /**
     * 删除新闻动态
     */
    @RequiresPermissions("work:new1:remove")
    @Log(title = "新闻动态", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(new1Service.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
