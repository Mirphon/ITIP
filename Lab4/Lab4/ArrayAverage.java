public class ArrayAverage{
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        int sum = 0;
        int average = 0;
        try {
            if (arr.length == 0){
                System.out.println("Массив пустой!");
            }else{
                for (int i = 0; i < arr.length; i++){
                    sum = sum + arr[i];
                    average  = sum/(i+1);
                }
                System.out.print("Среднее арифметическое: " + average);
            }
                
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Недопустимый индекс массива");
        } catch (IllegalArgumentException e){
            System.out.print("Недопустимое значение элемента массива");
        } catch (Exception e){
            System.out.print("Непредвиденная ошибка");}
    }
}
