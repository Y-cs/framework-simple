package com.yidian.galaxy.web.entity.result;

import com.yidian.galaxy.web.exception.BusinessException;
import com.yidian.galaxy.web.exception.ExceptionWrapper;
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
        OK(1, "success"),
        FAIL(-1, "fail");
        
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
    
    private Result(int code, String msg, T data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    
    /**
     * 不允许外部构造
     *
     * @param resultEnum 结果类型
     * @param msg        信息
     * @param data       数据
     */
    protected Result(ResultEnum resultEnum, String msg, T data) {
        this(resultEnum.code, (msg != null && !msg.isEmpty()) ? msg : resultEnum.msg, data);
    }
    
    /**
     * 成功
     *
     * @param msg  信息
     * @param data 数据
     * @param <T>  数据泛型
     * @return 系统返回
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResultEnum.OK, msg, null);
    }
    
    /**
     * 成功
     *
     * @param data 数据
     * @param <T>  数据泛型
     * @return 系统返回
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.OK, null, data);
    }
    
    /**
     * 成功
     *
     * @param <T> 数据泛型
     * @return 系统返回
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultEnum.OK, null, null);
    }
    
    /**
     * 失败
     *
     * @param msg  信息
     * @param data 数据
     * @param <T>  数据泛型
     * @return 系统返回
     */
    public static <T> Result<T> fail(String msg, T data) {
        return new Result<>(ResultEnum.FAIL, msg, null);
    }
    
    /**
     * 失败
     *
     * @param msg 信息
     * @param <T> 数据泛型
     * @return 系统返回
     */
    public static <T> Result<T> fail(String msg) {
        return new Result<>(ResultEnum.FAIL, msg, null);
    }
    
    /**
     * 失败
     *
     * @param data 数据
     * @param <T>  数据泛型
     * @return 系统返回
     */
    public static <T> Result<T> fail(T data) {
        return new Result<>(ResultEnum.FAIL, null, data);
    }
    
    /**
     * 失败
     *
     * @param <T> 数据泛型
     * @return 系统返回
     */
    public static <T> Result<T> fail() {
        return new Result<>(ResultEnum.FAIL, null, null);
    }
    
    /**
     * 失败
     *
     * @param exception 异常
     * @param msg       信息
     * @param data      数据
     * @param <T>       数据反省
     * @return 系统返回
     */
    public static <T> Result<T> fail(Exception exception, String msg, T data) {
        if (exception == null) {
            return new Result<>(ResultEnum.FAIL, msg, data);
        }
        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            return new Result<>(businessException.getCode(), msg, data);
        }
        return new Result<>(ResultEnum.FAIL, msg, null);
    }
    
    /**
     * 失败
     *
     * @param exception 异常
     * @param data      数据
     * @param <T>       数据反省
     * @return 系统返回
     */
    public static <T> Result<T> fail(Exception exception, T data) {
        return Result.fail(exception, exception.getMessage(), data);
    }
    
    /**
     * 失败
     *
     * @param exception 异常
     * @param msg       信息
     * @param <T>       数据反省
     * @return 系统返回
     */
    public static <T> Result<T> fail(Exception exception, String msg) {
        return Result.fail(exception, msg, null);
    }
    
    /**
     * 失败
     *
     * @param exception 异常
     * @param <T>       数据反省
     * @return 系统返回
     */
    public static <T> Result<T> fail(Exception exception) {
        return Result.fail(exception, exception.getMessage(), null);
    }
    
    /**
     * 失败
     *
     * @param exceptionWrapper 异常
     * @param <T>              数据反省
     * @return 系统返回
     */
    public static <T> Result<T> fail(ExceptionWrapper exceptionWrapper) {
        return Result.fail(new BusinessException(exceptionWrapper), null);
    }
    
}
