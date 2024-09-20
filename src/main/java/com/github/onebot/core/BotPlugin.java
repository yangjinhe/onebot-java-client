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

public abstract class BotPlugin {

    /**
     * 执行完毕不继续执行下一个插件
     */
    public static final int MESSAGE_BLOCK = 1;

    /**
     * 继续执行下一个插件
     */
    public static final int MESSAGE_IGNORE = 0;

    /**
     * 收到私聊消息时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onPrivateMessage(Bot bot, PrivateMessageEvent event);

    /**
     * 收到群消息时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGroupMessage(Bot bot, GroupMessageEvent event);

    /**
     * 收到讨论组消息时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onDiscussMessage(Bot bot, DiscussMessageEvent event);

    /**
     * GuildMessage
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGuildMessage(Bot bot, GuildMessageEvent event);

    /**
     * WholeMessage
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onWholeMessage(Bot bot, WholeMessageEvent event);

    /**
     * 群内有文件上传时调用此方法
     * 仅群文件上传表现为事件，好友发送文件在 酷Q 中没有独立的事件，而是直接表现为好友消息，请注意在编写业务逻辑时进行判断。
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGroupUploadNotice(Bot bot, GroupUploadNoticeEvent event);

    /**
     * 群管理员变动时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGroupAdminNotice(Bot bot, GroupAdminNoticeEvent event);

    /**
     * 群成员减少时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGroupDecreaseNotice(Bot bot, GroupDecreaseNoticeEvent event);

    /**
     * 群成员增加时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGroupIncreaseNotice(Bot bot, GroupIncreaseNoticeEvent event);

    /**
     * 群禁言时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGroupBanNotice(Bot bot, GroupBanNoticeEvent event);

    /**
     * 好友添加时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onFriendAddNotice(Bot bot, FriendAddNoticeEvent event);

    /**
     * 加好友请求时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onFriendRequest(Bot bot, FriendRequestEvent event);

    /**
     * 加群请求/邀请时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGroupRequest(Bot bot, GroupRequestEvent event);

    /**
     * 收到心跳包时调用此方法
     * 心跳类型的元事件需要通过设置配置项 enable_heartbeat 为 true 开启，并可通过 heartbeat_interval 配置心跳间隔（单位毫秒）。
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onHeartBeatMeta(Bot bot, HeartBeatMetaEvent event);

    /**
     * 生命周期事件
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onLifecycleMeta(Bot bot, LifecycleMetaEvent event);


    /**
     * 群内戳一戳
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGroupPokeNotice(Bot bot, GroupPokeNoticeEvent event);

    /**
     * 群红包运气王
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGroupLuckyKingNotice(Bot bot, GroupLuckyKingNoticeEvent event);

    /**
     * 群成员荣誉变更
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public abstract int onGroupHonorChangeNotice(Bot bot, GroupHonorChangeNoticeEvent event);
}
