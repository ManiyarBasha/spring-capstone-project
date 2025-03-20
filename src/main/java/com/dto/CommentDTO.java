package com.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentDTO {
	
    @NotEmpty(message = "Comment text cannot be null")
    @Size(min = 3, max = 200, message = "Comment must be between 3 and 200 characters")
    private String text;
    @Min(value = 1, message = "Blog ID must be a positive number")
     @NotNull(message = "Blog ID cannot be null")
    private Long blogId;
    
    

    public CommentDTO() {
		super();
		
	}

	public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

	public CommentDTO(
			@NotEmpty(message = "Comment text cannot be null") @Size(min = 3, max = 200, message = "Comment must be between 3 and 200 characters") String text,
			@NotNull(message = "Blog ID cannot be null") Long blogId) {
		super();
		this.text = text;
		this.blogId = blogId;
	}

	@Override
	public String toString() {
		return "CommentDTO [text=" + text + ", blogId=" + blogId + "]";
	}
	

	
    
}
