package ba.unsa.etf.rpr.exceptions;

public class ProductsException extends Exception{
    public ProductsException(String msg, Exception reason){
        super(msg, reason);
    }

    public ProductsException(String msg){
        super(msg);
    }
}
