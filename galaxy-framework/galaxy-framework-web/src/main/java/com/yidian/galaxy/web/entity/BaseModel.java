package com.yidian.galaxy.web.entity;

import com.yidian.galaxy.web.session.UserHolder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 数据库操作通用对象
 *
 * @author changshuai.yuan create on 2024/1/23 16:07
 */
@Getter
@Setter
public class BaseModel {
    
    private long createId;
    
    private LocalDateTime createTime;
    
    private long modifyId;
    
    private LocalDateTime modifyTime;
    
    private boolean del;
    
    /**
     * 初始化
     */
    public void init() {
        this.createId = UserHolder.getUserOrEmptySession().getAccountId();
        this.createTime = LocalDateTime.now();
        this.modifyId = UserHolder.getUserOrEmptySession().getAccountId();
        this.modifyTime = LocalDateTime.now();
        this.del = false;
    }
    
    /**
     * 更新
     */
    public void update() {
        this.modifyId = UserHolder.getUserOrEmptySession().getAccountId();
        this.modifyTime = LocalDateTime.now();
        this.del = false;
    }
    
    /**
     * 逻辑删除
     */
    public void tombstone() {
        this.modifyId = UserHolder.getUserOrEmptySession().getAccountId();
        this.modifyTime = LocalDateTime.now();
        this.del = true;
    }
    
}
