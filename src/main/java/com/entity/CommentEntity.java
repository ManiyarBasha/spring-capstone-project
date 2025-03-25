package com.entity;  // Package declaration

import jakarta.persistence.*;

/**
 * Entity class representing a comment on a blog post.
 * 
 * This class is mapped to a database table and contains information about a comment,
 * including its text and the associated blog post.
 */
@Entity
public class CommentEntity {

    /** Unique identifier for the comment, automatically generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The text of the comment, cannot be null. */
    @Column(nullable = false) 
    private String text;

    /** Many-to-One relationship: Each comment belongs to a single blog post. */
    @ManyToOne
    @JoinColumn(name = "blog_id") // Foreign key referencing the BlogEntity
    private BlogEntity blog;

    /** Default constructor. */
    public CommentEntity() {
        super();
    }

    /** Gets the comment ID. */
    public Long getId() {
        return id;
    }

    /** Sets the comment ID. */
    public void setId(Long id) {
        this.id = id;
    }

    /** Gets the comment text. */
    public String getText() {
        return text;
    }

    /** Sets the comment text. */
    public void setText(String text) {
        this.text = text;
    }

    /** Gets the associated blog post. */
    public BlogEntity getBlog() {
        return blog;
    }

    /** Sets the associated blog post. */
    public void setBlog(BlogEntity blog) {
        this.blog = blog;
    }
}
