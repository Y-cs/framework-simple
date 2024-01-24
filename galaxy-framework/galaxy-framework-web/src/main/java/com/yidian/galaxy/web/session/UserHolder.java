package com.yidian.galaxy.web.session;

import com.yidian.galaxy.web.entity.UserSession;
import com.yidian.galaxy.web.exception.BusinessException;
import com.yidian.galaxy.web.exception.SystemExceptionEnum;

import java.util.Optional;

/**
 * 用户信息持有类
 *
 * @author changshuai.yuan create on 2024/1/19 15:45
 */
public class UserHolder {
    
    private static final UserSession EMPTY_USER_SESSION = UserSession.of(0, 0, "", "", "", null);
    
    private UserHolder() {
    }
    
    private static final ThreadLocal<UserSession> USER_SESSION_DTO_THREAD_LOCAL = new ThreadLocal<>();
    
    /**
     * 获取用户信息
     *
     * @return 用户信息
     * @throws BusinessException notLogin
     */
    public static UserSession getUser() {
        return Optional.ofNullable(USER_SESSION_DTO_THREAD_LOCAL.get())
                .orElseThrow(() -> new BusinessException(SystemExceptionEnum.NOT_LOGIN));
    }
    
    /**
     * 获取用户信息 但不会报错
     *
     * @return 用户信息 或 空的用户信息
     */
    public static UserSession getUserOrEmptySession() {
        return Optional.ofNullable(USER_SESSION_DTO_THREAD_LOCAL.get()).orElse(EMPTY_USER_SESSION);
    }
    
    /**
     * 判断是否登录
     *
     * @return bool
     */
    public static boolean isLogin() {
        return USER_SESSION_DTO_THREAD_LOCAL.get() != null;
    }
    
    /**
     * 设置用户Session
     *
     * @param userSession session
     */
    private static void doSetUser(UserSession userSession) {
        USER_SESSION_DTO_THREAD_LOCAL.set(userSession);
    }
    
    /**
     * 清理
     */
    private static void doRemove() {
        USER_SESSION_DTO_THREAD_LOCAL.remove();
    }
    
    /**
     * 用于控制UserHolder不能直接操作新增和删除
     */
    public static class Editor {
        
        /**
         * 设置用户信息
         *
         * @param userSession 会话信息
         */
        public static void setUser(UserSession userSession) {
            UserHolder.doSetUser(userSession);
        }
        
        /**
         * 移除用户信息
         */
        public static void removeUser() {
            UserHolder.doRemove();
        }
        
    }
    
}
