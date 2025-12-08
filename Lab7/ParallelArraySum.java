import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ParallelArraySum {

    public static void main(String[] args) throws Exception {
        int arraySize = 1_000_000;
        int numThreads = 4;

        int[] array = new int[arraySize];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
        }

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Long>> futures = new ArrayList<>();

        int chunkSize = arraySize / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end;
            if (i == numThreads - 1){
                end = arraySize;
            } else { end = start + chunkSize;}

            Callable<Long> task = () -> {
                long sum = 0;
                for (int j = start; j < end; j++) {
                    sum += array[j];
                }
                return sum;
            };

            futures.add(executor.submit(task));
        }

        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }

        executor.shutdown();

        System.out.println("Сумма элементов массива: " + totalSum);
    }
}
