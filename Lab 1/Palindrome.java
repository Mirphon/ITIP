public class Palindrome {
    public static void main(String[ ] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (isPalindrome(s)){
            System.out.print(s + " is a palindrome");
            } else{
                System.out.print(s + " is not a palindrome");
            }
        }   
    } 
    
    public static String reverseString(String p){
        String o = "";
        for (int i = p.length()-1; i>=0; i--){
            o = o + p.charAt(i);
        }
        return o;
    }

    public static boolean isPalindrome(String s){
        String revs = reverseString(s);
        return s.equals(revs);
    }
}