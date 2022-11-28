package com.ozenero.adscategorizedservice.controller;

import com.ozenero.adscategorizedservice.dto.CategoriesResponse;
import com.ozenero.adscategorizedservice.dto.UrlsRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdTechOperation {
    @PostMapping("/categories")
    public CategoriesResponse getUrlCategories(@RequestBody UrlsRequest urlsRequest);
}