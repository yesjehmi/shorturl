package com.kakaopay.shorturl.controller;

import com.kakaopay.shorturl.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class UrlWebController {

    private final ShortUrlService shortUrlService;

    @GetMapping("{shortUrl}")
    public RedirectView redirectFromShortUrl(@PathVariable String shortUrl) {
        RedirectView redirectView = new RedirectView();
        String redirectUrl = shortUrlService.getOriginUrl(shortUrl);
        redirectView.setUrl(redirectUrl);
        return redirectView;
    }
}
