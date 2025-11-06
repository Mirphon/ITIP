package plop;

public class CustomEmptyStackException extends Exception{
    public CustomEmptyStackException(){
        super("Ошибка. Стек пустой.");
    }
    public CustomEmptyStackException(String message){
        super(message);
    }
}
