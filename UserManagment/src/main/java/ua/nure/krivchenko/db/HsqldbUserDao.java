package ua.nure.krivchenko.ua.nure.krivchenko.db;

import ua.nure.krivchenko.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

class HsqldbUserDao implements DAO<User> {

    public static final String SELECT_QUERY = "SELECT id, firstname, lastname, dateOfBirth FROM users";
    private ConnectionFactory connectionFactory;
    private static final String INSERT_QUERY = "INSERT INTO users(firstname, lastname, dateOfBirth) VALUES(?, ?, ?)";// определяет тип запросса

    public HsqldbUserDao(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
    }

    public HsqldbUserDao(){
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public User create(User user) throws DatabaseException {
        try { // проверка вложенного кода на наличие исключительных ситуаций
            User resultUser = new User(user); // копия объекта User
            Connection connection = connectionFactory.createConnection(); // создаем возможность взаемодействия с бд
            PreparedStatement statement = connection.prepareStatement(
                    INSERT_QUERY);// определяет тип запросса
            statement.setString(1, user.getFirstName()); // замена вопросительного знака на значение user.getFirstName
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
            statement.executeUpdate(); // выполнить запрос,в результате обработки появляется ответ
            int n = statement.executeUpdate();
            if (n != 1) {
                throw new DatabaseException("Number of the inserted rows: " + n);
            }
            CallableStatement callableStatement = connection.prepareCall("call IDENTITY()"); // формирования идентификатора юзера в бд
            ResultSet id = callableStatement.executeQuery(); // запрашивает инфо с бд(последнее сгенерирование id)
            // ResultSet колекции
            if (id.next()) { // next возвращает текущий объект
                resultUser.setId(new Long(id.getLong(1))); // устанавлием пользователя идентификатор
            }

            id.close(); // закрытие
            callableStatement.close();
            statement.close();
            connection.close();
            return resultUser;
        }catch(DatabaseException e){
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    /*public Object create(Object o) throws DatabaseException {
        return null;
    }
*/
    public void update(User user) throws DatabaseException {

    }

    public void delete(User user) throws DatabaseException { // public boolean

    }

    public User find(Long id) throws DatabaseException {
        return null;
    }

    public Collection<User> findAll() throws DatabaseException {
        Collection<User> result = new LinkedList<User>();

        try{
            Connection connection = connectionFactory.createConnection(); // устанавливаем соедениение с бд
            Statement statement = connection.createStatement(); //Statment дает возможность выполнять статический запрос
            ResultSet resultSet = statement.executeQuery(
                    SELECT_QUERY); //получить записи из бд // executeQuery формирует перечень пользователей

            while(resultSet.next()) {  //проверяет есть ли след. запись
                User user = new User();
                user.setId(new Long(resultSet.getLong(1))); // достали id
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                result.add(user);
            }
            resultSet.close();
            statement.close();
            connection.close();

        }catch (SQLException e){
            throw new DatabaseException(e);
        }

        return result;
    }




}
