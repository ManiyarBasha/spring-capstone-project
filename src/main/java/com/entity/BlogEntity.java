package com.entity;  // Package declaration

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity class representing a blog post.
 * 
 * This class is mapped to a database table and contains information about a blog post,
 * including its title, content, and associated comments.
 */
@Entity
public class BlogEntity {

    /** Unique identifier for the blog post, automatically generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Title of the blog post, cannot be null. */
    @Column(nullable = false) 
    private String title;

    /** Content of the blog post, cannot be null. */
    @Column(nullable = false) 
    private String content;

    /** List of comments associated with the blog post, using a one-to-many relationship. */
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    /** Default constructor. */
    public BlogEntity() {
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

    /** Gets the list of comments associated with the blog. */
    public List<CommentEntity> getComments() {
        return comments;
    }

    /** Sets the list of comments associated with the blog. */
    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }
}
