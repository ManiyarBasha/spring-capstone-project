package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entity.CommentEntity;

/**
 * Repository for managing comments in the database.
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    /**
     * Retrieves all comments associated with a specific blog.
     */
    List<CommentEntity> findByBlogId(Long blogId);
}
