
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class Matrix {
    public static void main(String[] args) throws Exception {  
        int number_String = 10;
        int number_column = 10;
        int numThreads = number_String;

        Random random = new Random();

        int[][] matrix = new int[number_String][number_column];
        for (int i = 0; i < number_String; i++){
            for (int j = 0; j < number_column; j++){
                matrix[i][j] = random.nextInt(-100000, 100000);
            }
        }
        for (int i = 0; i < matrix.length; i++){
            System.err.println(Arrays.toString(matrix[i]));
        }

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < number_String; i++){
            final int n = i;
            Callable <Long> task = ()->{
                long maximal = -1_000_000;
                for (int j = 0; j < number_column; j++){
                    if (matrix[n][j] > maximal){
                        maximal = matrix[n][j];
                    }
                }
                return maximal;
            };
            futures.add(executor.submit(task));
        }
        long TotalMaximal = -1_000_000;
        for (Future<Long> future : futures) {
            if (future.get() > TotalMaximal){
                TotalMaximal = future.get();
            }

        }

        executor.shutdown();

        System.out.println("Максимальный элемент матрицы:  " + TotalMaximal);

    }
}
