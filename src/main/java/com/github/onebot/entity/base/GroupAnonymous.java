package com.github.onebot.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class GroupAnonymous {
    @JSONField(name = "id")
    private long id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "flag")
    private String flag;
}