package com.comments.commentSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Builder
@Data
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String content;


    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parentId;


    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;


    public Comment(String name, String content) {
        this.name = name;
        this.content = content;
    }


    public Comment(Long id, String name, String content, Long parentId/*, Date createdAt*/) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.parentId = parentId;
        /*  this.createdAt = createdAt;*/
    }

    public Comment(Long id, String name, String content, Long parentId, Date createdAt) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.parentId = parentId;
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "Comment{" +
                " name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


}
