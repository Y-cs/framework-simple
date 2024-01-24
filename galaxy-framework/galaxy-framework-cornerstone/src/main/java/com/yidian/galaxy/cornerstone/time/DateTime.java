package com.yidian.galaxy.cornerstone.time;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DateTime 由于数据库编写的时候强制要求not null,对于时间类型需要有一个空的默认值
 *
 * @author changshuai.yuan create on 2024/1/24 17:18
 */
public class DateTime {
    
    private static final LocalDateTime EMPTY_TIME = LocalDateTime.of(1000, 1, 1, 0, 0);
    
    /**
     * 判断是否等于空时间
     *
     * @param localDateTime 需要判断的时间
     * @return 真假
     */
    public static boolean isEmpty(LocalDateTime localDateTime) {
        return localDateTime == null || EMPTY_TIME.isEqual(localDateTime);
    }
    
    /**
     * 判断是否等于空时间
     *
     * @param localDate 需要判断的时间
     * @return 真假
     */
    public static boolean isEmpty(LocalDate localDate) {
        return localDate == null || EMPTY_TIME.toLocalDate().isEqual(localDate);
    }
    
    /**
     * 获取空时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime getEmptyTime() {
        return EMPTY_TIME;
    }
}
