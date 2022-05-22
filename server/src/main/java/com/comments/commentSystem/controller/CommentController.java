package com.comments.commentSystem.controller;


import com.comments.commentSystem.Custom.CommentsJmsSender;
import com.comments.commentSystem.exception.ResourceNotFoundException;
import com.comments.commentSystem.model.Comment;
import com.comments.commentSystem.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
public class CommentController {

    @Autowired
    CommentsJmsSender commentsJmsSender;

    @Autowired
    CommentRepository commentRepository;


    /*
    Select all comments
     */
    @GetMapping("/get/comments")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    /*
    Post a comment using Messaging Queue
     */

    @PostMapping("/post/comments")
    @ResponseBody
    public Comment createComment(Comment comment) {
        return commentsJmsSender.send(comment);
    }

    @PostMapping("/post/comments/Async")
    @ResponseBody
    public Comment createCommentForAsync(@RequestBody Comment comment) {
        return commentsJmsSender.send(comment);
    }

    /*
    Get Comment by id
     */
    @GetMapping("/comments/{id}")
    public Comment getCommentById(@PathVariable(value = "id") Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(commentId));
    }

    /*
   To show comments in UI
     */
    private String html = "";

    @GetMapping("/check/Comment")
    @ResponseBody
    public String test(HttpServletResponse response) {
        html = "";
        List<Comment> comments = getAllComments();
        for (Comment c : comments) {
            if (c.getParentId() == null) {
                html += "<div class='panel panel-primary'><div class='panel-heading'>By <b>" + c.getName() + "</b></div>" +
                        "<div class='panel-body'>" + c.getContent() + "</div><div class='panel-footer' align='right'>" +
                        "<button type='button' class='btn btn-primary reply' id=" + c.getId() + ">Reply</button></div> </div>";
            }
        }
        return html;
    }

}