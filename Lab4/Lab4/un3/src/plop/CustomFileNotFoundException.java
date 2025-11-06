package plop;

public class CustomFileNotFoundException extends Exception{
    public CustomFileNotFoundException(){
        super("Файл не найден!");
    }
    public CustomFileNotFoundException(String message){
        super(message);
    }
}
