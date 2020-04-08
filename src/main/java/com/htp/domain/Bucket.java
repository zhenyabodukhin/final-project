package com.htp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bucket {

    private Map<Long, Integer> bucketMap = Collections.synchronizedMap(new LinkedHashMap<>());

    public Map<Long, Integer> putIntoBucket(Long goodId, Integer count) {
        if (bucketMap.containsKey(goodId)) {
            bucketMap.replace(goodId, count + bucketMap.get(goodId));
        } else {
            bucketMap.put(goodId, count);
        }
        return bucketMap;
    }

    public void clearBucket() {
        bucketMap.clear();
    }

    public Map<Long, Integer> deleteItem(Long goodId) {
        bucketMap.remove(goodId);
        return bucketMap;
    }

    public Integer getSize() {
        return bucketMap.size();
    }

    public Map<Long, Integer> showItemsInBucket() {
        return bucketMap;
    }
}
