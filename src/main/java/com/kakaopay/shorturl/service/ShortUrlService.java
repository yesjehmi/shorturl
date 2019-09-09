package com.kakaopay.shorturl.service;

import com.kakaopay.shorturl.common.exception.ApplicationException;
import com.kakaopay.shorturl.common.response.ResultType;
import com.kakaopay.shorturl.common.util.Base62Util;
import com.kakaopay.shorturl.domain.UrlDictionary;
import com.kakaopay.shorturl.repository.UrlDictionaryRepository;
import com.kakaopay.shorturl.validator.UrlValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortUrlService {
    private static final String SHORT_URL_DEFAULT_DOMAIN = "http://localhost/";

    private final UrlDictionaryRepository urlDictionaryRepository;
    private final UrlValidator urlValidator;

    public String getShortUrl(String originUrl) {
        urlValidator.validate(originUrl);

        Optional<UrlDictionary> urlDictionary = urlDictionaryRepository.findByOriginUrl(originUrl);
        long urlId = urlDictionary.map(UrlDictionary::getId)
                .orElseGet(() -> createUrlDictionary(originUrl).getId());

        return SHORT_URL_DEFAULT_DOMAIN + Base62Util.fromDecimal(urlId);
    }

    private UrlDictionary createUrlDictionary(String originUrl) {
        return urlDictionaryRepository.save(UrlDictionary.createInstance(originUrl));
    }

    public String getOriginUrl(String shortUrl) {
        long idFromShortUrl = Base62Util.toDecimal(shortUrl);
        UrlDictionary urlDictionary = urlDictionaryRepository.findById(idFromShortUrl)
                .orElseThrow(()-> new ApplicationException(ResultType.INVALID_SHORT_URL));

        return urlDictionary.getOriginUrl();
    }
}
