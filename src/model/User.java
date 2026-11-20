package model;

public class User{
    private String phoneNumber;
    private String email;
    private double cash;

    public User(String phoneNumber, String email, double cash){
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.cash=cash;
    }

    public String GetPhoneNum(){
        return phoneNumber;
    }

    public String GetEmail(){
        return email;
    }

    public double GetCash(){ return cash; }

    public void SetCash(double cash){
        this.cash=cash;
    }

}