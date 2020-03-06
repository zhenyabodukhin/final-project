package com.htp.dao.impl;

import com.htp.dao.DoughTypeRepositoryDao;
import com.htp.domain.DoughType;
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

@Repository("DoughTypeRepositoryImpl")
@Transactional
public class DoughTypeRepositoryImpl implements DoughTypeRepositoryDao {

    public static final String DOUGH_ID = "id";
    public static final String DOUGH = "type";
    public static final String DOUGH_PRICE = "dough_price";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DoughTypeRepositoryImpl(JdbcTemplate jdbcTemplate,
                                   NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private DoughType getDoughTypeRowMapper(ResultSet set, int i) throws SQLException {
        DoughType type = new DoughType();
        type.setId(set.getLong(DOUGH_ID));
        type.setDoughType(set.getString(DOUGH));
        type.setDoughPrice(set.getDouble(DOUGH_PRICE));
        return type;
    }

    @Override
    public List<DoughType> findAll() {
        final String findAllQuery = "select * from m_dough_type";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getDoughTypeRowMapper);
    }

    @Override
    public DoughType save(DoughType entity) {
        final String createQueryForDough = "INSERT INTO m_dough_type (type, dough_price)" +
                "VALUES (:type, :dough_price);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramsForDough = new MapSqlParameterSource();
        paramsForDough.addValue(DOUGH, entity.getDoughType());
        paramsForDough.addValue(DOUGH_PRICE, entity.getDoughPrice());
        namedParameterJdbcTemplate.update(createQueryForDough, paramsForDough, keyHolder, new String[]{"id"});

        return findById(keyHolder.getKey().longValue());
    }

    @Override
    public DoughType update(DoughType entity) {
        final String createQuery = "UPDATE m_dough_type set type = :type, dough_price = :dough_price where id = :id";

        MapSqlParameterSource paramsForDough = new MapSqlParameterSource();
        paramsForDough.addValue(DOUGH_ID, entity.getId());
        paramsForDough.addValue(DOUGH, entity.getDoughType());
        paramsForDough.addValue(DOUGH_PRICE, entity.getDoughPrice());

        namedParameterJdbcTemplate.update(createQuery, paramsForDough);
        return findById(entity.getId());
    }

    @Override
    public void delete(Long id) {
        final String delete = "delete from m_dough_type where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(DOUGH_ID, id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public DoughType findById(Long id) {
        final String findById = "select * from m_dough_type where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(DOUGH_ID, id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getDoughTypeRowMapper);
    }
}