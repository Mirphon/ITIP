package plop;

public class CustomAgeException extends Exception {
    public CustomAgeException(){
        super("Недопустимое значение возраста!");
    }
    public CustomAgeException(String message){
        super(message);
    }
}
