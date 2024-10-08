package com.github.onebot.entity.event.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.onebot.entity.base.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 讨论组消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DiscussMessageEvent extends MessageEvent {
    /**
     * 讨论组 ID
     */
    @JSONField(name = "discuss_id")
    private long discussId;
    /**
     * 发送人信息
     */
    @JSONField(name = "sender")
    private User sender;
}
