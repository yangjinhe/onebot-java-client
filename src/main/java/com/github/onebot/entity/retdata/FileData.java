package com.github.onebot.entity.retdata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class FileData {
    @JSONField(name = "file")
    private String file;
}
