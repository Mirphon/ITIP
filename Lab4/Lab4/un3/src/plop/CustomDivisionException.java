package plop;

public class CustomDivisionException extends Exception {
    public CustomDivisionException() {
        super("Ошибка: деление на ноль запрещено!");
    }
    public CustomDivisionException(String message) {
        super(message);
    }    
}
