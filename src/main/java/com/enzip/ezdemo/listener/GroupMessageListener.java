package com.enzip.ezdemo.listener;

import com.enzip.robot.component.annotation.Filter;
import com.enzip.robot.component.annotation.FilterValue;
import com.enzip.robot.component.annotation.Listener;
import com.enzip.robot.component.event.message.GroupMessageEvent;
import com.enzip.robot.component.message.MessageReceipt;
import com.enzip.robot.component.message.Messages;
import com.enzip.robot.component.message.MessagesBuilder;
import com.enzip.robot.core.method.match.MatchType;
import com.enzip.robot.core.method.result.EventResult;
import org.springframework.stereotype.Component;

/**
 * @author Enzip
 * @since 2023/11/16 15:38
 */
@Component
public class GroupMessageListener {

    @Listener(priority = 1)
    public EventResult hello(GroupMessageEvent groupMessageEvent) {
        Messages messages = new MessagesBuilder().text("你好").build();
        groupMessageEvent.getGroup().sendBlocking(messages);
        return EventResult.through();
    }

    @Listener(priority = 2)
    @Filter(targets = @Filter.Targets(atBot = true))
    public EventResult at(GroupMessageEvent groupMessageEvent) {
        Messages messages = new MessagesBuilder().at(groupMessageEvent.getUserId()).build();
        groupMessageEvent.getGroup().sendBlocking(messages);
        return EventResult.truncate();
    }

    @Listener(priority = 3)
    @Filter(value = "百度LOGO", matchType = MatchType.TEXT_EQUALS)
    public EventResult baidu(GroupMessageEvent groupMessageEvent) {
        Messages messages = new MessagesBuilder().image("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png").build();
        groupMessageEvent.getGroup().sendBlocking(messages);
        return EventResult.truncate();
    }

    @Listener(priority = 4)
    @Filter(value = "星期{{week,[\\w\\W]*}}", matchType = MatchType.REGEX_CONTAINS)
    public EventResult week(GroupMessageEvent groupMessageEvent, @FilterValue(value = "week") String week) {
        Messages messages = new MessagesBuilder().text("今天是星期").text(week).build();
        MessageReceipt messageReceipt = groupMessageEvent.getGroup().sendBlocking(messages);
        messageReceipt.deleteBlocking(10L);
        return EventResult.truncate();
    }
}
