package ua.nure.krivchenko.ua.nure.krivchenko.db;

public class DaoFactoryImpl extends DaoFactory  {

    public DAO getUserDao(){
        DAO result = null;
        try{
            Class clazz = Class.forName(properties.getProperty("dao.user")); // позволяет грузить классы на лету
            result = (DAO) clazz.newInstance();
            result .setConnectionFactory(getConnectionFactory());
        }catch(Exception e){
            throw new RuntimeException();
        }
        return result;
    }
}
