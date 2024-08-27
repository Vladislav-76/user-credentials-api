package com.userCredentials.userCredentialsAPI.db;

import com.userCredentials.userCredentialsAPI.models.User;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;
import java.util.Date;


public class ServiceDB {
//    final private static String SERVER_NAME = "192.168.88.56";
    final private static String SERVER_NAME = "localhost";
    final private static int PORT_NUMBER = 5432;
    final private static String DATABASE_NAME = "postgres_db";
    final private static String DATABASE_USER = "postgres";
    final private static String DATABASE_PASSWORD = "password";


    private static Connection getConnection() throws SQLException {
        try {
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setServerNames(new String[]{SERVER_NAME});
            dataSource.setPortNumbers(new int[]{PORT_NUMBER});
            dataSource.setDatabaseName(DATABASE_NAME);
            dataSource.setUser(DATABASE_USER);
            dataSource.setPassword(DATABASE_PASSWORD);
            return dataSource.getConnection();
        } catch (SQLException error) {
            System.out.println("Невозможно подключиться к БД");
            throw new SQLException(error);
        }
    }

    public User userSelect(String login) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = String.format(
                "SELECT password, email, date " +
                "FROM users_credentials " +
                "JOIN users_emails ON users_credentials.login = users_emails.login " +
                "WHERE users_credentials.login = '%s';", login
        );

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String password = resultSet.getString(1);
                String email = resultSet.getString(2);
                Date date = resultSet.getDate(3);
                return new User(login, password, email, date);
            } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь не найден");
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    public static void userInsert(User user) throws SQLException {
        String query = (
                "INSERT INTO users_credentials (login, password, date) VALUES (?, ?, NOW()); " +
                "INSERT INTO users_emails (login, email) VALUES (?, ?);"
        );
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

}
