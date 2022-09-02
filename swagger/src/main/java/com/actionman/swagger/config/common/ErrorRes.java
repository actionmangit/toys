package com.actionman.swagger.config.common;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorRes {
    
    private String code;
    private String msg;
}
