package plop;

public class deistv {

    public static final String ADDITION = "ADD";
    public static final String SUBTRACTION = "SUB";
    public static final String MULTIPLICATION = "MUL";
    public static final String DIVISION = "DIV";
    public static final String POWER = "POW";
    public static final String MODULO = "MOD";
    
    public static double calculate(double a, double b, String operation) throws CustomUnsupportedOperationException{
        switch (operation.toUpperCase()) {
            case ADDITION:
                return a + b;
                
            case SUBTRACTION:
                return a - b;
                
            case MULTIPLICATION:
                return a * b;
                
            case DIVISION:
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль!");
                }
                return a / b;
                
            case POWER:
                return Math.pow(a, b);
                
            case MODULO:
                if (b == 0) {
                    throw new ArithmeticException("Деление по модулю на ноль!");
                }
                return a % b;
                
            default:
                throw new CustomUnsupportedOperationException();
        }
    }
}