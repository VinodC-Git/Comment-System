package com.comments.commentSystem.repository;

import com.comments.commentSystem.dto.CustomCommentResult;
import com.comments.commentSystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /*
    Select child nodes from a parent node
     */
    @Query("SELECT NEW  com.comments.commentSystem.dto.CustomCommentResult(c.id, c.name, c.content, c.parentId)" +
            "FROM Comment as c WHERE parentId = :parentId")
    public List<CustomCommentResult> findByParent(@Param("parentId") Long parent_id);
}