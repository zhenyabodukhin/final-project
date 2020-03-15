package com.htp.service.impl;

import com.htp.repository.RoleRepository;
import com.htp.domain.Role;
import com.htp.service.RoleService;
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

@Repository("RoleRepositoryImpl")
public class RoleRepositoryImpl implements RoleService {

    public static final String ROLE_ID = "id";
    public static final String USER_ID = "user_id";
    public static final String ROLE_TYPE = "role";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public RoleRepositoryImpl(JdbcTemplate jdbcTemplate,
                              NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Role getRoleRowMapper(ResultSet set, int i) throws SQLException {
        Role role = new Role();
        role.setId(set.getLong(ROLE_ID));
        role.setUserId(set.getLong(USER_ID));
        role.setRole(set.getString(ROLE_TYPE));
        return role;
    }

    @Override
    public List<Role> findAll() {
        final String findAllQuery = "select * from m_roles";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getRoleRowMapper);
    }

    @Override
    @Transactional
    public Role save(Role entity) {
        final String createQueryForRole = "INSERT INTO m_roles (user_id, role)" +
                "VALUES (:user_id, :role);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource paramsForRole = new MapSqlParameterSource();
        paramsForRole.addValue(USER_ID, entity.getUserId());
        paramsForRole.addValue(ROLE_TYPE, entity.getRole());
        namedParameterJdbcTemplate.update(createQueryForRole, paramsForRole, keyHolder, new String[]{"id"});

        return findById(keyHolder.getKey().longValue());
    }

    @Override
    @Transactional
    public Role update(Role entity) {
        final String createQuery = "UPDATE m_roles set user_id = :user_id, role = :role where id = :id";

        MapSqlParameterSource paramsForRole = new MapSqlParameterSource();
        paramsForRole.addValue(ROLE_ID, entity.getId());
        paramsForRole.addValue(USER_ID, entity.getUserId());
        paramsForRole.addValue(ROLE_TYPE, entity.getRole());

        namedParameterJdbcTemplate.update(createQuery, paramsForRole);
        return findById(entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        final String delete = "delete from m_roles where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ROLE_ID, id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public Role findById(Long id) {
        final String findById = "select * from m_roles where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ROLE_ID, id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getRoleRowMapper);
    }
}

