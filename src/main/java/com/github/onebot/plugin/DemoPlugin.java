package com.github.onebot.plugin;

import com.github.onebot.core.Bot;
import com.github.onebot.core.DefaultBotPluginImpl;
import com.github.onebot.entity.event.message.GroupMessageEvent;
import com.github.onebot.entity.event.message.PrivateMessageEvent;
import com.github.onebot.utils.CQCode;
import org.springframework.stereotype.Component;


/**
 * Demo插件
 * 插件必须继承Plugin，上面要 @Component
 * <p>
 * 添加事件：光标移动到类中，按 Ctrl+O 添加事件(讨论组消息、加群请求、加好友请求等)
 * 查看API参数类型：光标移动到方法括号中按Ctrl+P
 * 查看API说明：光标移动到方法括号中按Ctrl+Q
 */
@Component
public class DemoPlugin extends DefaultBotPluginImpl {
    /**
     * 收到私聊消息时会调用这个方法
     *
     * @param bot    机器人对象，用于调用API，例如发送私聊消息 sendPrivateMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onPrivateMessage(Bot bot, PrivateMessageEvent event) {
        // 获取 发送者QQ 和 消息内容
        long userId = event.getUserId();
        String msg = event.getMessage();

        if (msg.equals("hi")) {
            // 调用API发送hello
            bot.sendPrivateMsg(userId, "hello", false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }
        if (msg.equals("sendImage")) {
            // 调用API发送hello
            bot.sendPrivateMsg(userId, CQCode.image("/app/napcat/cache/qrcode.png"), false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }
        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }


    /**
     * 收到群消息时会调用这个方法
     *
     * @param bot    机器人对象，用于调用API，例如发送群消息 sendGroupMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onGroupMessage(Bot bot, GroupMessageEvent event) {
        // 获取 消息内容 群号 发送者QQ
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();

        if (msg.equals("hello")) {
            // 回复内容为 at发送者 + hi
            String result = CQCode.at(userId) + " hi";

            // 调用API发送消息
            bot.sendGroupMsg(groupId, result, false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }

        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }


}
