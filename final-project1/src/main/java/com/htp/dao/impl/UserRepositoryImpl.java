package com.htp.dao.impl;


import com.htp.dao.UserRepositoryDao;
import com.htp.domain.User;
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

@Repository("UserRepositoryImpl")
public class UserRepositoryImpl implements UserRepositoryDao {

    public static final String USER_ID = "id";
    public static final String USER_NAME = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_CREATED = "created";
    public static final String USER_CHANGED = "changed";
    public static final String IS_DELETED = "is_deleted";

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate,
                       NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private User getUserRowMapper(ResultSet set, int i) throws SQLException {
        User user = new User();
        user.setId(set.getLong(USER_ID));
        user.setLogin(set.getString(USER_NAME));
        user.setPassword(set.getString(USER_PASSWORD));
        user.setCreated(set.getTimestamp(USER_CREATED));
        user.setChanged(set.getTimestamp(USER_CHANGED));
        user.setIs_deleted(set.getBoolean(IS_DELETED));
        return user;
    }


    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from m_users";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getUserRowMapper);
    }

    @Override
    public User save(User entity) {
        final String createQueryForUsers = "INSERT INTO m_users (login, password)" +
                "VALUES (:userName, :userPassword);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramsForUsers = new MapSqlParameterSource();
        paramsForUsers.addValue("userName", entity.getLogin());
        paramsForUsers.addValue("userPassword", entity.getPassword());
        namedParameterJdbcTemplate.update(createQueryForUsers, paramsForUsers, keyHolder);

        final String createQueryForRole = "INSERT INTO m_roles (user_name, user_id)" +
                "VALUES (:userName, :createdUserId);";

        MapSqlParameterSource paramsForRole= new MapSqlParameterSource();
        paramsForRole.addValue("userName", entity.getLogin());
        paramsForRole.addValue("createdUserId", findByName(entity.getLogin()).getId());
        namedParameterJdbcTemplate.update(createQueryForRole, paramsForRole, keyHolder);

        //long createdUserId = Objects.requireNonNull(keyHolder.getKey().longValue());

        return findById(findByName(entity.getLogin()).getId());
    }


    @Override
    public void delete(Long id) {

        final String delete = "UPDATE m_users set is_deleted = true where id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id);

        namedParameterJdbcTemplate.update(delete, params);

    }

    @Override
    public User findById(Long id) {

        final String findById = "select * from m_users where id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getUserRowMapper);
    }

    @Override
    public User findByName(String name) {

        final String findByName = "select * from m_users where login = :userName";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", name);

        return namedParameterJdbcTemplate.queryForObject(findByName, params, this::getUserRowMapper);
    }



    @Override
    public User update(User entity) {
        final String createQuery = "UPDATE m_users set login = :userName, password = :userPassword," +
                " created = :userCreated, changed = :changed, is_deleted = :deleted where id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", entity.getId());
        params.addValue("userName", entity.getLogin());
        params.addValue("userPassword", entity.getPassword());
        params.addValue("userCreated", entity.getCreated());
        params.addValue("changed", new Timestamp(new Date().getTime()));
        params.addValue("deleted", entity.isIs_deleted());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getId());
    }

    @Override
    public List<Long> batchUpdate(List<User> users) {return null;}
}