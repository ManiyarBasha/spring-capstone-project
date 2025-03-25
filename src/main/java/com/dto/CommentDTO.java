package com.dto;  // Package declaration

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for comments.
 * 
 * This class is used to transfer comment data while ensuring validation constraints.
 */
public class CommentDTO {

    /** The text of the comment, must be between 3 and 200 characters and cannot be empty */
    @NotEmpty(message = "Comment text cannot be null")
    @Size(min = 3, max = 200, message = "Comment must be between 3 and 200 characters")
    private String text;

    /** The ID of the blog post that the comment belongs to, must be a positive number */
    @Min(value = 1, message = "Blog ID must be a positive number")
    private Long blogId;

    /** Default constructor. */
    public CommentDTO() {
        super();
    }

    /** Parameterized constructor for creating a CommentDTO object with values. */
    public CommentDTO(String text, Long blogId) {
        this.text = text;
        this.blogId = blogId;
    }

    /** Gets the comment text. */
    public String getText() {
        return text;
    }

    /** Sets the comment text. */
    public void setText(String text) {
        this.text = text;
    }

    /** Gets the associated blog ID. */
    public Long getBlogId() {
        return blogId;
    }

    /** Sets the associated blog ID. */
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    /** Returns a string representation of the CommentDTO object. */
    @Override
    public String toString() {
        return "CommentDTO [text=" + text + ", blogId=" + blogId + "]";
    }
}
