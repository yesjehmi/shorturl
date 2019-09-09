package com.kakaopay.shorturl.validator;

import com.kakaopay.shorturl.common.exception.ApplicationException;
import com.kakaopay.shorturl.common.response.ResultType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Component
public class UrlValidator {
    private static final Pattern URL_PATTERN = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)");

    public void validate(String value){
        if (StringUtils.isEmpty(value)) {
            throw new ApplicationException(ResultType.URL_CANNOT_BE_EMPTY);
        } else {
            if(!URL_PATTERN.matcher(value).find()){
                throw new ApplicationException(ResultType.INVALID_URL_FORMAT);
            }
        }
    }
}
