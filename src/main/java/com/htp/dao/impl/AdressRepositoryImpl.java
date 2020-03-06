package com.htp.dao.impl;

import com.htp.dao.AdressRepositoryDao;
import com.htp.domain.Adress;
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

@Repository("AdressRepositoryImpl")
@Transactional
public class AdressRepositoryImpl implements AdressRepositoryDao {

    public static final String ADRESS_ID = "id";
    public static final String STREET = "street";
    public static final String HOUSE_NUMBER = "n_house";
    public static final String FLAT_NUMBER = "n_flat";
    public static final String IS_PIZZA = "is_pizza";
    public static final String FLOOR_NUMBER = "n_floor";
    public static final String PORCH_NUMBER = "n_porch";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AdressRepositoryImpl(JdbcTemplate jdbcTemplate,
                                NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Adress getAdressRowMapper(ResultSet set, int i) throws SQLException {
        Adress adress = new Adress();
        adress.setId(set.getLong(ADRESS_ID));
        adress.setStreet(set.getString(STREET));
        adress.setHouseNumber(set.getString(HOUSE_NUMBER));
        adress.setFlatNumber(set.getInt(FLAT_NUMBER));
        adress.setIsPizzaAdress(set.getBoolean(IS_PIZZA));
        adress.setFloorNumber(set.getInt(FLOOR_NUMBER));
        adress.setPorchNumber(set.getInt(PORCH_NUMBER));
        return adress;
    }

    @Override
    public List<Adress> findAll() {
        final String findAllQuery = "select * from m_adress";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getAdressRowMapper);
    }

    @Override
    public Adress save(Adress entity) {
        final String createQueryForAdress = "INSERT INTO m_adress (street, n_house, n_flat, is_pizza, n_floor, n_porch)" +
                "VALUES (:street, :n_house, :n_flat, :is_pizza, :n_floor, :n_porch );";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramsForAdress = new MapSqlParameterSource();
        paramsForAdress.addValue(STREET, entity.getStreet());
        paramsForAdress.addValue(HOUSE_NUMBER, entity.getHouseNumber());
        paramsForAdress.addValue(FLAT_NUMBER, entity.getFlatNumber());
        paramsForAdress.addValue(IS_PIZZA, entity.getIsPizzaAdress());
        paramsForAdress.addValue(FLOOR_NUMBER, entity.getFloorNumber());
        paramsForAdress.addValue(PORCH_NUMBER, entity.getPorchNumber());
        namedParameterJdbcTemplate.update(createQueryForAdress, paramsForAdress, keyHolder, new String[]{"id"});

        return findById(keyHolder.getKey().longValue());
    }

    @Override
    public Adress update(Adress entity) {
        final String createQuery = "UPDATE m_adress set street = :street, n_house = :n_house," +
                " n_flat = :n_flat, is_pizza = :is_pizza, n_floor = :n_floor, n_porch = :n_porch where id = :id";

        MapSqlParameterSource paramsForAdress = new MapSqlParameterSource();
        paramsForAdress.addValue(ADRESS_ID, entity.getId());
        paramsForAdress.addValue(STREET, entity.getStreet());
        paramsForAdress.addValue(HOUSE_NUMBER, entity.getHouseNumber());
        paramsForAdress.addValue(FLAT_NUMBER, entity.getFlatNumber());
        paramsForAdress.addValue(IS_PIZZA, entity.getIsPizzaAdress());
        paramsForAdress.addValue(FLOOR_NUMBER, entity.getFloorNumber());
        paramsForAdress.addValue(PORCH_NUMBER, entity.getPorchNumber());

        namedParameterJdbcTemplate.update(createQuery, paramsForAdress);
        return findById(entity.getId());
    }

    @Override
    public void delete(Long id) {
        final String delete = "delete from m_adress where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ADRESS_ID, id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public Adress findById(Long id) {
        final String findById = "select * from m_adress where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ADRESS_ID, id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getAdressRowMapper);
    }
}
