package mainClasses;

import java.io.Serializable;

public class User extends Person implements Serializable {
    private String phoneNum;
    private double balance;
    private String adress;

    public User(Long id, String login, String password, String firstName, String lastName, String phoneNum, double balance, String adress) {
        super(id, login, password, firstName, lastName);
        this.phoneNum = phoneNum;
        this.balance = balance;
        this.adress = adress;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\n" +
                "First name: " + getFirstName() + "\n" +
                "Last name: " + getLastName() + "\n" +
                "Tellephone number: " + getPhoneNum() + "\n" +
                "Balance: " + balance  + "\n" +
                "Adress " + getAdress();
    }
}
