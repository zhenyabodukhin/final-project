package com.htp.service.impl;

import com.htp.controller.request.CustomGoodCreateRequest;
import com.htp.domain.Good;
import com.htp.domain.Price;
import com.htp.repository.GoodRepository;
import com.htp.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.htp.enums.Ingredients.*;

@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService {

    private final GoodRepository goodRepository;

    private final PriceServiceImpl priceService;

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
        goodRepository.deleteById(id);
    }

    @Override
    public Good findById(Long id) {
        return goodRepository.findById(id).get();
    }

    @Override
    public Good findGoodByName(String name) {
        return goodRepository.findGoodByName(name);
    }

    @Transactional
    @Override
    public Good createCustomGood(CustomGoodCreateRequest request) {

        String space = " ";
        String comma = ", ";

        Good good = new Good();

        good.setGoodName("Custom " + good.getId());
        good.setGoodWeight(0.8);
        good.setSizeId(request.getSizeId());
        good.setDoughId(request.getDoughId());
        good.setIngredients(request.getBacon() + space + BACON + comma +
                request.getBeef() + space + BEEF + comma +
                request.getChicken() + space + CHICKEN + comma +
                request.getPeperoni() + space + PEPPERONI + comma +
                request.getSalami() + space + SALAMI + comma +
                request.getSausages() + space + SAUSAGES + comma +
                request.getShrimp() + space + SHRIMP + comma +
                request.getTomato() + space + TOMATOES + comma +
                request.getPineapple() + space + PINEAPPLE + comma +
                request.getPepper() + space + PEPPER + comma +
                request.getOils() + space + OILS + comma +
                request.getMushrooms() + space + MUSHROOMS + comma +
                request.getCucumbers() + space + CUCUMBERS + comma +
                request.getCheeseFeta() + space + CHEESE_FETA + comma +
                request.getParmesan() + space + PARMESAN + comma +
                request.getMozzarella() + space + MOZZARELLA + comma +
                request.getDorBlue() + space + DOR_BLU);

        Price price = new Price();
        Double ingredientCost = priceService.findById(4L).getPrice();

        //total priceService.findById(((sizeService.findById(request.getSizeId()).getPriceId()))).getPrice()
        //                + priceService.findById(((doughTypeService.findById(request.getDoughId()).getPriceId()))).getPrice()

        Double totalIngredientsPrice = (request.getBacon() + request.getBeef() + request.getChicken() +
                request.getPeperoni() + request.getSalami() + request.getSausages() + request.getShrimp() +
                request.getTomato() + request.getPineapple() + request.getPepper() + request.getOils() +
                request.getMushrooms() + request.getCucumbers() + request.getCheeseFeta() + request.getParmesan() +
                request.getMozzarella() + request.getDorBlue()) * ingredientCost;

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

