package plop;

public class CustomEmailFormatException extends Exception{
    public CustomEmailFormatException(){
        super("Недопусстимый формат!");
    }
    public CustomEmailFormatException(String message){
        super(message);
    }
}
