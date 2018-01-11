package ua.nure.krivchenko.ua.nure.krivchenko.db;

import java.io.IOException;
import java.util.Properties;


/*
    РЕАЛИЗАЦИЯ SINGLETON
 */

public abstract class DaoFactory {

    private static DaoFactory instance; //ссылка на единственный экземпляр класс
    protected static Properties properties;

    protected DaoFactory(){}; // защищеный конструктор


    static {        // статический блок,выполняется когда jvm загружает файл в память
        properties = new Properties();  //класс позволяющий работать с файлами типа properties
        try {
            properties.load(DaoFactory.class
                            .getClassLoader()
                            .getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(); //ошибка того уровня - на котором мы ничего не можем сделать
        }
    }

    // метод возвращающий экземпляр нашей фабрики
    public static synchronized DaoFactory getInstance() {    //synchronaized - метод может вызывается в
        if (instance == null) {                              // разных потоках,только если он не занят
            Class factoryClass;
            try {
                factoryClass = Class.forName(properties.getProperty("dao.factory"));
                instance = (DaoFactory) factoryClass.newInstance();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException();
            } catch (IllegalAccessException e) {
                throw new RuntimeException();
            } catch (InstantiationException e) {
                throw new RuntimeException();
            }
        }
        return instance; // возвращение ссылки
    }

    public ConnectionFactory getConnectionFactory() { // создает экземпляр соеденения
        return new ConnectionFactoryImplementation(properties); // реализация фабрики
    }

    public abstract DAO getUserDao();



    //public abstract ProjectDAO getProjectDAO();
}
