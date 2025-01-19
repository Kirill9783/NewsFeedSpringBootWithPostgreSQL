package com.news.spring.repositories;

import com.news.spring.dto.NewsDto;
import com.news.spring.entity.CategoryEntity;
import com.news.spring.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    List<NewsEntity> findByCategoryEntity(CategoryEntity categoryEntity);
}
