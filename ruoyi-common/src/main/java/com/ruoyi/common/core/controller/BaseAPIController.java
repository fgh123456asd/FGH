package com.ruoyi.common.core.controller;

import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author SJY
 * @description
 * @date 2021/9/10
 */
public class BaseAPIController extends BaseController{
    protected Long getKey(){
        return getUserId();
    }

    protected String uploadOpenFile(MultipartFile file, String project){
        String fileName = null;
        try {
            fileName = FileUploadUtils.uploadOpenFile(file,project);
        }catch (IOException e){
            logger.error(e.getMessage());
        }
        return fileName;
    }

    protected void writeMsg(int status,String msg){
        JSONObject jsonObject = new JSONObject();

        PrintWriter out = null;
        try {
            HttpServletResponse response = getResponse();
            HttpServletRequest request = getRequest();
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            response.setStatus(status);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            jsonObject.put("code", response.getStatus());
            jsonObject.put("msg", msg);

            out = response.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
}
