package com.ruoyi.work.controller.api;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.framework.config.WxMaConfiguration;
import com.ruoyi.framework.jwt.utils.JwtUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.system.service.ISysUserService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author SJY
 * @description 用户获取token，根据具体需求修改
 * @date 2021/9/10
 */
@Controller
@RequestMapping("/jwt/login")
public class LoginAPIController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    /**
     * 微信用户登录
     */
    @GetMapping("/miniapp/{appid}")
    public AjaxResult login(@PathVariable String appid, String code) {
        if (StringUtils.isBlank(code)) {
            return AjaxResult.error("empty jscode");
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            //生成 3rd_session (skey)
            String openid = DigestUtils.md5Hex(session.getOpenid());
            String skey = JwtUtils.createToken(openid);

            return AjaxResult.success("ok", skey);
        } catch (WxErrorException e) {
            return AjaxResult.error(e.toString());
        }
    }

    @PostMapping("/test")
    @ResponseBody
    public AjaxResult jwtLogin(String username, String password)
    {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            return AjaxResult.error("账号和密码不能为空!");
        }

        SysUser user = userService.selectUserByLoginName(username);
        if (user == null)
        {
            return AjaxResult.error("用户不存在/密码错误!");
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            return AjaxResult.error("对不起，您的账号已被删除!");
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            return AjaxResult.error("用户已封禁，请联系管理员!");
        }

        if (!passwordService.matches(user, password))
        {
            return AjaxResult.error("用户不存在/密码错误!");
        }

        String token = JwtUtils.createToken(user.getUserId().toString());
        return AjaxResult.success("登录成功,请妥善保管您的token信息").put("token",token);
    }
}
