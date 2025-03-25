package com.controller;  // Package declaration

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.BlogDTO;
import com.exception.ResourceNotFoundException;
import com.service.BlogService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

/**
 * REST Controller for handling blog-related operations.
 */
@RestController
@RequestMapping("/api/blogs")
@Tag(name = "Blog Controller", description = "API for managing blogs")  // Swagger Tag for API documentation
public class BlogController {

    /** Service layer dependency for handling business logic. */
    private final BlogService blogService;

    /**
     * Constructor-based dependency injection.
     */
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    @Operation(summary = "Create a new blog", description = "Creates a new blog entry")
    public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDTO) {
        BlogDTO createdBlog = blogService.createBlog(blogDTO);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a blog by ID", description = "Fetches a blog using its unique ID")
    public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id) {
        validateId(id);  // Validate the ID before proceeding
        BlogDTO blogDTO = blogService.getBlogById(id);
        return ResponseEntity.ok(blogDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a blog", description = "Updates an existing blog using its ID")
    public ResponseEntity<BlogDTO> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDTO blogDTO) {
        validateId(id);  // Validate the ID before proceeding
        BlogDTO updatedBlog = blogService.updateBlog(id, blogDTO);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a blog", description = "Deletes a blog using its ID")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        validateId(id);  // Validate the ID before proceeding
        blogService.deleteBlog(id);
        return ResponseEntity.ok("Blog deleted successfully");
    }

    @GetMapping
    @Operation(summary = "Get all blogs", description = "Fetches a list of all available blogs")
    public ResponseEntity<List<BlogDTO>> getAllBlogs() {
        List<BlogDTO> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    private void validateId(Long id) {
        if (id < 0) {
            throw new ResourceNotFoundException("Blog ID must be a positive number");
        }
    }
}
