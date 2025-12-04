import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Sales {
    private final ConcurrentHashMap<String, int[]> sales = new ConcurrentHashMap<>();

    public void addSale(String productName, int price, int quantity) {
        sales.merge(productName,
                new int[]{quantity, price},
                (oldValue, newValue) -> {
                    oldValue[0] += quantity;   
                    oldValue[1] = price;       
                    return oldValue;
                });
    }

    public void printSales() {
        System.out.println("Список проданных товаров:");
        for (Map.Entry<String, int[]> entry : sales.entrySet()) {
            System.out.println(entry.getKey() + " - количество: "
                    + entry.getValue()[0] + ", цена: "
                    + entry.getValue()[1]);
        }
    }

    public int getTotalRevenue() {
        int sum = 0;
        for (int[] value : sales.values()) {
            sum += value[0] * value[1];
        }
        return sum;
    }

    public int getLocalRevenue(String brand){
        int sum = 0;
        for (Map.Entry<String, int[]> entry : sales.entrySet()){
            if (entry.getKey() == brand){
                sum = entry.getValue()[0] * entry.getValue()[1];
            }

        }
        return sum;
    }

    public String getMostPopularProduct() {
        String popular = null;
        int maxCount = 0;

        for (Map.Entry<String, int[]> entry : sales.entrySet()) {
            if (entry.getValue()[0] > maxCount) {
                maxCount = entry.getValue()[0];
                popular = entry.getKey();
            }
        }
        return popular;
    }

    public static void main(String[] args) {
        Sales manager = new Sales();

        manager.addSale("Хлеб", 50, 10);
        manager.addSale("Сыр", 120, 5);
        manager.addSale("Хлеб", 50, 7);
        manager.addSale("Молоко", 80, 12);

        manager.printSales();
        System.out.println("Общая сумма продаж: " + manager.getTotalRevenue());
        System.out.println("Сумма продаж хлеба: " + manager.getLocalRevenue("Хлеб"));
        System.out.println("Самый популярный товар: " + manager.getMostPopularProduct());
    }
}
