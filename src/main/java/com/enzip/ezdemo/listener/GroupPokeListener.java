package com.enzip.ezdemo.listener;

import com.enzip.robot.component.annotation.Listener;
import com.enzip.robot.component.event.notice.GroupPokeEvent;
import com.enzip.robot.component.message.Messages;
import com.enzip.robot.component.message.MessagesBuilder;
import com.enzip.robot.core.method.result.EventResult;
import org.springframework.stereotype.Component;

/**
 * @author Enzip
 * @since 2023/11/16 16:49
 */
@Component
public class GroupPokeListener {

    @Listener(priority = 1)
    public EventResult poke(GroupPokeEvent groupPokeEvent) {
        if (groupPokeEvent.getTargetId().equals(groupPokeEvent.getBot().getUserId())) {
            Messages messages = new MessagesBuilder().poke(groupPokeEvent.getUserId()).build();
            groupPokeEvent.getGroup().sendBlocking(messages);
        }
        return EventResult.truncate();
    }
}
