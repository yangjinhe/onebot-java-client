package com.github.onebot.entity.event.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.onebot.entity.base.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 私聊消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PrivateMessageEvent extends MessageEvent {
    /**
     * 消息子类型
     * 如果是好友则是 friend
     * 如果从群或讨论组来的临时会话则分别是 group、discuss
     */
    @JSONField(name = "sub_type")
    private String subType;
    /**
     * 发送人信息
     */
    @JSONField(name = "sender")
    private User sender;
}
