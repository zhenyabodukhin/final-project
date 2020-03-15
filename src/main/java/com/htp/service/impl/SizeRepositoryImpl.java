package com.htp.service.impl;

import com.htp.repository.SizeRepository;
import com.htp.domain.Size;
import com.htp.service.SizeService;
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

@Repository("SizeRepositoryImpl")
public class SizeRepositoryImpl implements SizeService {

    public static final String SIZE_ID = "id";
    public static final String SIZE_COUNT = "size_count";
    public static final String SIZE_PRICE = "price";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public SizeRepositoryImpl(JdbcTemplate jdbcTemplate,
                              NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Size getSizeRowMapper(ResultSet set, int i) throws SQLException {
        Size size = new Size();
        size.setId(set.getLong(SIZE_ID));
        size.setSizeCount(set.getInt(SIZE_COUNT));
        size.setSizePrice(set.getDouble(SIZE_PRICE));
        return size;
    }

    @Override
    public List<Size> findAll() {
        final String findAllQuery = "select * from m_size";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getSizeRowMapper);
    }

    @Override
    @Transactional
    public Size save(Size entity) {
        final String createQueryForSize = "INSERT INTO m_size (size_count, size_price)" +
                "VALUES (:size_count, :price);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramsForSize = new MapSqlParameterSource();
        paramsForSize.addValue(SIZE_COUNT, entity.getSizeCount());
        paramsForSize.addValue(SIZE_PRICE, entity.getSizePrice());
        namedParameterJdbcTemplate.update(createQueryForSize, paramsForSize, keyHolder, new String[]{"id"});

        return findById(keyHolder.getKey().longValue());
    }

    @Override
    @Transactional
    public Size update(Size entity) {
        final String createQuery = "UPDATE m_size set size_count = :size_count, size_price = :price where id = :id";

        MapSqlParameterSource paramsForSize = new MapSqlParameterSource();
        paramsForSize.addValue(SIZE_ID, entity.getId());
        paramsForSize.addValue(SIZE_COUNT, entity.getSizeCount());
        paramsForSize.addValue(SIZE_PRICE, entity.getSizePrice());

        namedParameterJdbcTemplate.update(createQuery, paramsForSize);
        return findById(entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        final String delete = "delete from m_size where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(SIZE_ID, id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public Size findById(Long id) {
        final String findById = "select * from m_size where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(SIZE_ID, id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getSizeRowMapper);
    }
}

