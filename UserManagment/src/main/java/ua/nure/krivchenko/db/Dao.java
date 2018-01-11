package ua.nure.krivchenko.ua.nure.krivchenko.db;

import ua.nure.krivchenko.User;

import java.util.Collection;

public interface DAO<T> {

    User create(T user) throws DatabaseException;

    void update(T user) throws DatabaseException;

    void delete(T user) throws DatabaseException;

    User find(Long id) throws DatabaseException;

    Collection findAll() throws DatabaseException;

    public void setConnectionFactory(ConnectionFactory connectionFactory); // метод установки соеденения

}
