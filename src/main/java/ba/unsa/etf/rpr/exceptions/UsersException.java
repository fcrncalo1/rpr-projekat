package ba.unsa.etf.rpr.exceptions;

public class UsersException extends Exception{
    public UsersException(String msg, Exception reason){
        super(msg, reason);
    }

    public UsersException(String msg){super(msg);}
}
