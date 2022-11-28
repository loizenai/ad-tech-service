package com.ozenero.adscategorizedservice.controller;

import com.ozenero.adscategorizedservice.dto.CategoriesResponse;
import com.ozenero.adscategorizedservice.dto.UrlsRequest;
import com.ozenero.adscategorizedservice.service.CategorizedService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdTechApis implements AdTechOperation {

    private CategorizedService categorizedService;

    public AdTechApis(CategorizedService categorizedService) {
        this.categorizedService = categorizedService;
    }

    @Override
    public CategoriesResponse getUrlCategories(@RequestBody UrlsRequest urlsRequest) {
        return this.categorizedService.retrieveCategories(urlsRequest);
    }
}
