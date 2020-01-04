package mainClasses;

import java.io.Serializable;

public class Staff extends Person implements Serializable {
    private String phoneNum;
    private String position;
    private double salary;

    public Staff(){}

    @Override
    public String toString() {
        return "Staff{" +
                "phoneNum='" + phoneNum + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Staff(Long id, String login, String password, String firstName, String lastName, String phoneNum, String position, double salary) {
        super(id, login, password, firstName, lastName);
        this.phoneNum = phoneNum;
        this.position = position;
        this.salary = salary;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
