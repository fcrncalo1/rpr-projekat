package ba.unsa.etf.rpr.exceptions;

public class OrdersException extends Exception{

    public OrdersException(String msg, Exception reason){
        super(msg, reason);
    }

    public OrdersException(String msg){
        super(msg);
    }
}
