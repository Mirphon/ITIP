package plop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;


public class Checking {
    public static double zeros(double a, double b) throws CustomDivisionException{
        if (b == 0) {
            throw new CustomDivisionException();
        }
        return a/b;
    }

    public static String age(int man_age) throws CustomAgeException{
        if ((man_age < 0) || (man_age > 120)){
            throw new CustomAgeException();
        } else{
            return ("Ваш возраст: " + man_age);
        }
    }

    public static void found(String fileName, String fileNameTwo) throws CustomFileNotFoundException{
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            FileOutputStream outputStream = new FileOutputStream(fileNameTwo);
            byte[] buffer = new byte[1024];
            int bytesRead;
                
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            throw new CustomFileNotFoundException();
        } catch (Exception e) {
            System.out.println("Ошибка(");}
    }

    public static int numberFormat(String a) throws CustomNumberFormatException{
        try {
            int s;
            s = Integer.parseInt(a);    
            return s;       
        } catch (NumberFormatException e){
            throw new CustomNumberFormatException();
        }
    }

    public static void emptySteck(String name) throws CustomEmptyStackException{
        Stack <Card> st = new Stack<>();
        try {
            st.pop();
        } catch (EmptyStackException e) {
            throw new CustomEmptyStackException();
        }
    }

    public static void mat() throws CustomInputMismatchException{
        Scanner sc = new Scanner(System.in);
        try {
            int a = sc.nextInt();
            
        } catch (InputMismatchException e) {
            throw new CustomInputMismatchException();
        }
    }

    public static void mail(String mails) throws CustomEmailFormatException{
        String[] a = mails.split("@mail.ru");
        if (a.length != 1){
            throw new CustomEmailFormatException();
        }
    }
}
