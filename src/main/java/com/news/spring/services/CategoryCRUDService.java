package com.news.spring.services;

import com.news.spring.dto.CategoryDto;
import com.news.spring.entity.CategoryEntity;
import com.news.spring.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryCRUDService implements CRUDService<CategoryDto> {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto getById(Long id) {
        log.info("Показана категория с ID: {}", id);
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория с ID " + id + " не найдена")
        );
        return mapToDto(categoryEntity);
    }

    @Override
    public List<CategoryDto> getAll() {
        log.info("Выведен полный список категорий");
        return categoryRepository.findAll()
                .stream()
                .map(CategoryCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(CategoryDto categoryDto) {
        Long nextId = (categoryRepository.count() == 0 ? 0 : categoryRepository.count())+ 1;
        categoryDto.setId(nextId);
        categoryRepository.save(mapToEntity(categoryDto));
    }

    @Override
    public void update(Long id, CategoryDto categoryDto) {
        categoryRepository.save(mapToEntity(categoryDto));
    }

    @Override
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            log.info("Не удалось удалить категорию с ID {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория с ID " + id + " не найдена");
        }
        log.info("Категория с ID {} удалена", id);
        categoryRepository.deleteById(id);
    }

    public static CategoryDto mapToDto(CategoryEntity categoryEntity) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryEntity.getId());
        categoryDto.setTitle(categoryEntity.getTitle());
        categoryDto.setNews(categoryEntity.getNews()
                .stream()
                .map(NewsCRUDService::mapToDto)
                .toList());
        return categoryDto;
    }

    public static CategoryEntity mapToEntity(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDto.getId());
        categoryEntity.setTitle(categoryDto.getTitle());
        categoryEntity.setNews(categoryDto.getNews()
                .stream()
                .map(NewsCRUDService::mapToEntity)
                .toList());
        return categoryEntity;
    }
}
