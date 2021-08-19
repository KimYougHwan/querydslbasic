package com.swallaby.openrestapi.common;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheEventLog implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        log.info("cacheEventLog ======= key : {}, oldValue : {}, newValue : {}",
                cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }
}
