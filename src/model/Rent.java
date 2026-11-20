package model;
import exception.SleepingBagisTakenException;
import java.util.ArrayList;
import java.util.List;

public abstract class Rent {
    private final String owner;
    private final double price;
    private final String Category;
    private final List<SleepingBag> isSleepingBag = new ArrayList<>();

    public Rent(String name, double price, String category) {
        this.owner = name;
        if (price <= 0.0) {
            throw new IllegalArgumentException("Цена не может быть равна 0 или быть меньше 0");
        }
        this.price = price;
        this.Category = category;
    }

    public Rent(String name, double price) {
        this(name, price, "Small");
    }

    public double getKoficient() {
        return switch (Getcategory()) {
            case "Medium" -> 1.6;
            case "Large" -> 2.30;
            default -> 1.0;
        };
    }

    public void addBag(SleepingBag b) {
        if (b == null) throw new IllegalArgumentException("Нельзя добавить пустую опцию (null)!");
        if (b.GetisTaken()) throw new SleepingBagisTakenException("Нельзя добавить спальный мешок, который уже занят");
        this.isSleepingBag.add(b);
        b.SetisTaken(true);
    }

    public void addBag(SleepingBag b, boolean warm) {
        if (b == null) throw new IllegalArgumentException("Нельзя добавить пустую опцию (null)!");
        if (b.GetisTaken()) throw new SleepingBagisTakenException("Нельзя добавить спальный мешок, который уже занят");
        this.isSleepingBag.add(b);
        b.setPrice(b.GetPrice() * 1.5);
        b.SetisTaken(true);
    }

    public abstract double cost();

    public String Getname() { return owner; }
    public double Getprice() { return price; }
    public String Getcategory() { return Category; }
    public List<SleepingBag> GetBags() { return isSleepingBag; } // Возвращаем список!
}