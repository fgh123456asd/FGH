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
import com.ruoyi.work.domain.Logo;
import com.ruoyi.work.service.ILogoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * logo图Controller
 * 
 * @author SJY
 * @date 2023-12-28
 */
@Controller
@RequestMapping("/work/logo")
public class LogoController extends BaseController
{
    private String prefix = "work/logo";

    @Autowired
    private ILogoService logoService;

    @RequiresPermissions("work:logo:view")
    @GetMapping()
    public String logo()
    {
        return prefix + "/logo";
    }

    /**
     * 查询logo图列表
     */
    @RequiresPermissions("work:logo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Logo logo)
    {
        startPage();
        List<Logo> list = logoService.selectLogoList(logo);
        return getDataTable(list);
    }

    /**
     * 导出logo图列表
     */
    @RequiresPermissions("work:logo:export")
    @Log(title = "logo图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Logo logo)
    {
        List<Logo> list = logoService.selectLogoList(logo);
        ExcelUtil<Logo> util = new ExcelUtil<Logo>(Logo.class);
        return util.exportExcel(list, "logo");
    }

    /**
     * 新增logo图
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存logo图
     */
    @RequiresPermissions("work:logo:add")
    @Log(title = "logo图", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Logo logo)
    {
        return toAjax(logoService.save(logo));
    }

    /**
     * 修改logo图
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Logo logo = logoService.getById(id);
        mmap.put("logo", logo);
        return prefix + "/edit";
    }

    /**
     * 修改保存logo图
     */
    @RequiresPermissions("work:logo:edit")
    @Log(title = "logo图", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Logo logo)
    {
        return toAjax(logoService.updateById(logo));
    }

    /**
     * 删除logo图
     */
    @RequiresPermissions("work:logo:remove")
    @Log(title = "logo图", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(logoService.removeByIds(Arrays.asList(Convert.toLongArray(ids))));
    }
}
