package com.github.onebot.entity.event.notice.group;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.onebot.entity.event.notice.NoticeEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群管理员变动
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupAdminNoticeEvent extends NoticeEvent {
    /**
     * 事件子类型
     * set unset分别表示设置和取消管理员
     */
    @JSONField(name = "sub_type")
    private String subType;
    /**
     * 群号
     */
    @JSONField(name = "group_id")
    private long groupId;
}
