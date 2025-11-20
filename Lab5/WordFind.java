import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class WordFind {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите текст");
            String text = sc.nextLine();
            text = text.toLowerCase();
            System.out.println("Введите букву, с которой начинаются слова");
            String word = sc.nextLine();
            word = word.toLowerCase();

            String k = "\\b" + word + "[a-z]*\\b";
            Pattern pattern = Pattern.compile(k);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()){
                System.out.println(matcher.group().replace(word, word.toUpperCase()));
            }
        } catch (NullPointerException e){
            System.out.println("Строка не может быть пустой");
        } catch (IllegalStateException e){
            System.out.println("Неправльный порядок команд");
        } catch (PatternSyntaxException e){
            System.err.println("Ошибка в шаблоне");
        } catch (NoSuchElementException e){
            System.out.println("Введите в строку символы");
        } catch (Exception e){
            System.out.println("Непредвиденная ошибка");
        } 

    }
    
}
