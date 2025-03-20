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

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public CommentDTO addComment(CommentDTO commentDTO) {
        BlogEntity blogEntity = blogRepository.findById(commentDTO.getBlogId())
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + commentDTO.getBlogId()));

        CommentEntity commentEntity = convertToEntity(commentDTO, blogEntity);
        CommentEntity savedComment = commentRepository.save(commentEntity);
        return convertToDTO(savedComment);
    }
    
    // Get all comments by blog ID , if not found display comment not found
    public List<CommentDTO> getCommentsByBlogId(Long blogId) {
        if (!blogRepository.existsById(blogId)) {
            throw new ResourceNotFoundException("Blog not found with id: " + blogId);
        }

        List<CommentEntity> commentEntities = commentRepository.findByBlogId(blogId);

        if (commentEntities.isEmpty()) {
            throw new ResourceNotFoundException(" comments not found for blog id: " + blogId);
        }

        // Convert manually without using map()
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (CommentEntity comment : commentEntities) {
            commentDTOs.add(convertToDTO(comment));
        }

        return commentDTOs;
    }
    
    public List<CommentDTO> getAllComments() {
        List<CommentEntity> commentEntities = commentRepository.findAll();
        
        // Convert manually without using map()
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (CommentEntity comment : commentEntities) {
            commentDTOs.add(convertToDTO(comment));
        }

        return commentDTOs;
    }




    private CommentEntity convertToEntity(CommentDTO commentDTO, BlogEntity blogEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(commentDTO.getText());
        commentEntity.setBlog(blogEntity);
        return commentEntity;
    }

    private CommentDTO convertToDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText(commentEntity.getText());
        commentDTO.setBlogId(commentEntity.getBlog().getId());
        return commentDTO;
    }
}
