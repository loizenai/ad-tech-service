package com.ozenero.adscategorizedservice;

import com.google.gson.Gson;
import com.ozenero.adscategorizedservice.dto.CategoriesResponse;
import com.ozenero.adscategorizedservice.dto.UrlsRequest;
import com.ozenero.adscategorizedservice.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.util.Arrays;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
class AdsCategorizedServiceApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    void testcase_1() throws Exception{
        UrlsRequest urlsRequest =
                UrlsRequest.builder()
                    .urls(Arrays.asList(
                        "https://www.starwars.com/news/everything-we-know-about-the-mandalorian",
                        "https://www.starwars.com/",
                        "https://www.imdb.com/find?q=star+wars&ref_=nv_sr_sm",
                        "https://edition.cnn.com/sport"
                    ))
                .build();
        doTestCase_1(urlsRequest);
    }

    private void doTestCase_1(UrlsRequest urlsRequest) throws Exception {
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.asJsonString(urlsRequest)));

        // then - verify the output
        response.andExpect(status().isOk());

        String output = response.andReturn()
                .getResponse()
                .getContentAsString();

        CategoriesResponse categoriesResponse = gson.fromJson(output, CategoriesResponse.class);

        Assert.isTrue(categoriesResponse.getUrlCategories().get(
                        "https://www.starwars.com/news/everything-we-know-about-the-mandalorian"
                    ).contains("Star Wars"), "Star-Wars category");

        Assert.isTrue(categoriesResponse.getUrlCategories().get(
                "https://www.starwars.com/"
        ).contains("Star Wars"), "Star-Wars category");

        Assert.isTrue(categoriesResponse.getUrlCategories().get(
                "https://www.imdb.com/find?q=star+wars&ref_=nv_sr_sm"
        ).isEmpty(), "");

        Assert.isTrue(categoriesResponse.getUrlCategories().get(
                "https://edition.cnn.com/sport"
        ).contains("Basketball"), "Basketball Category");
    }
}
