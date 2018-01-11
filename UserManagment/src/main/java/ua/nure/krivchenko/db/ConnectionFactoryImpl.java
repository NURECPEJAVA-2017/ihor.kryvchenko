package ua.nure.krivchenko.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImplementation implements ConnectionFactory { // интерфейс реализующий фабрику

    private String driver;
    private String url;
    private String user;
    private String password;

    public ConnectionFactoryImplementation(String driver, String url, String user, String password) { // конструктор для тестов
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public ConnectionFactoryImplementation(Properties properties){
        driver = properties.getProperty("connection.driver");
        url = properties.getProperty("connection.url");
        user = properties.getProperty("connection.user");
        password = properties.getProperty("connection.password");
    }

    public Connection createConnection() throws DatabaseException {
        try {
            Class.forName(driver); //пытается загрузить нужный нам класс по имени
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(); // оператор который вызывает исключ. ситуацию
            //RunTimeException  - нельзя перехватить и исправить
        }

        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new DatabaseException(e); // DataBaseException можно перехватить
        }
    }
}
