package plop;

public class CustomUnsupportedOperationException extends  Exception{
    public CustomUnsupportedOperationException(){
        super("Операция не поддерживается!");
    }
    public CustomUnsupportedOperationException(String message){
        super(message);
    }
}
