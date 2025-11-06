package plop;

public class CustomNumberFormatException extends Exception{
    public CustomNumberFormatException(){
        super("Невозможно преобразовать в число");
    }
    public CustomNumberFormatException(String message){
        super(message);
    }
}
