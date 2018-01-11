package ua.nure.krivchenko.db;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Test;
import ua.nure.krivchenko.User;
import ua.nure.krivchenko.ua.nure.krivchenko.db.ConnectionFactory;
import ua.nure.krivchenko.ua.nure.krivchenko.db.ConnectionFactoryImplementation;
import ua.nure.krivchenko.ua.nure.krivchenko.db.DatabaseException;
import ua.nure.krivchenko.ua.nure.krivchenko.db.HsqldbUserDao;

import java.util.Collection;
import java.util.Date;

public class HsqldbUserDaoTest extends DatabaseTestCase {

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";

    private HsqldbUserDao dao;
    private ConnectionFactory connectionFactory;


    protected void Setup() throws Exception {
        super.setUp(); //указывает на класс родителя
        dao = new HsqldbUserDao(connectionFactory); // связь между dao
    }

    @Test
    public void testCreate(){
        User user = new User(); // создание экземпляра
        User resUser;
        Date dateOfBirth = new Date();
        user.setFirstName(FIRST_NAME); //
        user.setLastName(LAST_NAME);
        user.setDateOfBirth(new Date());
        assertNull(user.getId());
        try {
            resUser = dao.create(user);
            assertNotNull(resUser);
            assertNotNull(resUser.getId());
            assertEquals(FIRST_NAME,resUser.getFirstName());
            assertEquals(LAST_NAME,resUser.getLastName());
            assertEquals(dateOfBirth,resUser.getDateOfBirth());
        }catch (DatabaseException e){
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(user);
        assertNotNull(user.getId());
    }

    @Test
    public void testFindAll(){
        try {
            Collection<User> collection = dao.findAll(); //перечень объектов класса user
            assertNotNull(collection);
            assertEquals(2,collection.size()); // проверка на кол-во записей(предполог.2)
        } catch (DatabaseException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setDateOfBirth(new Date());
        assertNull(user.getId());
        try {
            user = dao.update(user);
            assertNotNull(user);

        } catch (DatabaseException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    @Test
    public void testDelete(){
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setDateOfBirth(new Date());
        assertNull(user.getId());

        try {
            user = dao.delete(user);
            assertNull(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNull(user);
        assertNull(user.getId());
    }

    protected IDatabaseConnection getConnection() throws Exception { // метод для создания соед. с БД
        connectionFactory = new ConnectionFactoryImplementation();  //
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    protected IDataSet getDataSet() throws Exception { // читает из файла xml
        IDataSet dataSet = new XmlDataSet(
                 getClass()
                .getClassLoader()
                .getResourceAsStream("usersDataSet.xml"));
        return dataSet;
    }
}
