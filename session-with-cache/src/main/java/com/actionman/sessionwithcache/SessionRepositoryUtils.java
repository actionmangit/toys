package com.actionman.sessionwithcache;

import javax.annotation.PostConstruct;

import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SessionRepositoryUtils {

    private final FindByIndexNameSessionRepository sessionRepository;
    
    public static FindByIndexNameSessionRepository repository;

    @PostConstruct
    void init() {
        repository = this.sessionRepository;
    }
}
