package com.htp.domain;

import com.htp.exception.EntityNotFoundException;
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
public class Basket {

    private Map<Long, Integer> basketMap = Collections.synchronizedMap(new LinkedHashMap<>());

    public Map<Long, Integer> putIntoBasket(Long goodId, Integer count) {
        if (basketMap.containsKey(goodId)) {
            basketMap.replace(goodId, count + basketMap.get(goodId));
        } else {
            basketMap.put(goodId, count);
        }
        return basketMap;
    }

    public void clearBasket() {
        basketMap.clear();
    }

    public Map<Long, Integer> deleteItem(Long goodId) {
        if(basketMap.containsKey(goodId)) {
            basketMap.remove(goodId);
        } else {
            throw new EntityNotFoundException(Good.class, goodId);
        }
        return basketMap;
    }

    public Integer getSize() {
        return basketMap.size();
    }

    public Map<Long, Integer> showItemsInBasket() {
        return basketMap;
    }
}
