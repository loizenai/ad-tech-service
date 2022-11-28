package com.ozenero.adscategorizedservice.feign;

import com.ozenero.adscategorizedservice.model.transfer.WebContents;
import com.ozenero.adscategorizedservice.model.transfer.WebUrls;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "web-parser-client", url = "${config.services.web-parser.url}")
public interface WebParserService {

    @PostMapping("/api/parse")
    WebContents getWebContents(WebUrls urls);
}