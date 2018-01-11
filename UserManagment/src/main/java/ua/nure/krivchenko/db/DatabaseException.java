package ua.nure.krivchenko.ua.nure.krivchenko.db;

import java.sql.SQLException;

public class DatabaseException extends Exception {

    public DatabaseException(){
        super();
    }

    public DatabaseException(Exception e) {
        super(e);
    }

    public DatabaseException(String string) {
        super(string);
    }

    public DatabaseException(String message,Throwable cause){
        super(message,cause);
    }

    public DatabaseException(Throwable cause){
        super(cause);
    }
}
