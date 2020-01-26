package com.htp.dao;

import com.htp.domain.User;
import com.htp.util.DatabasePropertiesUtil;
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

    private User fillObject(ResultSet set) throws SQLException {
        User user = new User();
        user.setId(set.getLong(1));
        user.setFirstName(set.getString(2));
        user.setLastName(set.getString(3));
        user.setBirthDate(set.getDate(4));
        user.setWeight(set.getDouble(5));
        return user;
    }

    @Override
    public List<User> findAll() {

        dataSource = init();
        try {
            //
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
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
