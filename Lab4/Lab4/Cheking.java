
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Cheking{
    public static void main(String[] args){
        String pastFile = "C:\\Users\\mikha\\OneDrive\\Документы\\Java\\Lab4\\pastFile.txt";
        String newFile = "C:\\Users\\mikha\\OneDrive\\Документы\\Java\\Lab4\\newFile.txt";
        openFile(pastFile, newFile);
    }
    
    public static void openFile(String pastFile, String newFile){
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try{
            inputStream = new FileInputStream(pastFile);
        } catch (FileNotFoundException e){
            System.out.println("Исходный файл не существует!");
        } catch (NullPointerException e){
            System.out.println("Путь к файлу не указан!");
        } catch (Exception e){
            System.out.println("Непредвиденная ошибка: " + e);
        }
        
        try{
            outputStream = new FileOutputStream(newFile);
        } catch (NullPointerException e){
            System.out.println("Путь к файлу не указан!");
        } catch (FileNotFoundException e){
            System.out.println("Изменяемый файл не существует!");
        } catch (Exception e){
            System.out.println("Непредвиденная ошибка: " + e);
        }

        try{
            byte[] buffer = new byte[1024];
            int bytesRead;
                
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("Файл скопирован!");
        } catch (java.io.IOException e){
            System.out.println("Ошибка считывания и записи данных файла!");
        } catch (NullPointerException e){
            System.out.println("Считать файл невозможно!");
        }
        catch (Exception e){
            System.out.println("Непридвиденная ошибка: " + e);
        }

        try{
            inputStream.close();
 
        }catch (NullPointerException e){
            System.out.println("Закрыть файл невозможно!");

        } catch (Exception e){
            System.out.println("Непредвиденная ошибка: " + e);
        }
        
        try{
            outputStream.close();
        
        }catch (NullPointerException e){
            System.out.println("Закрыть файл невозможно!");

        } catch (Exception e){
            System.out.println("Непредвиденная ошибка: " + e);
        }
    }
}
