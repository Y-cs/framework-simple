package com.yidian.galaxy.web.entity.result;

import com.yidian.galaxy.web.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 全局统一返回类
 *
 * @author changshuai.yuan create on 2024/1/18 17:06
 */
@Data
public class Result<T> {
    
    protected enum ResultEnum {
        Ok(1, "success"),
        Fail(-1, "fail");
        
        private final int code;
        
        private final String msg;
        
        ResultEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
    
    @Schema(name = "code", description = "code")
    private final int code;
    
    @Schema(name = "code", description = "数据")
    private final T data;
    
    @Schema(name = "code", description = "信息")
    private final String msg;
    
    @Getter(lazy = true)
    @Schema(name = "code", description = "时间戳")
    private final LocalDateTime timestamp = LocalDateTime.now();
    
    protected Result(int code, String msg, T data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    
    /**
     * 不允许外部构造
     *
     * @param resultEnum 结果类型
     * @param data       数据
     */
    protected Result(ResultEnum resultEnum, T data) {
        this(resultEnum.code, resultEnum.msg, data);
    }
    
    /**
     * 成功
     *
     * @param <T> 数据类型
     * @return 系统返回
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultEnum.Ok, null);
    }
    
    /**
     * 成功
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 系统返回
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.Ok, data);
    }
    
    /**
     * 失败
     *
     * @param <T> 数据类型
     * @return 系统返回
     */
    public static <T> Result<T> fail() {
        return new Result<>(ResultEnum.Fail, null);
    }
    
    /**
     * 失败
     *
     * @param exception 错误
     * @param <T>       数据类型
     * @return 系统返回
     */
    public static <T> Result<T> fail(Exception exception) {
        if (exception == null) {
            return new Result<>(ResultEnum.Fail, null);
        }
        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            return new Result<>(businessException.getCode(), businessException.getMsg(), null);
        }
        return new Result<>(ResultEnum.Fail, null);
    }
    
    /**
     * 失败
     *
     * @param exception 错误
     * @param data      数据
     * @param <T>       数据类型
     * @return 系统返回
     */
    public static <T> Result<T> fail(Exception exception, T data) {
        if (exception == null) {
            return new Result<>(ResultEnum.Fail, data);
        }
        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            return new Result<>(businessException.getCode(), businessException.getMsg(), data);
        }
        return new Result<>(ResultEnum.Fail, null);
    }
    
}
