import java.util.HashMap;
import java.util.Map;

class Car {
    private String brand;
    private String model;
    private int year;
    private String color;
    
    public Car(String brand, String model, int year) {
        this(brand, model, year, "Не указан");
    }
    
    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getColor() { return color; }
    
    public void setColor(String color) { this.color = color; }
    
    @Override
    public String toString() {
        return String.format(brand, model, year, color);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return year == car.year && 
               brand.equals(car.brand) && 
               model.equals(car.model) &&
               color.equals(car.color);
    }
    
    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = result % 16;
        return result;
    }
}

public class CarAccounthing {
    private Map<String, Car> carAccount;
    
    public void CarAccInitialization() {
        this.carAccount = new HashMap<>();
    }
    
    public CarAccounthing() {
        this.carAccount = new HashMap<>(16);
    }

    public CarAccounthing(int initialCapacity) {
        this.carAccount = new HashMap<>(initialCapacity);
    }
    
    public CarAccounthing(int initialCapacity, float loadFactor) {
        this.carAccount = new HashMap<>(initialCapacity, loadFactor);
    }
    
    public String addCar(String NumberSign, Car car) {
        if (NumberSign == null || NumberSign.trim().isEmpty()) {
            return "Номерной знак не может быть пустым";
        }
        if (car == null) {
            return "Автомобиль не введен";
        }
        
        carAccount.put(NumberSign.toUpperCase(), car);
        return ("Автомобиль " + NumberSign + " добавлен в автопарк");
    }
    
    public Car findCar(String NumbeSign) {
        if (NumbeSign == null) return null;
        
        Car car = carAccount.get(NumbeSign.toUpperCase());
        if (car != null) {
            System.out.println("Найден автомобиль: " + NumbeSign + " - " + car);
        } else {
            System.out.println("Автомобиль с номером " + NumbeSign + " не найден");
        }
        return car;
    }
    
    public Car removeCar(String NumberSign) {
        if (NumberSign == null) return null;
        
        Car removedCar = carAccount.remove(NumberSign.toUpperCase());
        if (removedCar != null) {
            System.out.println("Автомобиль " + NumberSign + " удален из автопарка");
        } else {
            System.out.println("Автомобиль с номером " + NumberSign + " не найден для удаления");
        }
        return removedCar;
    }
    
    public boolean containsCar(String NumberString) {
        boolean exists = carAccount.containsKey(NumberString.toUpperCase());
        if (exists){System.out.println("Автомобиль с номером" + NumberString  + " в автопарке");}
        else {System.out.println("Автомобиль с номером" + NumberString  + " не в автопарке");}

        return exists;
    }
    

    public void updateCar(String NumberSign, Car newCar) {
        if (NumberSign == null || newCar == null) return;
        
        if (carAccount.containsKey(NumberSign.toUpperCase())) {
            carAccount.put(NumberSign.toUpperCase(), newCar);
            System.out.println("Информация об автомобиле " + NumberSign + " обновлена");
        } else {
            System.out.println("Автомобиль с номером " + NumberSign + " не найден для обновления");
        }
    }
    
    public int getCarCount() {
        return carAccount.size();
    }
    
    public boolean isEmpty() {
        return carAccount.isEmpty();
    }
    

    public void displayAllCars() {
        if (carAccount.isEmpty()) {
            System.out.println("Автопарк пуст");
            return;
        }
        
        System.out.println("=== АВТОПАРК (всего автомобилей: " + carAccount.size() + ") ===");
        int counter = 1;
        for (Map.Entry<String, Car> entry : carAccount.entrySet()) {
            System.out.println(counter + ". " + entry.getKey() + " - " + entry.getValue());
            counter++;
        }
        System.out.println("============================================\n");
    }
    
    public void clearAcc() {
        carAccount.clear();
        System.out.println("Автопарк полностью очищен");
    }
    
    public static void main(String[] args) {
        CarAccounthing fleet = new CarAccounthing();
        
        fleet.addCar("A123BC", new Car("Toyota", "Camry", 2020, "Черный"));
        fleet.addCar("B456DE", new Car("Honda", "Civic", 2019, "Белый"));
        fleet.addCar("C789FG", new Car("BMW", "X5", 2022, "Синий"));
        fleet.addCar("D012HI", new Car("Mercedes", "E-Class", 2021, "Серый"));
        fleet.addCar("E345JK", new Car("Toyota", "RAV4", 2023, "Красный"));
        
        fleet.displayAllCars();
        
        fleet.findCar("A123BC");
        fleet.findCar("X999YY"); 
        
        fleet.containsCar("B456DE");
        fleet.containsCar("Z000ZZ");
        
        fleet.updateCar("A123BC", new Car("Aboba", "Camry Hybrid", 2023, "Черный"));
        fleet.findCar("A123BC");
        
        fleet.removeCar("C789FG");
        fleet.removeCar("NONEXISTENT");
        
        fleet.displayAllCars();
        
        System.out.println("   Всего автомобилей: " + fleet.getCarCount());
        System.out.println("   Автопарк пуст: " + fleet.isEmpty());
    }
}