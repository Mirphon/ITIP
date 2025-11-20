import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class IpCheck {
    public static void main(String[] args){
        try {
            String s = "255.255.55.9";
            String ipPattern = "^((25[0-5]|2[0-4][0-9]|[1][0-9][0-9]|[1-9][0-9]|[0-9])\\.){3}" +
                    "(25[0-5]|2[0-4][0-9]|[1][0-9][0-9]|[1-9][0-9]|[0-9])$";
        
            Pattern pattern = Pattern.compile(ipPattern);
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()){
                System.out.print("IP-адрес корректен.");}
            else{
                System.out.println("IP-адрес некоректен.");
            }
        } catch (NullPointerException e){
            System.out.println("Строка на входе пуста!");
        } catch (PatternSyntaxException e) {
            System.out.println("Ошибка в шаблоне.");
        } catch (IllegalStateException e){
            System.out.println("Неправильный порядок команд!");
        } catch (Exception e){
            System.out.println("Непредвиденная ошибка.");
        }
    }
}
