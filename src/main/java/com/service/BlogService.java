package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.BlogDTO;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogDTO createBlog(BlogDTO blogDTO) {
        BlogEntity blogEntity = convertToEntity(blogDTO);
        BlogEntity savedBlog = blogRepository.save(blogEntity);
        return convertToDTO(savedBlog);
    }

    public BlogDTO getBlogById(Long id) {
        BlogEntity blogEntity = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));
        return convertToDTO(blogEntity);
    }

    public BlogDTO updateBlog(Long id, BlogDTO blogDTO) {
        BlogEntity blogEntity = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));
        blogEntity.setTitle(blogDTO.getTitle());
        blogEntity.setContent(blogDTO.getContent());
        BlogEntity updatedBlog = blogRepository.save(blogEntity);
        return convertToDTO(updatedBlog);
    }

    public boolean deleteBlog(Long id) {
        if (!blogRepository.existsById(id)) {
            throw new ResourceNotFoundException("Blog not found with id: " + id);
        }
        blogRepository.deleteById(id);
        return true;
    }
    public List<BlogDTO> getAllBlogs() {
        List<BlogEntity> blogEntities = blogRepository.findAll();
        List<BlogDTO> blogDTOs = new ArrayList<>();

        for (BlogEntity blogEntity : blogEntities) {
            blogDTOs.add(convertToDTO(blogEntity));
        }
        
        return blogDTOs;
    }
    
    

    private BlogEntity convertToEntity(BlogDTO blogDTO) {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId(blogDTO.getId());
        blogEntity.setTitle(blogDTO.getTitle());
        blogEntity.setContent(blogDTO.getContent());
        return blogEntity;
    }

    private BlogDTO convertToDTO(BlogEntity blogEntity) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blogEntity.getId());
        blogDTO.setTitle(blogEntity.getTitle());
        blogDTO.setContent(blogEntity.getContent());
        
//        List<String> comments = new ArrayList<>();
//        if (blogEntity.getComments() != null) {
//            for (CommentEntity commentEntity : blogEntity.getComments()) {
//                comments.add(commentEntity.getText());
//            }
//        }
//        blogDTO.setComments(comments);
      return blogDTO;
   }
}