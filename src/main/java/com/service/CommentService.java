package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.CommentDTO;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

/**
 * Service class for managing blog comments.
 * Provides functionalities to add, retrieve, and list comments for blogs.
 */
@Service
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    /**
     * Constructor to initialize dependencies.
     */
    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    /**
     * Adds a new comment to a blog post after validating the blog ID.
     * Throws an exception if the blog does not exist.
     */
    public CommentDTO addComment(CommentDTO commentDTO) {
        BlogEntity blogEntity = blogRepository.findById(commentDTO.getBlogId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blog not found with id: " + commentDTO.getBlogId()));

        CommentEntity commentEntity = convertToEntity(commentDTO, blogEntity);
        CommentEntity savedComment = commentRepository.save(commentEntity);
        return convertToDTO(savedComment);
    }

    /**
     * Retrieves all comments associated with a specific blog post.
     * Throws an exception if the blog ID is invalid or no comments are found.
     */
    public List<CommentDTO> getCommentsByBlogId(Long blogId) {
        if (!blogRepository.existsById(blogId)) {
            throw new ResourceNotFoundException("Blog not found with id: " + blogId);
        }

        List<CommentEntity> commentEntities = commentRepository.findByBlogId(blogId);
        
        if (commentEntities.isEmpty()) {
            throw new ResourceNotFoundException("No comments found for blog id: " + blogId);
        }

        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (CommentEntity comment : commentEntities) {
            commentDTOs.add(convertToDTO(comment));
        }

        return commentDTOs;
    }

    /**
     * Retrieves all comments across all blog posts.
     */
    public List<CommentDTO> getAllComments() {
        List<CommentEntity> commentEntities = commentRepository.findAll();
        
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (CommentEntity comment : commentEntities) {
            commentDTOs.add(convertToDTO(comment));
        }

        return commentDTOs;
    }

    /**
     * Converts a CommentDTO object to a CommentEntity.
     */
    private CommentEntity convertToEntity(CommentDTO commentDTO, BlogEntity blogEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(commentDTO.getText());
        commentEntity.setBlog(blogEntity);
        return commentEntity;
    }

    /**
     * Converts a CommentEntity object to a CommentDTO.
     */
    private CommentDTO convertToDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText(commentEntity.getText());
        commentDTO.setBlogId(commentEntity.getBlog().getId());
        return commentDTO;
    }
}
