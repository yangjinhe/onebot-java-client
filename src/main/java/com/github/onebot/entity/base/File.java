package com.github.onebot.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class File {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "size")
    private long size;
    @JSONField(name = "busid")
    private long busid;
}
