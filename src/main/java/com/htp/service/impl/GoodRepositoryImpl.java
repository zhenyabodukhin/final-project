package com.htp.service.impl;

import com.htp.repository.GoodRepository;
import com.htp.domain.Good;
import com.htp.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("GoodRepositoryImpl")
public class GoodRepositoryImpl implements GoodService {

    public static final String GOOD_ID = "id";
    public static final String GOOD_NAME = "name";
    public static final String GOOD_PRICE = "good_price";
    public static final String GOOD_WEIGHT = "weight";
    public static final String SIZE_ID = "size_id";
    public static final String DOUGH_ID = "dough_id";


    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GoodRepositoryImpl(JdbcTemplate jdbcTemplate,
                              NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Good getGoodRowMapper(ResultSet set, int i) throws SQLException {
        Good good = new Good();
        good.setId(set.getLong(GOOD_ID));
        good.setGoodName(set.getString(GOOD_NAME));
        good.setGoodPrice(set.getDouble(GOOD_PRICE));
        good.setGoodWeight(set.getDouble(GOOD_WEIGHT));
        good.setSizeId(set.getLong(SIZE_ID));
        good.setDoughId(set.getLong(DOUGH_ID));
        return good;
    }

    @Override
    public List<Good> findAll() {
        final String findAllQuery = "select * from m_goods";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getGoodRowMapper);
    }

    @Override
    @Transactional
    public Good save(Good entity) {
        final String createQueryForGood = "INSERT INTO m_goods (name, good_price, weight, size_id, dough_id)" +
                "VALUES (:name, :good_price, :weight, :size_id, :dough_id);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramsForGood = new MapSqlParameterSource();
        paramsForGood.addValue(GOOD_NAME, entity.getGoodName());
        paramsForGood.addValue(GOOD_PRICE, entity.getGoodPrice());
        paramsForGood.addValue(GOOD_WEIGHT, entity.getGoodWeight());
        paramsForGood.addValue(SIZE_ID, entity.getSizeId());
        paramsForGood.addValue(DOUGH_ID, entity.getDoughId());
        namedParameterJdbcTemplate.update(createQueryForGood, paramsForGood, keyHolder, new String[]{"id"});

        return findById(keyHolder.getKey().longValue());
    }

    @Override
    @Transactional
    public Good update(Good entity) {
        final String createQuery = "UPDATE m_goods set name = :name, good_price = :good_price," +
                " weight = :weight, size_id = :size_id, dough_id = :dough_id where id = :id";

        MapSqlParameterSource paramsForGood = new MapSqlParameterSource();
        paramsForGood.addValue(GOOD_ID, entity.getId());
        paramsForGood.addValue(GOOD_NAME, entity.getGoodName());
        paramsForGood.addValue(GOOD_PRICE, entity.getGoodPrice());
        paramsForGood.addValue(GOOD_WEIGHT, entity.getGoodWeight());
        paramsForGood.addValue(SIZE_ID, entity.getSizeId());
        paramsForGood.addValue(DOUGH_ID, entity.getDoughId());

        namedParameterJdbcTemplate.update(createQuery, paramsForGood);
        return findById(entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        final String delete = "delete from m_goods where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(GOOD_ID, id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public Good findById(Long id) {
        final String findById = "select * from m_goods where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(GOOD_ID, id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getGoodRowMapper);
    }
}

