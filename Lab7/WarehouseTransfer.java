import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

class Warehouse {
    private List<Integer> goods;
    public int currentIndex = 0;

    public Warehouse(List<Integer> goods) {
        this.goods = goods;
    }

    public synchronized Integer getNextGood() {
        if (currentIndex < goods.size()) {
            return goods.get(currentIndex++);
        }
        return null;
    }

    public synchronized boolean hasGoods() {
        return currentIndex < goods.size();
    }
}

class Worker implements Runnable {
    private String name;
    private Warehouse warehouse;
    private CountDownLatch startLatch;
    private CountDownLatch doneLatch;
    private static final int MAX_WEIGHT = 150;

    public Worker(String name, Warehouse warehouse, CountDownLatch startLatch, CountDownLatch doneLatch) {
        this.name = name;
        this.warehouse = warehouse;
        this.startLatch = startLatch;
        this.doneLatch = doneLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
            while (true) {
                List<Integer> load = new ArrayList<>();
                int totalWeight = 0;

                while (warehouse.hasGoods()) {
                    Integer weight = warehouse.getNextGood();
                    if (weight == null) break;

                    if (totalWeight + weight <= MAX_WEIGHT) {
                        load.add(weight);
                        totalWeight += weight;
                    } else {
                        synchronized (warehouse) {
                            warehouse.currentIndex = warehouse.currentIndex - 1;
                        }
                        break;
                    }
                }

                if (load.isEmpty()) {
                    break; 
                }

                System.out.println(name + " переносит товары: " + load + " | Общий вес: " + totalWeight + " кг");

                Thread.sleep(1000);

                System.out.println(name + " разгрузил товары: " + load);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            doneLatch.countDown(); 
        }
    }
}

public class WarehouseTransfer {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> goods = List.of(50, 70, 20, 30, 90, 60, 40, 10, 80, 20);

        Warehouse warehouse = new Warehouse(new ArrayList<>(goods));

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(3); 

        Thread worker1 = new Thread(new Worker("Грузчик 1", warehouse, startLatch, doneLatch));
        Thread worker2 = new Thread(new Worker("Грузчик 2", warehouse, startLatch, doneLatch));
        Thread worker3 = new Thread(new Worker("Грузчик 3", warehouse, startLatch, doneLatch));

        worker1.start();
        worker2.start();
        worker3.start();

        System.out.println("Все грузчики готовы. Начинаем перенос товаров!");
        startLatch.countDown();

        doneLatch.await();
        System.out.println("Все товары перенесены на другой склад!");
    }
}
