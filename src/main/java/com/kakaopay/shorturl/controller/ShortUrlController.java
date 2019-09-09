package com.kakaopay.shorturl.controller;

import com.kakaopay.shorturl.controller.request.ShortUrlRequest;
import com.kakaopay.shorturl.controller.response.ShortUrlResponse;
import com.kakaopay.shorturl.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/shortUrl")
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    @PostMapping
    public ShortUrlResponse createShortUrl(@RequestBody @Validated ShortUrlRequest shortUrlRequest) {
        return new ShortUrlResponse(shortUrlService.getShortUrl(shortUrlRequest.getOriginUrl()));
    }
}
