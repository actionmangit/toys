package com.actionman.sessionwithcache.inbound.sample;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class SampleDto implements Serializable {
    String id;
    String name;
}
