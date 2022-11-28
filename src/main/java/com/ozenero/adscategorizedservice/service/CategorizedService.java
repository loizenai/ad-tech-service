package com.ozenero.adscategorizedservice.service;

import com.ozenero.adscategorizedservice.dto.CategoriesResponse;
import com.ozenero.adscategorizedservice.dto.UrlsCategoriesRequest;
import com.ozenero.adscategorizedservice.dto.UrlsRequest;

public interface CategorizedService {
    void process(UrlsCategoriesRequest request);
    void showData();

    CategoriesResponse retrieveCategories(UrlsRequest urlsRequest);
}
