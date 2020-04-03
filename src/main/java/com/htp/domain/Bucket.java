package com.htp.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Bucket {

    private HashMap<Long, Integer> bucket = new HashMap<>();

    public void putIntoBucket(Long goodId, Integer count) {
        bucket.put(goodId, count);
    }

    public void clearBucket() {
        bucket.clear();
    }

    public void deleteItem(Long goodId) {
        bucket.remove(goodId);
    }

    public Integer getSize() {
        return bucket.size();
    }
}
