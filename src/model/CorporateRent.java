package model;

public class CorporateRent extends Rent {
    private int orders;

    public CorporateRent(String owner, double price, String Category, int orders) {
        super(owner, price, Category);
        if (orders < 1) {
            System.out.println("Количество позиций не может быть меньше 1");
            orders = 1;
        }
        this.orders = orders;
    }

    public CorporateRent(String owner, double price) {
        super(owner, price, "Small");
    }

    @Override
    public double cost() {
        double Cost = Getprice() * getKoficient();
        for (SleepingBag Bag : GetBags()) {
            Cost += Bag.GetPrice();
        }
        Cost = orders * Cost;
        return Cost;
    }
}