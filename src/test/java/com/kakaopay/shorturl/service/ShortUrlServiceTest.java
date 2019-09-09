package com.kakaopay.shorturl.service;

import com.kakaopay.shorturl.common.exception.ApplicationException;
import com.kakaopay.shorturl.domain.UrlDictionary;
import com.kakaopay.shorturl.repository.UrlDictionaryRepository;
import com.kakaopay.shorturl.support.BaseTest;
import com.kakaopay.shorturl.validator.UrlValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ShortUrlServiceTest extends BaseTest {

    @Mock
    private UrlDictionaryRepository urlDictionaryRepository;

    @Mock
    private UrlValidator urlValidator;

    @InjectMocks
    private ShortUrlService shortUrlService;

    private static final String MOCK_TEST_SHORT_URL = "b";
    private static final String MOCK_TEST_ORIGIN_URL = "https://kakaopay.recruiter.co.kr/appsite/company/index";
    private static final String MOCK_TEST_2ND_ORIGIN_URL = "https://daum.net";

    @Before
    public void setUp() {
        UrlDictionary urlDictionary = UrlDictionary.createInstance(1,MOCK_TEST_ORIGIN_URL);
        UrlDictionary url2ndDictionary = UrlDictionary.createInstance(2,MOCK_TEST_2ND_ORIGIN_URL);

        when(urlDictionaryRepository.findByOriginUrl(MOCK_TEST_ORIGIN_URL)).thenReturn(Optional.of(urlDictionary));
        when(urlDictionaryRepository.findByOriginUrl(MOCK_TEST_2ND_ORIGIN_URL)).thenReturn(Optional.of(url2ndDictionary));

        when(urlDictionaryRepository.findById(1)).thenReturn(Optional.of(urlDictionary));

        doNothing().when(urlValidator).validate(MOCK_TEST_ORIGIN_URL);
        doNothing().when(urlValidator).validate(MOCK_TEST_2ND_ORIGIN_URL);
    }

    @Test
    public void 동일한_originUrl_값에_대해_다른_shortUrl을_반환하는지_확인() {
        String shortUrl = shortUrlService.getShortUrl(MOCK_TEST_ORIGIN_URL);
        String shortUrl2 = shortUrlService.getShortUrl(MOCK_TEST_2ND_ORIGIN_URL);

        softly.assertThat(shortUrl).isNotEqualTo(shortUrl2);
    }

    @Test(expected = ApplicationException.class)
    public void getOriginUrl_시_등록되지_않은_shortUrl이면_Exception() {
        shortUrlService.getOriginUrl("failTest");
    }

    @Test
    public void getOriginUrl_성공() {
        String originUrl = shortUrlService.getOriginUrl(MOCK_TEST_SHORT_URL);
        softly.assertThat(originUrl).isEqualTo(MOCK_TEST_ORIGIN_URL);
    }
}
