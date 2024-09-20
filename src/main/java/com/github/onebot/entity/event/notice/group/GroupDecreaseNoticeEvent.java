package com.github.onebot.entity.event.notice.group;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.onebot.entity.event.notice.NoticeEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群成员减少
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupDecreaseNoticeEvent extends NoticeEvent {
    /**
     * 事件子类型
     * leave、kick、kick_me分别表示主动退群、成员被踢、机器人自己被踢
     */
    @JSONField(name = "sub_type")
    private String subType;
    /**
     * 群号
     */
    @JSONField(name = "group_id")
    private long groupId;
    /**
     * 操作者 QQ 号（如果是主动退群，则和 user_id 相同）
     */
    @JSONField(name = "operator_id")
    private long operatorId;
}
