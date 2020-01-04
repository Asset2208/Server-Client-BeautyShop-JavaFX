package mainClasses;

import java.io.Serializable;

public abstract class Good implements Serializable {
    private Long id;
    private String category;
    private double price;
    private int sold;
    private int quantity;
    private int isActive;

    public Good(Long id, String category, double price, int sold, int quantity, int isActive) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.sold = sold;
        this.quantity = quantity;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Override
    public abstract String toString();
}
