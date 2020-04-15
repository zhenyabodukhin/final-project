package com.htp.domain;

import com.htp.controller.request.CustomGoodCreateRequest;
import com.htp.enums.Ingredients;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.htp.enums.Ingredients.*;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomGood {

    private Map<Ingredients, Integer> beefIngredients = new LinkedHashMap<>();

    private Map<Ingredients, Integer> cheeseIngredients = new LinkedHashMap<>();

    private Map<Ingredients, Integer> vegetableIngredients = new LinkedHashMap<>();

    private StringBuilder sb = new StringBuilder();

    private static final String SPACE = " ";

    private static final String COMMA = ", ";

    private void putBeef(CustomGoodCreateRequest request) {
        beefIngredients.put(BACON, request.getBacon());
        beefIngredients.put(BEEF, request.getBeef());
        beefIngredients.put(CHICKEN, request.getChicken());
        beefIngredients.put(PEPPERONI, request.getPeperoni());
        beefIngredients.put(SALAMI, request.getSalami());
        beefIngredients.put(SAUSAGES, request.getSausages());
        beefIngredients.put(SHRIMP, request.getShrimp());
    }

    private void putCheese(CustomGoodCreateRequest request) {
        cheeseIngredients.put(CHEESE_FETA, request.getCheeseFeta());
        cheeseIngredients.put(DOR_BLU, request.getDorBlue());
        cheeseIngredients.put(MOZZARELLA, request.getMozzarella());
        cheeseIngredients.put(PARMESAN, request.getParmesan());
    }

    private void putVegetable(CustomGoodCreateRequest request) {
        vegetableIngredients.put(CUCUMBERS, request.getCucumbers());
        vegetableIngredients.put(MUSHROOMS, request.getMushrooms());
        vegetableIngredients.put(OILS, request.getOils());
        vegetableIngredients.put(PEPPER, request.getPepper());
        vegetableIngredients.put(PINEAPPLE, request.getPineapple());
        vegetableIngredients.put(TOMATOES, request.getTomato());
    }

    public void putRequest(CustomGoodCreateRequest request) {
        putBeef(request);
        putCheese(request);
        putVegetable(request);
    }

    public void fillStringBuilder(Map<Ingredients, Integer> map) {
        for (Map.Entry<Ingredients, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                sb.append(entry.getKey())
                        .append(SPACE)
                        .append(entry.getValue())
                        .append(COMMA);
            }
        }
    }

    public Double getBeefTotalPrice(Map<Ingredients, Integer> map) {
        Double beefTotalPrice = 0.0;
        for (Map.Entry<Ingredients, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                beefTotalPrice += entry.getValue();
            }
        }
        return beefTotalPrice;
    }

    public Double getCheeseTotalPrice(Map<Ingredients, Integer> map) {
        Double cheeseTotalPrice = 0.0;
        for (Map.Entry<Ingredients, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                cheeseTotalPrice += entry.getValue();
            }
        }
        return cheeseTotalPrice;
    }

    public Double getVegetableTotalPrice(Map<Ingredients, Integer> map) {
        Double vegetableTotalPrice = 0.0;
        for (Map.Entry<Ingredients, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                vegetableTotalPrice += entry.getValue();
            }
        }
        return vegetableTotalPrice;
    }
}
