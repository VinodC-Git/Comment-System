package com.comments.commentSystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomCommentResult {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("content")
    private String content;
    @JsonProperty("parent_id")
    private Long parent_id;

    public CustomCommentResult(Long id, String name, String content, Long parent_id) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.parent_id = parent_id;
    }

}
