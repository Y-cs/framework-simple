package com.yidian.galaxy.common.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yidian.galaxy.common.consts.NoticeTemplate;
import com.yidian.galaxy.common.consts.RedisKey;
import com.yidian.galaxy.common.domain.NoticeTemplateDo;
import com.yidian.galaxy.common.mapper.NoticeTemplateMapper;
import com.yidian.galaxy.redis.support.RedisSupport;
import com.yidian.galaxy.web.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * NoticeTemplateBiz
 *
 * @author changshuai.yuan create on 2024/1/24 18:10
 */
@Component
@RequiredArgsConstructor
public class NoticeTemplateBiz {
    
    private final NoticeTemplateMapper noticeTemplateMapper;
    
    private final RedisSupport redisSupport;
    
    /**
     * 获取模板
     *
     * @param usesEnum 用途枚举
     * @return 模板
     */
    public String findTemplate(NoticeTemplate.UsesEnum usesEnum) {
        NoticeTemplateDo noticeTemplateDo = redisSupport.hashRedisOperations()
                .hGet(RedisKey.NOTICE_TEMPLATE.getPrefix(), usesEnum.name());
        if (noticeTemplateDo == null) {
            noticeTemplateDo = noticeTemplateMapper.selectOne(
                    new LambdaQueryWrapper<NoticeTemplateDo>().eq(NoticeTemplateDo::getUses, usesEnum));
            if (noticeTemplateDo == null) {
                //这一定是一个编码阶段能发现的错误,不做处理
                throw new BusinessException("模板不存在");
            }
            redisSupport.hashRedisOperations()
                    .hSet(RedisKey.NOTICE_TEMPLATE.getPrefix(), usesEnum.name(), noticeTemplateDo);
        }
        return noticeTemplateDo.getNotice();
    }
    
}
