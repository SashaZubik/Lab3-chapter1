package model;

public class SleepingBag{
    private double price;
    private String name;
    private boolean isTaken=false;

    public SleepingBag(double price, String name){
        this.price=price;
        this.name=name;
    }

    public double GetPrice(){
        return price;
    }

    public String GetName(){
        return name;
    }

    public boolean GetisTaken() {return isTaken;}

    public void SetisTaken(boolean taken){
        this.isTaken=taken;
    }

    public void setPrice(double price){
        if (price<0){
            throw new IllegalArgumentException("Цена не может быть меньше 0 рублей");
        }
        this.price=price;
    }
}