package com.github.onebot.core;

import com.alibaba.fastjson.JSONObject;
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
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * 事件处理器
 * 先根据 post_type 分类，消息/通知/请求/元事件
 * 然后交给对应的继续分类
 * 职责链模式调用插件，返回MESSAGE_BLOCK停止
 */
@Component
@Slf4j
public class EventHandler {

    private final ApplicationContext applicationContext;

    private final DefaultBotPluginImpl defaultPlugin = new DefaultBotPluginImpl();

    public EventHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void handle(Bot bot, JSONObject eventJson) {
        String postType = eventJson.getString("post_type");
        switch (postType) {
            case "message" -> {
                handleMessage(bot, eventJson);
            }
            case "notice" -> {
                handleNotice(bot, eventJson);
            }
            case "request" -> {
                handleRequest(bot, eventJson);
            }
            case "meta_event" -> {
                handleMeta(bot, eventJson);
            }
        }
    }

    private void handleMessage(Bot bot, JSONObject eventJson) {
        String messageType = eventJson.getString("message_type");
        switch (messageType) {
            case "private" -> {
                PrivateMessageEvent event = eventJson.toJavaObject(PrivateMessageEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onPrivateMessage(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "group" -> {
                GroupMessageEvent event = eventJson.toJavaObject(GroupMessageEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupMessage(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "guild" -> {
                GuildMessageEvent event = eventJson.toJavaObject(GuildMessageEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onGuildMessage(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "whole" -> {
                WholeMessageEvent event = eventJson.toJavaObject(WholeMessageEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onWholeMessage(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
        }

    }

    private void handleNotice(Bot bot, JSONObject eventJson) {

        String noticeType = eventJson.getString("notice_type");

        switch (noticeType) {
            case "group_upload" -> {
                GroupUploadNoticeEvent event = eventJson.toJavaObject(GroupUploadNoticeEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupUploadNotice(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "group_admin" -> {
                GroupAdminNoticeEvent event = eventJson.toJavaObject(GroupAdminNoticeEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupAdminNotice(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "group_decrease" -> {
                GroupDecreaseNoticeEvent event = eventJson.toJavaObject(GroupDecreaseNoticeEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupDecreaseNotice(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "group_increase" -> {
                GroupIncreaseNoticeEvent event = eventJson.toJavaObject(GroupIncreaseNoticeEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupIncreaseNotice(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "group_ban" -> {
                GroupBanNoticeEvent event = eventJson.toJavaObject(GroupBanNoticeEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupBanNotice(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "friend_add" -> {
                FriendAddNoticeEvent event = eventJson.toJavaObject(FriendAddNoticeEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onFriendAddNotice(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "notify" -> {
                String subType = eventJson.getString("sub_type");
                switch (subType) {
                    case "poke" -> {
                        // 戳一戳
                        // log.info("收到群{}里面{}的戳一戳", eventJson.getString("group_id"), eventJson.getString("user_id"));
                        GroupPokeNoticeEvent event = eventJson.toJavaObject(GroupPokeNoticeEvent.class);
                        for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                            if (getPlugin(pluginClass).onGroupPokeNotice(bot, event) == BotPlugin.MESSAGE_BLOCK)
                                break;
                        }
                    }
                    case "lucky_king" -> {
                        // 群红包运气王
                        GroupLuckyKingNoticeEvent event = eventJson.toJavaObject(GroupLuckyKingNoticeEvent.class);
                        for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                            if (getPlugin(pluginClass).onGroupLuckyKingNotice(bot, event) == BotPlugin.MESSAGE_BLOCK)
                                break;
                        }
                    }
                    case "honor" -> {
                        // 群成员荣誉变更
                        GroupHonorChangeNoticeEvent event = eventJson.toJavaObject(GroupHonorChangeNoticeEvent.class);
                        for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                            if (getPlugin(pluginClass).onGroupHonorChangeNotice(bot, event) == BotPlugin.MESSAGE_BLOCK)
                                break;
                        }
                    }
                }
            }
        }
    }

    private void handleRequest(Bot bot, JSONObject eventJson) {
        String requestType = eventJson.getString("request_type");
        switch (requestType) {
            case "friend" -> {
                FriendRequestEvent event = eventJson.toJavaObject(FriendRequestEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onFriendRequest(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "group" -> {
                GroupRequestEvent event = eventJson.toJavaObject(GroupRequestEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onGroupRequest(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
        }
    }

    private void handleMeta(Bot bot, JSONObject eventJson) {
        String metaType = eventJson.getString("meta_event_type");
        switch (metaType) {
            case "heartbeat" -> {
                HeartBeatMetaEvent event = eventJson.toJavaObject(HeartBeatMetaEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onHeartBeatMeta(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
            case "lifecycle" -> {
                LifecycleMetaEvent event = eventJson.toJavaObject(LifecycleMetaEvent.class);
                for (Class<? extends BotPlugin> pluginClass : bot.getPluginList()) {
                    if (getPlugin(pluginClass).onLifecycleMeta(bot, event) == BotPlugin.MESSAGE_BLOCK)
                        break;
                }
            }
        }
    }

    private BotPlugin getPlugin(Class<? extends BotPlugin> pluginClass) {
        try {
            return applicationContext.getBean(pluginClass);
        } catch (Exception e) {
            log.error("已跳过 {} ，请检查 @Component", pluginClass.getSimpleName());
            return defaultPlugin;
        }
    }
}
