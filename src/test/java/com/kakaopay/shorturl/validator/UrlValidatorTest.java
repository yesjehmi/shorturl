package com.kakaopay.shorturl.validator;

import com.kakaopay.shorturl.common.exception.ApplicationException;
import com.kakaopay.shorturl.common.response.ResultType;
import com.kakaopay.shorturl.support.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UrlValidatorTest extends BaseTest {

    @InjectMocks
    private UrlValidator urlValidator;

    @Test
    public void originUrl이_Null일때_Exception() {
        try {
            urlValidator.validate(null);
        } catch (ApplicationException ex) {
            softly.assertThat(ex.getResultType()).isEqualTo(ResultType.URL_CANNOT_BE_EMPTY);
        }
    }

    @Test
    public void 유효하지않은_originUrl() {
        try {
            urlValidator.validate("aabb");
        } catch (ApplicationException ex) {
            softly.assertThat(ex.getResultType()).isEqualTo(ResultType.INVALID_URL_FORMAT);
        }
    }

    @Test
    public void 정상적인_URL_SUCCESS() {
        urlValidator.validate("https://www.daum.net/");
        urlValidator.validate("https://kakaopay.recruiter.co.kr/appsite/company/index");
    }
}
