package com.enzip.ezdemo.listener;

import com.enzip.robot.component.annotation.Listener;
import com.enzip.robot.component.event.message.PrivateMessageEvent;
import com.enzip.robot.component.message.Messages;
import com.enzip.robot.component.message.MessagesBuilder;
import com.enzip.robot.core.method.result.EventResult;
import org.springframework.stereotype.Component;

/**
 * @author Enzip
 * @since 2023/11/17 8:56
 */
@Component
public class PrivateMessageListener {

    @Listener(priority = 1)
    public EventResult hello(PrivateMessageEvent privateMessageEvent) {
        Messages messages = new MessagesBuilder().text("你好").build();
        privateMessageEvent.getFriend().sendBlocking(messages);
        return EventResult.through();
    }
}
