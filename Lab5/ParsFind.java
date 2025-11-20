import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ParsFind{
    public static void main(String[] args){
        String s = "AabbBaA";
        System.out.println(Find(s));
    }

    public static String Find(String s){
        Pattern patt = Pattern.compile("[a-z][A-Z]");
        Matcher matcher = patt.matcher(s);
        String result = matcher.replaceAll(match -> "!" + match.group() + "!");
        return result;
    }
}