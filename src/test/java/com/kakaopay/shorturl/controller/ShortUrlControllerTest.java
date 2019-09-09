package com.kakaopay.shorturl.controller;

import com.kakaopay.shorturl.controller.request.ShortUrlRequest;
import com.kakaopay.shorturl.controller.response.ShortUrlResponse;
import com.kakaopay.shorturl.repository.UrlDictionaryRepository;
import com.kakaopay.shorturl.support.RestTemplateTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ShortUrlControllerTest extends RestTemplateTest {

    @Autowired
    private UrlDictionaryRepository urlDictionaryRepository;

    @Test
    public void shortUrl_생성_성공() {
        String originUrl = "https://kakaopay.recruiter.co.kr/appsite/company/index";
        ShortUrlRequest request = new ShortUrlRequest(originUrl);
        ResponseEntity<ShortUrlResponse> response = template().postForEntity("/api/v1/shortUrl", request, ShortUrlResponse.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(urlDictionaryRepository.findByOriginUrl(originUrl).isPresent()).isTrue();
    }

    @Test
    public void 다른_origin_URL로_요청시_다른_shortUrl_return() {
        String originUrl = "https://kakaopay.recruiter.co.kr/appsite/company/index";
        ShortUrlRequest request = new ShortUrlRequest(originUrl);

        String originUrl2 = "https://kakaopay.recruiter.co.kr/app/jobnotice/list";
        ShortUrlRequest request2 = new ShortUrlRequest(originUrl2);

        ResponseEntity<ShortUrlResponse> response = template().postForEntity("/api/v1/shortUrl", request, ShortUrlResponse.class);
        ResponseEntity<ShortUrlResponse> response2 = template().postForEntity("/api/v1/shortUrl", request2, ShortUrlResponse.class);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody().getShortUrl()).isNotEqualTo(response2.getBody().getShortUrl());
    }

    @Test
    public void 동일한_origin_URL로_요청시_동일한_shortUrl_return() {
        String originUrl = "https://scan.bezantium.com";
        ShortUrlRequest request = new ShortUrlRequest(originUrl);
        ResponseEntity<ShortUrlResponse> response = template().postForEntity("/api/v1/shortUrl", request, ShortUrlResponse.class);
        ResponseEntity<ShortUrlResponse> response2 = template().postForEntity("/api/v1/shortUrl", request, ShortUrlResponse.class);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(urlDictionaryRepository.count()).isEqualTo(1);
        softly.assertThat(response.getBody().getShortUrl()).isEqualTo(response2.getBody().getShortUrl());
    }

    @Test
    public void Request에_originUrl_null인경우_오류() {
        ShortUrlRequest request = new ShortUrlRequest(null);
        ResponseEntity<ShortUrlResponse> response = template().postForEntity("/api/v1/shortUrl", request, ShortUrlResponse.class);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
