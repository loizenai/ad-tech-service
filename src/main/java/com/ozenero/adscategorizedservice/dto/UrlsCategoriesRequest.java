package com.ozenero.adscategorizedservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UrlsCategoriesRequest {
    List<String> urls;
    List<String> categories;
}