package com.kakaopay.shorturl.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UrlDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String originUrl;

    private void setId(Long id) {
        this.id = id;
    }

    private void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public static UrlDictionary createInstance(String originUrl) {
        UrlDictionary urlDictionary = new UrlDictionary();
        urlDictionary.setOriginUrl(originUrl);
        return urlDictionary;
    }

    public static UrlDictionary createInstance(long id, String originUrl) {
        UrlDictionary urlDictionary = new UrlDictionary();
        urlDictionary.setId(id);
        urlDictionary.setOriginUrl(originUrl);
        return urlDictionary;
    }
}
