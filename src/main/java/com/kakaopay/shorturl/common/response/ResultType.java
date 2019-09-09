package com.kakaopay.shorturl.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum ResultType {

    SUCCESS_REQUEST("0000", HttpStatus.OK, "success request"),

    INVALID_SHORT_URL("4000", HttpStatus.BAD_REQUEST, "invalid short url"),
    INVALID_URL_FORMAT("4001", HttpStatus.BAD_REQUEST, "invalid url format"),
    URL_CANNOT_BE_EMPTY("4002", HttpStatus.INTERNAL_SERVER_ERROR, "url cannot be null");

    private final String code;
    private final HttpStatus httpStatus;
    private final String defaultMessage;

    ResultType(String code, HttpStatus httpStatus) {
        this(code, httpStatus, null);
    }

    ResultType(String code, HttpStatus httpStatus, String defaultMessage) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }

    public static ResultType of(String code) {
        return Arrays.stream(ResultType.values())
                .filter(v -> v.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
