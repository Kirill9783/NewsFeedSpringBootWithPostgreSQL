package com.news.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class NewsDto {

    private Long id;
    private String title;
    private String text;
    private Instant date;

    @JsonIgnore
    private Long categoryId;
    private String categoryTitle;
}
