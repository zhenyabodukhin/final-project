package com.htp.dao.impl;


import com.htp.dao.OrderRepositoryDao;
import com.htp.domain.Order;
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
import java.util.Objects;

@Repository("OrderRepositoryImpl")
public class OrderRepositoryImpl implements OrderRepositoryDao {

    public static final String ORDER_ID = "id";
    public static final String USER_ID = "user_id";
    public static final String ADRESS_ID = "adress_id";
    public static final String ORDER_TIME = "time";
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
        order.setUser_id(set.getLong(USER_ID));
        order.setAdress_id(set.getLong(ADRESS_ID));
        order.setTime(set.getTimestamp(ORDER_TIME));
        order.setIs_done(set.getBoolean(IS_DONE));
        return order;
    }

    @Override
    public List findAll() {
        final String findAllQuery = "select * from m_orders";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getOrderRowMapper);
    }

    @Override
    public Order save(Order entity) {
        final String createQueryForOrders = "INSERT INTO m_orders (user_id, adress_id)" +
                "VALUES (:userId, :adressId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramsForUsers = new MapSqlParameterSource();
        paramsForUsers.addValue("userId", entity.getUser_id());
        paramsForUsers.addValue("adressId", entity.getAdress_id());
        namedParameterJdbcTemplate.update(createQueryForOrders, paramsForUsers, keyHolder, new String[] { "id" });

        return findById(keyHolder.getKey().longValue());
    }

    @Override
    public Order update(Order entity) {
        final String createQuery = "UPDATE m_orders set user_id = :userId, adress_id = :adressId," +
                " time = :orderTime, is_done = :isDone where id = :orderId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("orderId", entity.getId());
        params.addValue("userId", entity.getUser_id());
        params.addValue("adressId", entity.getAdress_id());
        params.addValue("orderTime", new Timestamp(new Date().getTime()));
        params.addValue("isDone", entity.getIs_done());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getId());
    }

    @Override
    public void delete(Long id) {
        final String delete = "delete from m_orders where id = :orderId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("orderId", id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public void setOrderDone(Long id) {
        final String delete = "UPDATE m_orders set is_done = true where id = :orderId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("orderId", id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public Order findById(Long id) {
        final String findById = "select * from m_orders where id = :orderId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("orderId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getOrderRowMapper);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        final String findByUserId = "select * from m_orders where user_id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id);

        return namedParameterJdbcTemplate.query(findByUserId, params, this::getOrderRowMapper);
    }

    @Override
    public List<Order> findIsDone(boolean value) {
        final String findIsDone = "select * from m_orders where is_done = :isDone";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("isDone", value);

        return namedParameterJdbcTemplate.query(findIsDone, params, this::getOrderRowMapper);
    }
}
