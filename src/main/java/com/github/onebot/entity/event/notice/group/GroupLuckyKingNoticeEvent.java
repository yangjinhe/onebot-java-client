package com.github.onebot.entity.event.notice.group;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.onebot.entity.event.notice.NoticeEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupLuckyKingNoticeEvent extends NoticeEvent {

    @JSONField(name = "sub_type")
    private String subType;
    @JSONField(name = "group_id")
    private long groupId;
    @JSONField(name = "target_id")
    private long targetId;

}
