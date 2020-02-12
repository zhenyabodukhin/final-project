package com.htp.dao.impl;


import com.htp.dao.UserRepositoryDao;
import com.htp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryDao {

    public static final String USER_ID = "id";
    public static final String USER_NAME = "first_name";
    public static final String USER_SURNAME = "last_name";
    public static final String BIRTH_DATE = "birth_date";
    public static final String WEIGHT = "weight";

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate,
                       NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private User getUserRowMapper(ResultSet set) throws SQLException {
        User user = new User();
        user.setId(set.getLong("id"));
        user.setLogin(set.getString("login"));
        user.setPassword(set.getString("password"));
        user.setCreated(set.getDate("created"));
        user.setChanged(set.getDate("changed"));
        user.setIs_deleted(set.getBoolean("is_deleted"));
        return user;
    }


    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from m_users";
        return null; //namedParameterJdbcTemplate.query(findAllQuery, this::getUserRowMapper);
    }

    @Override
    public User save(User entity) {

        return null;
    }


    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(Long userId) {

    }

    @Override
    public List<Long> batchUpdate(List<User> users) {
        return null;
    }
}
