package com.htp.dao;

import com.htp.domain.User;
import com.htp.exception.NoSuchEntityException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private DataSource dataSource;

    public static final String url = "jdbc:postgresql://localhost:5432/postgres";
    public static final String username = "postgres";
    public static final String password = "root";
    public static final int initialSize = 10;

    private DataSource init() {
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setInitialSize(initialSize);
        return basicDataSource;
    }


    @Override
    public int save(User user) {

        return 0;
    }

    public static final String ID = "id";

    private User fillObject(ResultSet set) throws SQLException {
        User user = new User();
        user.setId(set.getLong(ID));
        user.setFirstName(set.getString("first_name"));
        user.setLastName(set.getString("last_name"));
        user.setBirthDate(set.getDate("birth_date"));
        user.setWeight(set.getDouble("weight"));
        return user;
    }

    @Override
    public List<User> findAll() {

        dataSource = init();
        Connection connection = null;
        try {
            //
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_users");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(fillObject(resultSet));
            }

            for (User user : users) {
                System.out.println(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User findOne(Long userId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Class.forName("org.postgresql.Driver");
            //
            PreparedStatement preparedStatement = connection.prepareStatement("select * from m_users where id = ?");
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println(fillObject(resultSet));
            } else {
                throw new NoSuchEntityException(String.format("User with id %s not found", userId));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("eroor!!");
        }
        return null;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public void delete(Long userId) {

    }
}
