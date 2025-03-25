package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.BlogEntity;

/**
 * Repository interface for BlogEntity.
 * Extends JpaRepository to provide CRUD operations for BlogEntity.
 */
@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
}
