import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PasswordCheck{
    public static void main(String[] args){
        try{ 
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            System.out.println(PassCheck(s));
        } catch (NoSuchElementException e){
            System.out.println("Введите в строку пароль!");
        } catch (IllegalStateException e){
            System.out.println("Сканер закрыт!");
        } catch (Exception e){
            System.out.println("Непридвиденная ошибка " + e);
        }

    }

    public static String PassCheck(String s){
        try {
            Pattern patt = Pattern.compile("^[A-Za-z0-9]{8,16}$");
            Matcher matcher = patt.matcher(s);
            if (s.length() == 0){
                return "Пароль не может быть пустым.";
            }
            if (matcher.matches() != true){
                String p = "Пароль должен быть от 8 до 16 символов и содержать только латинские буквы и цифры!";
                return p;
            }

            Pattern up = Pattern.compile("[A-Z]");
            Matcher matcherup = up.matcher(s);
            if (matcherup.find() != true){
                return "Пароль должен содержать хотя бы одну заглавную букву!";
            }

            Pattern number = Pattern.compile("[0-9]");
            Matcher matchernumb = number.matcher(s);
            if (matchernumb.find() != true){
                return "Пароль должен содержать хотя бы одну цифру!";
            }

            return "Пароль установлен!";
        } catch (IllegalStateException e){
            return "Неправильный порядок команд!";
        }catch (PatternSyntaxException e){
            return "Неверный шаблон!";
        }
    }
}