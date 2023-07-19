package ba.unsa.etf.rpr.exceptions;

public class OrderException extends Exception{

    public OrderException(String msg, Exception reason){
        super(msg, reason);
    }

    public OrderException(String msg){
        super(msg);
    }
}
