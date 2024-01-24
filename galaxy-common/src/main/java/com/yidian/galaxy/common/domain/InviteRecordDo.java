package com.yidian.galaxy.common.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * InviteRecordDo
 *
 * @author changshuai.yuan create on 2024/1/24 17:14
 */
@Data
public class InviteRecordDo {
    
    private Long id;
    
    private Long inviterId;
    
    private Long inviteesId;
    
    private LocalDateTime createTime;
    
}
