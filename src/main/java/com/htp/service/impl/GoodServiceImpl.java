package com.htp.service.impl;

import com.htp.controller.request.CustomGoodCreateRequest;
import com.htp.domain.CustomGood;
import com.htp.domain.Good;
import com.htp.domain.Price;
import com.htp.exception.EntityNotFoundException;
import com.htp.repository.GoodRepository;
import com.htp.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService {

    private static final Double CUSTOM_WEIGHT = 0.8;
    private static final Long BEEF_COST_ID = 4L;
    private static final Long VEGETABLE_COST_ID = 5L;
    private static final Long CHEESE_COST_ID = 6L;

    private final GoodRepository goodRepository;

    private final PriceServiceImpl priceService;

    private final CustomGood customGood;

    @Override
    public List<Good> findAll() {
        return goodRepository.findAll();
    }

    @Transactional
    @Override
    public Good save(Good good) {
        return goodRepository.save(good);
    }

    @Transactional
    @Override
    public Good update(Good good) {
        return goodRepository.saveAndFlush(good);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (goodRepository.findById(id).isPresent()) {
            goodRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(Good.class, id);
        }
    }

    @Override
    public Good findById(Long id) {
        Optional<Good> result = goodRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(Good.class, id);
        }
    }

    @Override
    public Good findGoodByName(String name) {
        return goodRepository.findGoodByName(name);
    }

    @Transactional
    @Override
    public Good createCustomGood(CustomGoodCreateRequest request) {
        customGood.putRequest(request);
        customGood.fillStringBuilder(customGood.getBeefIngredients());
        customGood.fillStringBuilder(customGood.getCheeseIngredients());
        customGood.fillStringBuilder(customGood.getVegetableIngredients());

        Good good = new Good();
        good.setGoodName("Custom ");
        good.setGoodWeight(CUSTOM_WEIGHT);
        good.setSizeId(request.getSizeId());
        good.setDoughId(request.getDoughId());
        good.setIngredients(customGood.getSb().toString());

        Price price = new Price();
        Double beefCost = priceService.findById(BEEF_COST_ID).getPrice();
        Double vegetableCost = priceService.findById(VEGETABLE_COST_ID).getPrice();
        Double cheeseCost = priceService.findById(CHEESE_COST_ID).getPrice();

        Double totalIngredientsPrice = customGood.getBeefTotalPrice(customGood.getBeefIngredients()) * beefCost
                + customGood.getCheeseTotalPrice(customGood.getCheeseIngredients()) * cheeseCost
                + customGood.getVegetableTotalPrice(customGood.getVegetableIngredients()) * vegetableCost;

        if (priceService.findPriceByValue(totalIngredientsPrice) != null) {
            good.setPriceId(priceService.findPriceByValue(totalIngredientsPrice).getId());
        } else {
            price.setPrice(totalIngredientsPrice);
            Price createdPrice = priceService.save(price);
            good.setPriceId(createdPrice.getId());
        }

        Long createdGoodId = save(good).getId();
        good.setGoodName("Custom " + createdGoodId);

        return update(good);
    }
}

