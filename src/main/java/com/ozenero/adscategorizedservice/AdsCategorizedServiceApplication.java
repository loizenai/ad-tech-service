package com.ozenero.adscategorizedservice;

import com.ozenero.adscategorizedservice.dto.UrlsCategoriesRequest;
import com.ozenero.adscategorizedservice.enums.OrderRunning;
import com.ozenero.adscategorizedservice.service.CategorizedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.Ordered;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class AdsCategorizedServiceApplication implements CommandLineRunner, Ordered {

    private CategorizedService categorizedService;

    public AdsCategorizedServiceApplication(CategorizedService categorizedService) {
        this.categorizedService = categorizedService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AdsCategorizedServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> urls = Arrays.asList(
                "https://www.starwars.com/news/everything-we-know-about-the-mandalorian",
                "https://www.starwars.com/",
                "https://www.imdb.com/find?q=star+wars&ref_=nv_sr_sm",
                "https://edition.cnn.com/sport"
        );
        List<String> categories = Arrays.asList("Star Wars", "Basketball");

        categorizedService.process(
                UrlsCategoriesRequest.builder()
                    .urls(urls)
                    .categories(categories)
                    .build()
        );
        categorizedService.showData();
    }

    @Override
    public int getOrder() {
        return OrderRunning.SECOND.getValue();
    }
}
