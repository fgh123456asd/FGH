package com.ruoyi.common.core.domain;

import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author SJY
 * @description
 * @date 2021/11/23
 */
@Data
@ApiModel(value = "Result", description = "通用返回值")
public class Result<T> implements Serializable {
    @ApiModelProperty("状态码，0：成功，500：失败")
    private int code;
    @ApiModelProperty("提示信息")
    private String msg;
    @ApiModelProperty("数据")
    private T data;

    public Result(){

    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg 返回内容
     */
    public Result(AjaxResult.Type type, String msg)
    {
        this.code = type.value();
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg 返回内容
     * @param data 数据对象
     */
    public Result(AjaxResult.Type type, String msg, T data)
    {
        this.code = type.value();
        this.msg = msg;
        if (StringUtils.isNotNull(data))
        {
            this.data = data;
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> Result<T> success()
    {
        return Result.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static <T> Result<T> success(T data)
    {
        return Result.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static <T> Result<T> success(String msg)
    {
        return Result.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> Result<T> success(String msg, T data)
    {
        return new Result(AjaxResult.Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> Result<T> warn(String msg)
    {
        return Result.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> Result<T> warn(String msg, Object data)
    {
        return new Result(AjaxResult.Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static <T> Result<T> error()
    {
        return Result.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> Result<T> error(String msg)
    {
        return Result.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> Result<T> error(String msg, T data)
    {
        return new Result(AjaxResult.Type.ERROR, msg, data);
    }
}
