package com.github.onebot.entity.event.meta;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.onebot.entity.event.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class MetaEvent extends Event {
    /**
     * heartbeat	元事件类型
     */
    @JSONField(name = "meta_event_type")
    private String metaEventType;
}
