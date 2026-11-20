package model;

public class UserRent extends Rent {
    public UserRent(String owner, double price, String Category) {
        super(owner, price, Category);
    }

    public UserRent(String owner, double price) {
        super(owner, price, "Small");
    }

    @Override
    public double cost() {
        double Cost = Getprice() * getKoficient();

        // Обращаемся к геттеру GetBags() вместо приватной переменной isSleepingBag
        if (!GetBags().isEmpty()) {
            for (SleepingBag Bag : GetBags()) {
                Cost += Bag.GetPrice();
            }
        }
        return Cost;
    }
}