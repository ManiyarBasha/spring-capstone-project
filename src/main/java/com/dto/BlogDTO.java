package com.dto;  // Package declaration

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for Blog.
 * 
 * This class is used to transfer blog data between different application layers.
 * It includes validation constraints to ensure data integrity.
 */
public class BlogDTO {

    /** Blog ID must be a positive number a */
    @Min(value = 1, message = "Blog ID must be a positive number")
    private Long id;

    /** Title must be between 3 and 100 characters and cannot be empty */
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    /** Content must be between 3 and 200 characters and cannot be empty */
    @NotEmpty(message = "Content cannot be empty")
    @Size(min = 3, max = 200, message = "Content must be between 3 and 200 characters")
    private String content;

    /** Default constructor. */
    public BlogDTO() {
        super();
    }

    /** Gets the blog ID. */
    public Long getId() {
        return id;
    }

    /** Sets the blog ID. */
    public void setId(Long id) {
        this.id = id;
    }

    /** Gets the blog title. */
    public String getTitle() {
        return title;
    }

    /** Sets the blog title. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Gets the blog content. */
    public String getContent() {
        return content;
    }

    /** Sets the blog content. */
    public void setContent(String content) {
        this.content = content;
    }

    /** Returns a string representation of the BlogDTO object. */
    @Override
    public String toString() {
        return "BlogDTO [id=" + id + ", title=" + title + ", content=" + content + "]";
    }
}
