package com.ozenero.adscategorizedservice.service;

import com.ozenero.adscategorizedservice.dto.CategoriesResponse;
import com.ozenero.adscategorizedservice.dto.UrlsCategoriesRequest;
import com.ozenero.adscategorizedservice.dto.UrlsRequest;
import com.ozenero.adscategorizedservice.exception.IAdTechErrorCode;
import com.ozenero.adscategorizedservice.exception.IAdTechException;
import com.ozenero.adscategorizedservice.feign.WebParserService;
import com.ozenero.adscategorizedservice.model.transfer.WebContents;
import com.ozenero.adscategorizedservice.model.transfer.WebText;
import com.ozenero.adscategorizedservice.model.transfer.WebUrls;
import com.ozenero.adscategorizedservice.storage.DataStorage;
import com.ozenero.adscategorizedservice.storage.DataStorageImpl;
import com.ozenero.adscategorizedservice.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class CategorizedServiceImpl implements CategorizedService{
    private WebParserService webParserService;
    private DataStorage dataStorage;

    public CategorizedServiceImpl(WebParserService webParserService, DataStorageImpl dataStorage) {
        this.webParserService = webParserService;
        this.dataStorage = dataStorage;
    }

    @Override
    public void process(UrlsCategoriesRequest request) {
        log.info("Start Processing!");
        try {
            WebContents webContents = webParserService.getWebContents(new WebUrls(request.getUrls()));
            // just checking data before main-processing
            if (webContents == null || webContents.getWebs() == null) {
                log.info("Double checking here !!!");
                return;
            }

            // get all set-up categories
            List<String> initCategories = dataStorage.getCategoryNames();
            for (WebText webText: webContents.getWebs()) {
                List<String> categories = request.getCategories();
                for (String category: categories) {
                    // just confirm the category is already init-before.
                    if (!initCategories.contains(category)) {
                        log.info("The categories was not set-up. Please double checking!");
                        continue;
                    }
                    categorizedWebPage(webText, category);
                }
            }
        } catch (Exception e) {
            log.error("Error: " + e);
        }
    }

    private void categorizedWebPage(WebText webText, String category) {
        try {
            List<String> keywords = dataStorage.keywordsByCategory(category);
            boolean isKeywordExist = Arrays.stream(keywords.stream()
                            .map(String::toLowerCase)
                            .toArray(String[]::new))
                    .anyMatch(i-> Utils.containsWord(webText.getContent().toLowerCase(), i));

            this.dataStorage.addCategoriesToUrlsCatMap(
                    webText.getUrl(),
                    isKeywordExist ?  category : null
            );
        } catch(Exception e) {
            log.error("Error: " + e);
        }
    }

    @Override
    public void showData() {
        dataStorage.retrieveUrlCategories().forEach(
                (key, value) -> log.info(">>>>>>>>>>>>>" + key + " : " + Arrays.toString(value.toArray()))
        );
    }

    @Override
    public CategoriesResponse retrieveCategories(UrlsRequest urlsRequest) {
        try {
            log.info(">>> Start retrieveCategories with urlsRequest = " + urlsRequest.toString());
            UrlsCategoriesRequest urlsCategoriesRequest = UrlsCategoriesRequest.builder()
                    .urls(urlsRequest.getUrls())
                    .categories(dataStorage.getCategoryNames())
                    .build();

            process(urlsCategoriesRequest);

            return CategoriesResponse.builder()
                    .urlCategories(dataStorage.retrieveUrlCategories())
                    .build();
        } catch(Exception e) {
            log.error("Error: " + e);
            throw new IAdTechException(IAdTechErrorCode.CATEGORY_PROCESS_ERROR);
        }
    }
}
