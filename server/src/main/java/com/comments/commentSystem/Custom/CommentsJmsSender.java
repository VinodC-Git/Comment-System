package com.comments.commentSystem.Custom;

import com.comments.commentSystem.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentsJmsSender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public Comment send(Comment comment) {
        log.info("Sending Comment to the queue.");
        amqpTemplate.convertAndSend(myLinks.exchange, myLinks.routingKey, comment);
        return comment;
    }

}
