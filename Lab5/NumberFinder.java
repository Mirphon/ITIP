import java.util.regex.*;

public class NumberFinder {
    public static void main(String[] args) {
        try{
            String text = "The price of the product is $0.99, 1.12";
            Pattern pattern = Pattern.compile("\\d+\\.\\d+");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        }catch( NullPointerException e){
            System.out.println("Строка пустая!");
        } catch (PatternSyntaxException e){
            System.err.println("Неверный шаблон!");
        } catch (IllegalStateException e){
            System.err.println("Неправильный порядок команд!");
        }
    }
}
 