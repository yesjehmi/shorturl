package com.kakaopay.shorturl.repository;


import com.kakaopay.shorturl.domain.UrlDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlDictionaryRepository extends JpaRepository<UrlDictionary, Long> {
    Optional<UrlDictionary> findById(long id);

    Optional<UrlDictionary> findByOriginUrl(String originUrl);
}
