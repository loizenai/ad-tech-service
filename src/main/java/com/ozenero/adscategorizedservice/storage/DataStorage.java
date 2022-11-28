package com.ozenero.adscategorizedservice.storage;

import java.util.List;
import java.util.Map;

public interface DataStorage {
    List<String> getCategoryNames();

    List<String> keywordsByCategory(String name);

    void addCategoriesToUrlsCatMap(String url, String categoryName);

    Map<String, List<String>> retrieveUrlCategories();
}
