package com.yidian.galaxy.app.interceptor;

import com.yidian.galaxy.app.consts.AppRedisKey;
import com.yidian.galaxy.app.entity.dto.AppUserInfoDto;
import com.yidian.galaxy.redis.support.RedisSupport;
import com.yidian.galaxy.web.ano.PublicApi;
import com.yidian.galaxy.web.entity.JwtParam;
import com.yidian.galaxy.web.entity.UserSession;
import com.yidian.galaxy.web.entity.result.Result;
import com.yidian.galaxy.web.exception.BusinessException;
import com.yidian.galaxy.web.exception.SystemExceptionEnum;
import com.yidian.galaxy.web.session.UserHolder;
import com.yidian.galaxy.web.support.JsonSupport;
import com.yidian.galaxy.web.support.JwtSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * UserInterceptor
 *
 * @author changshuai.yuan create on 2024/1/23 9:13
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {
    
    public static final String PHONE = "phone";
    
    private final RedisSupport redisSupport;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Class<?> clazz = handlerMethod.getBeanType();
        final Method method = handlerMethod.getMethod();
        if (clazz.isAnnotationPresent(PublicApi.class) || method.isAnnotationPresent(PublicApi.class)) {
            return true;
        }
        //获取token
        final String token = request.getHeader("Authorization");
        if (Objects.isNull(token)) {
            responseError(response, SystemExceptionEnum.NOT_LOGIN);
            return false;
        }
        //验证token
        final JwtParam jwtParam = JwtSupport.parseJwtToken(token);
        if (jwtParam.getUserId() == null) {
            responseError(response, SystemExceptionEnum.LOGIN_TIME_OUT);
            return false;
        }
        this.cacheUserInfo(jwtParam);
        return true;
    }
    
    private void cacheUserInfo(JwtParam jwtParam) {
        final String redisKey = AppRedisKey.APP_USER.keyFormat(jwtParam.getUserId());
        final AppUserInfoDto appUserInfo = redisSupport.objectRedisOperations().get(redisKey);
        //存在取出用户信息存入UserHolder
        UserHolder.Editor.setUser(
                UserSession.of(appUserInfo.getAccountId(), appUserInfo.getUserId(), appUserInfo.getPhone(),
                        appUserInfo.getNikeName(), appUserInfo.getName(), appUserInfo.getCreateTime()));
        //延时登陆状态
        redisSupport.expire(redisKey, AppRedisKey.APP_USER.getTimeout());
        //MDC
        MDC.put(PHONE, appUserInfo.getPhone());
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) {
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        UserHolder.Editor.removeUser();
        MDC.remove(PHONE);
    }
    
    /**
     * 抛出错误
     *
     * @param response      相应
     * @param exceptionEnum 错误枚举
     */
    public void responseError(HttpServletResponse response, SystemExceptionEnum exceptionEnum) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(ContentType.APPLICATION_JSON.toString());
        try (PrintWriter writer = response.getWriter()) {
            Result<Object> fail = Result.fail(new BusinessException(exceptionEnum));
            writer.write(JsonSupport.toJson(fail));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
