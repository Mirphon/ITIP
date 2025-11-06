package plop;

import java.io.FileWriter;

public class App {
    public static void main(String[] args) throws Exception {
        FileWriter writer = new FileWriter("C:\\Users\\mikha\\OneDrive\\Документы\\Java\\Lab4\\un3\\src\\plop\\logs.txt", true);
        try{
            double result = Checking.zeros(1.2,0);
            System.out.println(result);
        } catch (CustomDivisionException e) {
            writer.write(e + System.lineSeparator());   
        }

        try {
            System.out.println(Checking.age(118));
        } catch (CustomAgeException e) {
            writer.write(e + System.lineSeparator());
        }

        try {
            Checking.found("C:\\Users\\mikha\\OneDrive\\Документы\\Java\\Lab4\\un3\\src\\plop\\logs.txt", "C:\\Users\\mikha\\OneDrive\\Документы\\Java\\Lab4\\un3\\src\\plop\\copy.txt");
        } catch (CustomFileNotFoundException e) {
            writer.write(e + System.lineSeparator());}

        try {
            System.out.println(Checking.numberFormat("15"));

        } catch (CustomNumberFormatException e) {
            writer.write(e + System.lineSeparator());
        }

        try {
            Checking.emptySteck("ds");
        } catch (CustomEmptyStackException e) {
            writer.write(e + System.lineSeparator());
        }

        try {
            Checking.mat();
        } catch (CustomInputMismatchException e) {
            writer.write(e + System.lineSeparator());
        }

        try {
            Checking.mail("mikha@mail.ru");
        } catch (CustomEmailFormatException e) {
            writer.write(e + System.lineSeparator());}

        
        try {
            System.out.println(deistv.calculate(2,0, "AD2D"));
            
        } catch (CustomUnsupportedOperationException e) {
            writer.write(e + System.lineSeparator());}

        writer.close(); 


    }
}
