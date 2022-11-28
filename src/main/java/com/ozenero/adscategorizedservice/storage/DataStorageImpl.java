package com.ozenero.adscategorizedservice.storage;

import com.ozenero.adscategorizedservice.enums.OrderRunning;
import com.ozenero.adscategorizedservice.model.Category;
import com.ozenero.adscategorizedservice.model.Keyword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DataStorageImpl implements CommandLineRunner, Ordered, DataStorage {
    private List<Category> category = new ArrayList<Category>();
    private Map<String, List<String>> urlCategoriesMap = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {
        log.info("Init Data");

        // 1. StarWar Category
        List<Keyword> starWarKeywords = Arrays.asList("star war", "starwars", "starwar", "r2d2", "star wars")
                .stream().map(i -> new Keyword(i))
                .collect(Collectors.toList());

        Category starWar = Category.builder()
                            .name("Star Wars")
                            .keywords(starWarKeywords)
                            .build();

        category.add(starWar);

        // 2. Basketball Category
        List<Keyword> basketballKeywords = Arrays.asList("basketball", "nba", "ncaa", "lebron james", "john stokton",
                        "anthony davis")
                .stream().map(i -> new Keyword(i))
                .collect(Collectors.toList());

        Category basketball = Category.builder()
                .name("Basketball")
                .keywords(basketballKeywords)
                .build();

        category.add(basketball);
    }

    @Override
    public int getOrder() {
        return OrderRunning.FIRST.getValue();
    }

    @Override
    public List<String> getCategoryNames() {
        return category.stream().map(Category::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> keywordsByCategory(String name) {
        Optional<Category> cat = category.stream()
                                .filter(i->i.getName().equalsIgnoreCase(name))
                                .findFirst();

        if (cat.isPresent()) {
            return cat.get().getKeywords().stream()
                    .map(Keyword::getValue)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void addCategoriesToUrlsCatMap(String url, String categoryName) {
        if (urlCategoriesMap.containsKey(url) && categoryName == null) {
            return;
        } if (urlCategoriesMap.containsKey(url) && categoryName != null
                && !urlCategoriesMap.get(url).contains(categoryName)) {
            urlCategoriesMap.get(url).add(categoryName);
        } else {
            List<String> catLst =  categoryName != null ?
                    new LinkedList<String>(Arrays.asList(categoryName))
                    : new LinkedList<String>();
            urlCategoriesMap.put(url, catLst);
        }
    }

    @Override
    public Map<String, List<String>> retrieveUrlCategories() {
        return urlCategoriesMap;
    }
}
