package com.ruoyi.work.controller.api;

import com.ruoyi.common.core.controller.BaseAPIController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.ImageUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SJY
 * @description
 * @date 2021/9/10
 */
@RestController
@RequestMapping("/api")
public class APIController extends BaseAPIController {
    @GetMapping("/test")
    public AjaxResult test()
    {
        return AjaxResult.success("test success");
    }

    @PostMapping(value = { "/upload/{projectPath}", "/upload" })
    @ResponseBody
    public AjaxResult upload(@RequestParam("file") MultipartFile file,
                             @PathVariable(value = "projectPath", required = false) String projectPath){
        String fileName = uploadOpenFile(file,projectPath);

        if(fileName != null){
            return AjaxResult.success("上传图片成功", fileName);
        }
        return error("上传图片失败");
    }

    @GetMapping(value="/deleteImage")
    public AjaxResult deleteImage(String fileName){
        if(ImageUtils.deleteOpenFile(fileName)){
            return AjaxResult.success("删除图片成功", fileName);
        }
        return error("删除图片失败");
    }
}
