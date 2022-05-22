package com.comments.commentSystem.Custom;

import com.comments.commentSystem.model.Comment;
import com.comments.commentSystem.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentsJmsReceiver {

    @Autowired
    CommentRepository commentRepository;

    @RabbitListener(queues = myLinks.exchangeQueue)
    public void receiveMessage(Comment comment) {
        try {
            System.out.println("Received  <" + comment + ">");
            commentRepository.save(comment);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }


}

