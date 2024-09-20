package com.github.onebot.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.fastjson.annotation.JSONField;

@NoArgsConstructor
@Data
public class OneBotReqMessage {


    private int time;
    @JSONField(name = "self_id")
    private long selfId;
    @JSONField(name = "post_type")
    private String postType;
    @JSONField(name = "meta_event_type")
    private String metaEventType;
    @JSONField(name = "sub_type")
    private String subType;
}
