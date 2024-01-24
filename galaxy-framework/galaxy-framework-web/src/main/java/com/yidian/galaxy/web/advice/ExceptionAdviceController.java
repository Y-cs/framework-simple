package com.yidian.galaxy.web.advice;

import com.yidian.galaxy.web.entity.result.Result;
import com.yidian.galaxy.web.exception.BusinessException;
import com.yidian.galaxy.web.exception.SystemExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局统一错误处理
 *
 * @author changshuai.yuan create on 2024/1/18 16:34
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdviceController {
    
    /**
     * 参数验证错误处理
     *
     * @param e 错误对象
     * @return 系统返沪
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Result<?>> methodArgumentNotValidException(MethodArgumentNotValidException e,
            WebRequest webRequest) {
        log.warn("接口:{},请求参数错误:", webRequest.getDescription(true), e);
        List<String> errorInformation = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                Result.fail(new BusinessException(SystemExceptionEnum.PARAM_ERROR, errorInformation.toString())),
                HttpStatus.OK);
    }
    
    /**
     * BusinessException错误处理
     *
     * @param e BusinessException
     * @return 系统返回
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Result<?>> businessException(BusinessException e, WebRequest webRequest) {
        log.warn("接口:{},业务异常:{},参数:{}", webRequest.getDescription(true), e, webRequest.getParameterMap());
        return new ResponseEntity<>(Result.fail(e), HttpStatus.OK);
    }
    
    /**
     * 系统异常处理
     *
     * @param e 错误
     * @return 系统返回
     */
    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Result<?>> throwable(Throwable e, WebRequest webRequest) {
        log.error("接口:{},发生错误:", webRequest.getDescription(true), e);
        return new ResponseEntity<>(Result.fail(SystemExceptionEnum.FAIL), HttpStatus.OK);
    }
    
}
