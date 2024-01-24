package com.yidian.galaxy.web.entity.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页请求父类
 *
 * @author changshuai.yuan create on 2024/1/18 18:05
 */
@Getter
@Setter
public class PageRequest {
    
    private int pageIndex = 1;
    
    private int pageSize = 10;
    
}