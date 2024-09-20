package com.github.onebot.entity.event.meta;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.onebot.entity.base.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 心跳
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HeartBeatMetaEvent extends MetaEvent {
    /**
     * 状态信息
     */
    @JSONField(name = "status")
    private Status status;

    /**
     * 到下次心跳的间隔，单位毫秒
     */
    @JSONField(name = "interval")
    private Long interval;
}
