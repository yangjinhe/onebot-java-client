package com.github.onebot.entity.event.notice.group;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.onebot.entity.event.notice.NoticeEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群禁言
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupBanNoticeEvent extends NoticeEvent {
    /**
     * 事件子类型
     * ban、lift_ban分别表示禁言、解除禁言
     */
    @JSONField(name = "sub_type")
    private String subType;
    /**
     * 群号
     */
    @JSONField(name = "group_id")
    private long groupId;
    /**
     * 操作者 QQ 号
     */
    @JSONField(name = "operator_id")
    private long operatorId;
    /**
     * 禁言时长
     * 单位秒
     */
    @JSONField(name = "duration")
    private long duration;
}
