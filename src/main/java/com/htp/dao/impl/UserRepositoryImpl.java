package com.htp.dao.impl;


import com.htp.dao.UserRepositoryDao;
import com.htp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository("UserRepositoryImpl")
@Transactional
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
        user.setDeleted(set.getBoolean(IS_DELETED));
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
                "VALUES (:login, :password);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramsForUsers = new MapSqlParameterSource();
        paramsForUsers.addValue(USER_NAME, entity.getLogin());
        paramsForUsers.addValue(USER_PASSWORD, entity.getPassword());
        namedParameterJdbcTemplate.update(createQueryForUsers, paramsForUsers, keyHolder, new String[] { "id" });

        final String createQueryForRole = "INSERT INTO m_roles (user_name, user_id)" +
                "VALUES (:login, :createdUserId);";

        MapSqlParameterSource paramsForRole= new MapSqlParameterSource();
        paramsForRole.addValue(USER_NAME, entity.getLogin());
        paramsForRole.addValue("createdUserId", findByName(entity.getLogin()).getId());
        namedParameterJdbcTemplate.update(createQueryForRole, paramsForRole, keyHolder, new String[] { "id" });

        return findById(keyHolder.getKey().longValue());
    }


    @Override
    public void delete(Long id) {

        final String delete = "UPDATE m_users set is_deleted = true where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(USER_ID, id);

        namedParameterJdbcTemplate.update(delete, params);

    }

    @Override
    public User findById(Long id) {

        final String findById = "select * from m_users where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(USER_ID, id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getUserRowMapper);
    }

    @Override
    public User findByName(String name) {

        final String findByName = "select * from m_users where login = :login";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(USER_NAME, name);

        return namedParameterJdbcTemplate.queryForObject(findByName, params, this::getUserRowMapper);
    }

    @Override
    public List<User> findContainsValue(String value) {
        final String findContainsValue = "select * from m_users where login like :value";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("value", value);

        return namedParameterJdbcTemplate.query(findContainsValue, params, this::getUserRowMapper);
    }

    @Override
    public List<User> findIsDeleted(boolean value) {
        final String findIsDeleted = "select * from m_users where is_deleted = :is_deleted";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(IS_DELETED, value);

        return namedParameterJdbcTemplate.query(findIsDeleted, params, this::getUserRowMapper);
    }


    @Override
    public User update(User entity) {
        final String createQuery = "UPDATE m_users set login = :login, password = :password," +
                " created = :created, changed = :changed, is_deleted = :is_deleted where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(USER_ID, entity.getId());
        params.addValue(USER_NAME, entity.getLogin());
        params.addValue(USER_PASSWORD, entity.getPassword());
        params.addValue(USER_CREATED, entity.getCreated());
        params.addValue(USER_CHANGED, new Timestamp(new Date().getTime()));
        params.addValue(IS_DELETED, entity.isDeleted());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getId());
    }

    @Override
    public List<Long> batchUpdate(List<User> users) {
        final String createQuery = "UPDATE m_users set login = :login, password = :password," +
                " created = :created, changed = :changed, is_deleted = :is_deleted where id = :id";

        List<SqlParameterSource> batch = new ArrayList<>();
        for (User user : users) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(USER_ID, user.getId());
            params.addValue(USER_NAME, user.getLogin());
            params.addValue(USER_PASSWORD, user.getPassword());
            params.addValue(USER_CREATED, user.getCreated());
            params.addValue(USER_CHANGED, new Timestamp(new Date().getTime()));
            params.addValue(IS_DELETED, user.isDeleted());
            batch.add(params);
        }

        namedParameterJdbcTemplate.batchUpdate(createQuery, batch.toArray(new SqlParameterSource[batch.size()]));
        return users.stream().map(User::getId).collect(Collectors.toList());
    }
}
