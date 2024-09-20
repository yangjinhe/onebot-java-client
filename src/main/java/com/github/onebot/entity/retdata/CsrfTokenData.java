package com.github.onebot.entity.retdata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class CsrfTokenData {
    @JSONField(name = "token")
    private int token;
}
