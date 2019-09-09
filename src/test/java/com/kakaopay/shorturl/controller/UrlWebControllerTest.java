package com.kakaopay.shorturl.controller;

import com.kakaopay.shorturl.service.ShortUrlService;
import com.kakaopay.shorturl.support.RestTemplateTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

public class UrlWebControllerTest extends RestTemplateTest {
    @MockBean
    private ShortUrlService shortUrlService;

    private final static String MOCK_SHORT_URL = "24a1b";

    private final static String MOCK_ORIGIN_URL = "https://kakaopay.recruiter.co.kr/appsite/company/index";

    @Before
    public void setUp(){
        when(shortUrlService.getOriginUrl(MOCK_SHORT_URL)).thenReturn(MOCK_ORIGIN_URL);
    }

    @Test
    public void shortUrl_Redirection_성공() {
        ResponseEntity<String> response = template().getForEntity(String.format("/%s", MOCK_SHORT_URL), String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        softly.assertThat(response.getHeaders().getLocation().toString()).isEqualTo(MOCK_ORIGIN_URL);
    }
}
