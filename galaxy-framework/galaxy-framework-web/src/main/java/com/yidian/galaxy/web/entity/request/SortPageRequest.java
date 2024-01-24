package com.yidian.galaxy.web.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Optional;

/**
 * 排序分页
 *
 * @author changshuai.yuan DateTime: 2023/12/25 10:35
 */
@Getter
@Setter
public class SortPageRequest<T extends SortPageRequest.SortFields> extends PageRequest {
    
    public enum Sort {
        Asc,
        Desc
    }
    
    public interface SortFields {
        
        default String getSortParam() {
            return "";
        }
    }
    
    /**
     * 排序字段对,传递时候使用枚举name传递,避免暴露数据库字段
     *
     * <pre>{@code
     *      @Getter
     *      @RequiredArgsConstructor
     *      public enum SortParam implements SortFields{
     *          TIME("create_time")
     *          private final String sortParam;
     *      }
     *  }</pre>
     *
     * @param <T> 排序字段类,建议使用枚举的形式实现
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class SortPair<T extends SortFields> {
        
        @ApiModelProperty("排序字段")
        private T sortParam;
        
        @ApiModelProperty("排序方式,Asc-正序,Desc-倒序")
        private Sort sort = Sort.Asc;
    }
    
    private List<SortPair<T>> sortPairs;
    
    public Optional<SortPair<T>> getFirstSort() {
        return Optional.ofNullable(sortPairs != null && !sortPairs.isEmpty() ? sortPairs.get(0) : null);
    }
    
}
