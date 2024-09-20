package com.github.onebot.entity.event.notice.group;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.onebot.entity.base.File;
import com.github.onebot.entity.event.notice.NoticeEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 群文件上传
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupUploadNoticeEvent extends NoticeEvent {
    /**
     * 群号
     */
    @JSONField(name = "group_id")
    private long groupId;
    /**
     * 文件信息
     */
    @JSONField(name = "file")
    private File file;
}
