package ua.nure.krivchenko.ua.nure.krivchenko.db;

// интерфейс фабрики
// при запросе создает новый объект connection
import java.sql.Connection;

public interface ConnectionFactory {
    Connection createConnection() throws DatabaseException;

}
