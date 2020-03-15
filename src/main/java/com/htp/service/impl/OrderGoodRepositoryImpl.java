package com.htp.service.impl;

import com.htp.repository.OrderGoodRepository;
import com.htp.domain.OrderGood;
import com.htp.service.OrderGoodService;
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

@Repository("OrderGoodRepositoryImpl")
public class OrderGoodRepositoryImpl implements OrderGoodService {

    public static final String ORDER_GOOD_ID = "id";
    public static final String ORDER_ID = "order_id";
    public static final String GOOD_ID = "good_id";
    public static final String GOOD_COUNT = "count";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OrderGoodRepositoryImpl(JdbcTemplate jdbcTemplate,
                                   NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private OrderGood getOrderGoodRowMapper(ResultSet set, int i) throws SQLException {
        OrderGood orderGood = new OrderGood();
        orderGood.setId(set.getLong(ORDER_GOOD_ID));
        orderGood.setOrderId(set.getLong(ORDER_ID));
        orderGood.setGoodId(set.getLong(GOOD_ID));
        orderGood.setGoodCount(set.getInt(GOOD_COUNT));
        return orderGood;
    }

    @Override
    public List<OrderGood> findAll() {
        final String findAllQuery = "select * from order_goods";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getOrderGoodRowMapper);
    }

    @Override
    @Transactional
    public OrderGood save(OrderGood entity) {
        final String createQueryForOrderGood = "INSERT INTO order_goods (order_id, good_id, count)" +
                "VALUES (:order_id, :good_id, :count);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramsForOrderGood = new MapSqlParameterSource();
        paramsForOrderGood.addValue(ORDER_ID, entity.getOrderId());
        paramsForOrderGood.addValue(GOOD_ID, entity.getGoodId());
        paramsForOrderGood.addValue(GOOD_COUNT, entity.getGoodCount());
        namedParameterJdbcTemplate.update(createQueryForOrderGood, paramsForOrderGood, keyHolder, new String[]{"id"});

        return findById(keyHolder.getKey().longValue());
    }

    @Override
    @Transactional
    public OrderGood update(OrderGood entity) {
        final String createQuery = "UPDATE order_goods set order_id = :order_id, good_id = :good_id, count = :count " +
                "where id = :id";

        MapSqlParameterSource paramsForOrderGood = new MapSqlParameterSource();
        paramsForOrderGood.addValue(ORDER_GOOD_ID, entity.getId());
        paramsForOrderGood.addValue(ORDER_ID, entity.getOrderId());
        paramsForOrderGood.addValue(GOOD_ID, entity.getGoodId());
        paramsForOrderGood.addValue(GOOD_COUNT, entity.getGoodCount());

        namedParameterJdbcTemplate.update(createQuery, paramsForOrderGood);
        return findById(entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        final String delete = "delete from order_goods where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ORDER_GOOD_ID, id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public OrderGood findById(Long id) {
        final String findById = "select * from order_goods where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ORDER_GOOD_ID, id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getOrderGoodRowMapper);
    }
}

