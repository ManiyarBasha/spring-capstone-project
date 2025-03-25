package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.BlogDTO;
import com.entity.BlogEntity;
import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    /**
     * Creates a new blog entry.
     */
    public BlogDTO createBlog(BlogDTO blogDTO) {
        BlogEntity blogEntity = convertToEntity(blogDTO);
        BlogEntity savedBlog = blogRepository.save(blogEntity);
        return convertToDTO(savedBlog);
    }

    /**
     * Retrieves a blog by its ID.
     *
     * @throws ResourceNotFoundException If blog is not found.
     */
    public BlogDTO getBlogById(Long id) {
        BlogEntity blogEntity = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));
        return convertToDTO(blogEntity);
    }

    /**
     * Updates an existing blog entry.
     *
     * @throws ResourceNotFoundException If blog is not found.
     */
    public BlogDTO updateBlog(Long id, BlogDTO blogDTO) {
        BlogEntity blogEntity = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));
        
        blogEntity.setTitle(blogDTO.getTitle());
        blogEntity.setContent(blogDTO.getContent());

        BlogEntity updatedBlog = blogRepository.save(blogEntity);
        return convertToDTO(updatedBlog);
    }

    /**
     * Deletes a blog entry.
     *
     * @throws ResourceNotFoundException If blog is not found.
     */
    public boolean deleteBlog(Long id) {
        if (!blogRepository.existsById(id)) {
            throw new ResourceNotFoundException("Blog not found with id: " + id);
        }
        blogRepository.deleteById(id);
        return true;
    }

    /**
     * Retrieves all blogs.
     */
    public List<BlogDTO> getAllBlogs() {
        return blogRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts a BlogDTO to a BlogEntity.
     */
    private BlogEntity convertToEntity(BlogDTO blogDTO) {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId(blogDTO.getId());
        blogEntity.setTitle(blogDTO.getTitle());
        blogEntity.setContent(blogDTO.getContent());
        return blogEntity;
    }

    /**
     * Converts a BlogEntity to a BlogDTO.
     */
    private BlogDTO convertToDTO(BlogEntity blogEntity) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blogEntity.getId());
        blogDTO.setTitle(blogEntity.getTitle());
        blogDTO.setContent(blogEntity.getContent());
        return blogDTO;
    }
}
