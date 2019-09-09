package com.kakaopay.shorturl.common.exception;

import com.kakaopay.shorturl.common.response.ResultType;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ApplicationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 3713800883410031528L;

    private ResultType resultType;

    public ApplicationException(ResultType resultType) {
        super(resultType.name());
        this.resultType = resultType;
    }
}
