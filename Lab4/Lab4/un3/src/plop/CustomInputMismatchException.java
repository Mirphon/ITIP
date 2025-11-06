package plop;

public class CustomInputMismatchException extends Exception{
    public CustomInputMismatchException(){
        super("Введено не число!");
    }
    public CustomInputMismatchException(String message){
        super(message);
    }
}
