package com.kakaopay.shorturl.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlRequest {
    @NotNull
    private String originUrl;
}
