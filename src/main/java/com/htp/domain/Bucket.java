package com.htp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class Bucket {

    private Map<String, String> bucket = new HashMap<>();

    public Integer putIntoBucket(String goodId, String count) {
        if (bucket.containsKey(goodId)) {
            int newCount = Integer.parseInt(count) + 1;
            bucket.replace(goodId, count, Integer.toString(newCount));
        } else {
            bucket.put(goodId, count);
        }
        return bucket.size();
    }

    public void clearBucket() {
        bucket.clear();
    }

    public void deleteItem(String goodId) {
        bucket.remove(goodId);
    }

    public Integer getSize() {
        return bucket.size();
    }

    public void showItemsInBucket() {
        System.out.println(bucket.toString());
    }

//    @Override
//    public String toString() {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String json = null;
//        try {
//            json = objectMapper.writeValueAsString(bucket);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return json;
//    }
}
