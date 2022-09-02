package com.actionman.sessionwithcache.inbound.sample;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.actionman.sessionwithcache.SessionRepositoryUtils;
import com.actionman.sessionwithcache.domain.cache.CacheService;

import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
class SampleController {

    private final CacheService cacheService;

    @GetMapping("/test")
    void findSession() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        
        // @formatter:off
        SampleDto dto = SampleDto.builder()
                                 .id("sam")
                                 .name("name")
                                 .build();
        // @formatter:on

        WebUtils.setSessionAttribute(request, "key", dto);
        WebUtils.setSessionAttribute(request, FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, dto.getId());

        String id = (String) WebUtils.getSessionAttribute(request, FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME);

        cacheService.getCache("test");
    }

    @GetMapping("/test1")
    void findSession1() {

        Map<String, Session> map = SessionRepositoryUtils.repository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "sam");

        SessionRepositoryUtils.repository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "sam")
                            .keySet().forEach(
                                s -> {
                                    log.info((String) s);
                                    SessionRepositoryUtils.repository.deleteById((String) s);
                                });
    }
}
