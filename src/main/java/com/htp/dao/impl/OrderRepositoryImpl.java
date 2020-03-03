package com.htp.dao.impl;


import com.htp.dao.OrderRepositoryDao;
import com.htp.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository("OrderRepositoryImpl")
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryDao {

    public static final String ORDER_ID = "id";
    public static final String USER_ID = "user_id";
    public static final String ADRESS_ID = "adress_id";
    public static final String ORDER_TIME = "time";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String IS_DONE = "is_done";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate,
                               NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Order getOrderRowMapper(ResultSet set, int i) throws SQLException {
        Order order = new Order();
        order.setId(set.getLong(ORDER_ID));
        order.setUserId(set.getLong(USER_ID));
        order.setAdressId(set.getLong(ADRESS_ID));
        order.setTime(set.getTimestamp(ORDER_TIME));
        order.setPhoneNumber(set.getString(PHONE_NUMBER));
        order.setDone(set.getBoolean(IS_DONE));
        return order;
    }

    @Override
    public List findAll() {
        final String findAllQuery = "select * from m_orders";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getOrderRowMapper);
    }

    @Override
    public Order save(Order entity) {
        final String createQueryForOrders = "INSERT INTO m_orders (user_id, adress_id, phone_number)" +
                "VALUES (:user_id, :adress_id, :phone_number );";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramsForOrders = new MapSqlParameterSource();
        paramsForOrders.addValue(USER_ID, entity.getUserId());
        paramsForOrders.addValue(ADRESS_ID, entity.getAdressId());
        paramsForOrders.addValue(PHONE_NUMBER, entity.getPhoneNumber());
        namedParameterJdbcTemplate.update(createQueryForOrders, paramsForOrders, keyHolder, new String[] { "id" });

        return findById(keyHolder.getKey().longValue());
    }

    @Override
    public Order update(Order entity) {
        final String createQuery = "UPDATE m_orders set user_id = :user_id, adress_id = :adress_id," +
                " time = :time, phone_number = :phone_number, is_done = :is_done where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ORDER_ID, entity.getId());
        params.addValue(USER_ID, entity.getUserId());
        params.addValue(ADRESS_ID, entity.getAdressId());
        params.addValue(ORDER_TIME, new Timestamp(new Date().getTime()));
        params.addValue(PHONE_NUMBER, entity.getPhoneNumber());
        params.addValue(IS_DONE, entity.isDone());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getId());
    }

    @Override
    public void delete(Long id) {
        final String delete = "delete from m_orders where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ORDER_ID, id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public void setOrderDone(Long id) {
        final String setDone = "UPDATE m_orders set is_done = true where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ORDER_ID, id);

        namedParameterJdbcTemplate.update(setDone, params);
    }

    @Override
    public Order findById(Long id) {
        final String findById = "select * from m_orders where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ORDER_ID, id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getOrderRowMapper);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        final String findByUserId = "select * from m_orders where user_id = :user_id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(USER_ID, id);

        return namedParameterJdbcTemplate.query(findByUserId, params, this::getOrderRowMapper);
    }

    @Override
    public List<Order> findIsDone(boolean value) {
        final String findIsDone = "select * from m_orders where is_done = :is_done";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(IS_DONE, value);

        return namedParameterJdbcTemplate.query(findIsDone, params, this::getOrderRowMapper);
    }
}
