package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.CommentDTO;
import com.exception.ResourceNotFoundException;
import com.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs/comment")
@Tag(name = "Comment Controller", description = "API for managing blog comments")  // Swagger Tag
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @Operation(summary = "Add a comment", description = "Adds a comment to a blog post")
    public ResponseEntity<CommentDTO> addComment(@Valid @RequestBody CommentDTO commentDTO) {
    	 validateBlogId(commentDTO.getBlogId()); // Validate blogId before proceeding

        CommentDTO addedComment = commentService.addComment(commentDTO);
        return ResponseEntity.ok(addedComment);
    }

    @GetMapping("/{blogId}")
    @Operation(summary = "Get comments by blog ID", description = "Fetches all comments for a specific blog post")
    public ResponseEntity<List<CommentDTO>> getCommentsByBlogId(@PathVariable Long blogId) {
        validateBlogId(blogId);  // Validate the ID before proceeding
        List<CommentDTO> comments = commentService.getCommentsByBlogId(blogId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all comments", description = "Fetches a list of all comments from all blogs")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    private void validateBlogId(Long blogId) {
    	if ( blogId < 1) {
    		throw new ResourceNotFoundException("Blog ID must be a positive number");
        }
    }

}
