package com.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BlogDTO {
    @Min(value = 1, message = "Blog ID must be a positive number")
    @NotNull(message = "Blog ID cannot be null")
    private Long id;
    
    @NotEmpty(message = "Title cannot be null")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;
    
    @NotEmpty(message = "Content cannot be null")
    @Size(min = 3, max = 200, message = "Content must be between 3 and 100 characters")
    private String content;
    
   // private List<String> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public BlogDTO() {
		super();
		
	}
	

	@Override
	public String toString() {
		return "BlogDTO [id=" + id + ", title=" + title + ", content=" + content + "]";
	}

	

	
    
//
//    public List<String> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<String> comments) {
//        this.comments = comments;
//    }
}