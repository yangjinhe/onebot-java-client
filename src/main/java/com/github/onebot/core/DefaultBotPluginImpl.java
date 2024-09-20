package com.github.onebot.core;

import com.github.onebot.entity.event.message.DiscussMessageEvent;
import com.github.onebot.entity.event.message.GroupMessageEvent;
import com.github.onebot.entity.event.message.GuildMessageEvent;
import com.github.onebot.entity.event.message.PrivateMessageEvent;
import com.github.onebot.entity.event.message.WholeMessageEvent;
import com.github.onebot.entity.event.meta.HeartBeatMetaEvent;
import com.github.onebot.entity.event.meta.LifecycleMetaEvent;
import com.github.onebot.entity.event.notice.FriendAddNoticeEvent;
import com.github.onebot.entity.event.notice.group.GroupAdminNoticeEvent;
import com.github.onebot.entity.event.notice.group.GroupBanNoticeEvent;
import com.github.onebot.entity.event.notice.group.GroupDecreaseNoticeEvent;
import com.github.onebot.entity.event.notice.group.GroupHonorChangeNoticeEvent;
import com.github.onebot.entity.event.notice.group.GroupIncreaseNoticeEvent;
import com.github.onebot.entity.event.notice.group.GroupLuckyKingNoticeEvent;
import com.github.onebot.entity.event.notice.group.GroupPokeNoticeEvent;
import com.github.onebot.entity.event.notice.group.GroupUploadNoticeEvent;
import com.github.onebot.entity.event.request.FriendRequestEvent;
import com.github.onebot.entity.event.request.GroupRequestEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultBotPluginImpl extends BotPlugin {

    @Override
    public int onHeartBeatMeta(Bot bot, HeartBeatMetaEvent event) {
        log.info("收到heartbeat消息");
        return MESSAGE_IGNORE;
    }

    @Override
    public int onLifecycleMeta(Bot bot, LifecycleMetaEvent event) {
        log.info("收到lifecycle消息");
        return MESSAGE_IGNORE;
    }

    @Override
    public int onPrivateMessage(Bot bot, PrivateMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupMessage(Bot bot, GroupMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onDiscussMessage(Bot bot, DiscussMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupUploadNotice(Bot bot, GroupUploadNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupAdminNotice(Bot bot, GroupAdminNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupDecreaseNotice(Bot bot, GroupDecreaseNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupIncreaseNotice(Bot bot, GroupIncreaseNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupBanNotice(Bot bot, GroupBanNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onFriendAddNotice(Bot bot, FriendAddNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onFriendRequest(Bot bot, FriendRequestEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupRequest(Bot bot, GroupRequestEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGuildMessage(Bot bot, GuildMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onWholeMessage(Bot bot, WholeMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupPokeNotice(Bot bot, GroupPokeNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupLuckyKingNotice(Bot bot, GroupLuckyKingNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupHonorChangeNotice(Bot bot, GroupHonorChangeNoticeEvent event) {
        return MESSAGE_IGNORE;
    }
}
