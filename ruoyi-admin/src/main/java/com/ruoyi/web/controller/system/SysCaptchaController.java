package com.ruoyi.web.controller.system;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ruoyi.common.core.controller.BaseController;

/**
 * 图片验证码（支持算术形式）
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/captcha")
public class SysCaptchaController extends BaseController
{
    /**
     * 验证码生成
     */
    @GetMapping(value = "/captchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String type = request.getParameter("type");
        if("math".equals(type)){
            ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
            CaptchaUtil.out(captcha,request,response);
        }else if ("gif".equals(type)){
            // 三个参数分别为宽、高、位数
            GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 4);
            // 设置类型：字母数字混合
            gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);
            // 输出验证码
            CaptchaUtil.out(gifCaptcha,request,response);
        }else if("chinese".equals(type)){
            // 中文类型
            ChineseCaptcha captcha = new ChineseCaptcha(130, 48);
            // 输出验证码
            CaptchaUtil.out(captcha,request,response);
        }else {
            CaptchaUtil.out(request, response);
        }

        return null;
    }
}