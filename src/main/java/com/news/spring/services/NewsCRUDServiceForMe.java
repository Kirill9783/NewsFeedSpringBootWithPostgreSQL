//package com.news.spring.services;
//
//import com.news.spring.dto.NewsDto;
//import com.news.spring.entity.CategoryEntity;
//import com.news.spring.entity.NewsEntity;
//import com.news.spring.repositories.CategoryRepository;
//import com.news.spring.repositories.NewsRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class NewsCRUDService implements CRUDService<NewsDto> {
//
//    //файл для меня. Методы реализованы, если добавлять новость и категорию к ней по id категории
//
//    @Value("${title.length.max}")
//    private Integer maxTitleLength;
//
//    @Value("${text.length.max}")
//    private Integer maxTextLength;
//
//    private final CategoryRepository categoryRepository;
//    private final NewsRepository newsRepository;
//
//    public List<NewsDto> getNewsByCategoryId(Long id) {
//        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow();
//
//        return newsRepository.findByCategoryEntity(categoryEntity)
//                .stream()
//                .map(NewsCRUDService::mapToDto)
//                .toList();
//    }
//
//    @Override
//    public NewsDto getById(Long id) {
//        log.info("Показана новость с ID: {}", id);
//        NewsEntity newsEntity = newsRepository.findById(id).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Новость с ID " + id + " не найдена")
//        );
//        return mapToDto(newsEntity);
//    }
//
//    @Override
//    public List<NewsDto> getAll() {
//        log.info("Выведен полный список новостей");
//        return newsRepository.findAll()
//                .stream()
//                .map(NewsCRUDService::mapToDto)
//                .toList();
//    }
//
//    @Override
//    public void create(NewsDto newsDto) {
//        NewsEntity newsEntity = mapToEntity(newsDto);
//
//        Long categoryId = newsDto.getCategoryId();
//        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория с ID " + categoryId + " не найдена"));
//        newsEntity.setCategoryEntity(categoryEntity);
//
//        Long nextId = (newsRepository.count() == 0 ? 0 : newsRepository.count())+ 1;
//        newsDto.setId(nextId);
//        if (newsDto.getTitle().length() > maxTitleLength) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Заголовок слишком длинный");
//        }
//        if (newsDto.getText().length() > maxTextLength) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Текст слишком длинный");
//        }
//        newsDto.setDate(Instant.now());
//        newsDto.setCategoryTitle(categoryEntity.getTitle());
//
//
//        newsRepository.save(newsEntity);
//        log.info("Добавлена новость с ID: {}", nextId);
//    }
//
//    @Override
//    public void update(Long id, NewsDto newsDto) {
//        NewsEntity newsEntity = mapToEntity(newsDto);
//        Long categoryId = newsDto.getCategoryId();
//        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория с ID " + categoryId + " не найдена"));
//        newsEntity.setCategoryEntity(categoryEntity);
//
//        if (!newsRepository.existsById(id)) {
//            log.info("Не удалось обновить новость с ID {}", id);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Новость с ID " + id + " не найдена");
//        }
//        if (newsDto.getTitle().length() > maxTitleLength) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Заголовок слишком длинный");
//        }
//        if (newsDto.getText().length() > maxTextLength) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Текст слишком длинный");
//        }
//        log.info("Новость с ID {} обновлена", id);
//
//        newsDto.setId(id);
//        newsDto.setDate(Instant.now());
//        newsDto.setCategoryTitle(categoryEntity.getTitle());
//        newsRepository.save(newsEntity);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        if (!newsRepository.existsById(id)) {
//            log.info("Не удалось удалить новость с ID {}", id);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Новость с ID " + id + " не найдена");
//        }
//        log.info("Новость с ID {} удалена", id);
//        newsRepository.deleteById(id);
//    }
//
//    public static NewsDto mapToDto(NewsEntity newsEntity) {
//        NewsDto newsDto = new NewsDto();
//        newsDto.setId(newsEntity.getId());
//        newsDto.setTitle(newsEntity.getTitle());
//        newsDto.setText(newsEntity.getText());
//        newsDto.setDate(newsEntity.getDate());
//        newsDto.setCategoryId(newsEntity.getCategoryEntity().getId());
//        newsDto.setCategoryTitle(newsEntity.getCategoryEntity().getTitle());
//        return newsDto;
//    }
//
//    public static NewsEntity mapToEntity(NewsDto newsDto) {
//        NewsEntity newsEntity = new NewsEntity();
////        CategoryEntity categoryEntity = new CategoryEntity();
//
//        newsEntity.setId(newsDto.getId());
//        newsEntity.setTitle(newsDto.getTitle());
//        newsEntity.setText(newsDto.getText());
//        newsEntity.setDate(newsDto.getDate());
//        //newsEntity.setCategoryTitle(newsDto.getCategoryTitle());
//        //newsEntity.setCategoryTitle(categoryEntity.getTitle());
//
//
////        categoryEntity.setId(newsDto.getCategoryId());
////        categoryEntity.setTitle(newsDto.getCategoryTitle());
////        newsEntity.setCategoryEntity(categoryEntity);
//
////        List<NewsEntity> newsEntityList = new ArrayList<>();
////        categoryEntity.setNews(newsEntityList);
////        newsEntity.setCategoryEntity(categoryEntity);
//        return newsEntity;
//    }
//}
