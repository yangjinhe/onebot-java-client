package com.github.onebot.entity.event.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GuildMessageEvent extends MessageEvent {

    @JSONField(name = "sub_type")
    private String subType;
    @JSONField(name = "guild_id")
    private String guildId;
    @JSONField(name = "channel_id")
    private String channelId;
    @JSONField(name = "self_tiny_id")
    private String selfTinyId;
    @JSONField(name = "sender")
    private Sender sender;

    @Data
    public static class Sender {
        @JSONField(name = "user_id")
        private long userId;
        @JSONField(name = "tiny_id")
        private String tinyId;
        @JSONField(name = "nickname")
        private String nickname;

    }
}
