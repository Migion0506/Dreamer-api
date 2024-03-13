package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.CommentEntity;
import org.beru.dreammer.service.CommentService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
@AllArgsConstructor

public class DreamSocketController {
    private final CommentService commentService;

    @MessageMapping("/dream/{dreamId}/comment")
    @SendTo("/topic/dream/{dreamId}/comment")
    public CommentEntity addComment(@DestinationVariable String dreamId, CommentEntity comment) {
        return commentService.addComment(comment);
    }

    @MessageMapping("/dream/{dreamId}/edit")
    @SendTo("/topic/comment/{dreamId}/edit")
    public CommentEntity editComment(@DestinationVariable String dreamId, CommentEntity comment) {
        return commentService.editComment(comment, comment.getId());
    }

    @MessageMapping("/dream/{dreamId}/delete")
    @SendTo("/topic/comment/{dreamId}/delete")
    public CommentEntity deleteComment(@DestinationVariable String dreamId, CommentEntity commentId) {
        commentService.deleteComment(commentId.getId());
        return commentId;
    }
}

