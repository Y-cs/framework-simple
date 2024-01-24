package com.yidian.galaxy.web.entity.result;

import com.github.pagehelper.Page;
import com.yidian.galaxy.web.entity.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;

/**
 * 分页返回类
 * @author changshuai.yuan create on 2024/1/18 17:06
 */
@Getter
@Setter
public class PageResult<T> {
    
    private final long total;
    
    private final int pageIndex;
    
    private final int pageSize;
    
    private final Collection<T> data;
    
    public PageResult(Collection<T> data, long total, int pageIndex, int pageSize) {
        this.data = data;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
    }
    
    public PageResult(Collection<T> data, long total, PageRequest pageRequest) {
        this.data = data;
        this.pageIndex = pageRequest.getPageIndex();
        this.pageSize = pageRequest.getPageSize();
        this.total = total;
    }
    
    public static <T> PageResult<T> empty(Page<?> page, PageRequest pageRequest) {
        return new PageResult<>(Collections.emptyList(), page.getTotal(), pageRequest.getPageIndex(),
                pageRequest.getPageSize());
    }
    
}
