import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface DataProcessor {
}


class DataManager {

    private List<String> data = new ArrayList<>();
    private List<String> processedData = new ArrayList<>();
    private final List<Object> processors = new ArrayList<>();

    private final ExecutorService executor = Executors.newFixedThreadPool(6);

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public void loadData(String source) throws IOException {
        data = Files.readAllLines(Path.of(source));
    }

    public void processData() throws Exception {

        List<Callable<List<String>>> tasks = new ArrayList<>();

        for (Object processor : processors) {
            for (Method method : processor.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(DataProcessor.class)) {

                    method.setAccessible(true);

                tasks.add(() -> {
                    @SuppressWarnings("unchecked")
                    List<String> result =
                            (List<String>) method.invoke(processor, data);

                    List<String> withMarker = new ArrayList<>();

                    withMarker.add(
                        "=== " + processor.getClass().getSimpleName()
                        + "." + method.getName() + " ==="
                    );

                    withMarker.addAll(result);
                    withMarker.add(""); 

                    return withMarker;
                });
                }
            }
        }

        List<Future<List<String>>> results = executor.invokeAll(tasks);

        processedData = results.stream()
                .flatMap(f -> {
                    try {
                        return f.get().stream();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }


    public void saveData(String destination) throws IOException {
        Files.write(Path.of(destination), processedData);
        executor.shutdown();
    }
}

class FilterProcessor {

    @DataProcessor
    public List<String> filter(List<String> data) {
        return data.stream()
                .filter(s -> s.length() > 3)
                .collect(Collectors.toList());
    }
}

class TransformProcessor {

    @DataProcessor
    public List<String> transform(List<String> data) {
        return data.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}

class AggregateProcessor {

    @DataProcessor
    public List<String> aggregate(List<String> data) {
        return List.of("TOTAL ELEMENTS: " + data.size());
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        DataManager manager = new DataManager();

        manager.registerDataProcessor(new FilterProcessor());
        manager.registerDataProcessor(new TransformProcessor());
        manager.registerDataProcessor(new AggregateProcessor());

        manager.loadData("input.txt");
        manager.processData();
        manager.saveData("output.txt");

        System.out.println("Обработка завершена. Результат сохранён в output.txt");
    }
}
