package mainClasses;

import java.io.Serializable;

public class HistoryPurchase implements Serializable {
    private Long id;
    private Long idUser;
    private Long idGood;
    private int isCosmetics;
    private String category;
    private String model;
    private int quantity;
    private double price;
    private double sum;
    private String date;

    public HistoryPurchase(Long id, Long idUser, Long idGood, int isCosmetics, String category, String model, int quantity, double price, double sum, String date) {
        this.id = id;
        this.idUser = idUser;
        this.idGood = idGood;
        this.isCosmetics = isCosmetics;
        this.category = category;
        this.model = model;
        this.quantity = quantity;
        this.price = price;
        this.sum = sum;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdGood() {
        return idGood;
    }

    public void setIdGood(Long idGood) {
        this.idGood = idGood;
    }

    public int getIsCosmetics() {
        return isCosmetics;
    }

    public void setIsCosmetics(int isCosmetics) {
        this.isCosmetics = isCosmetics;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HistoryPurchase{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idGood=" + idGood +
                ", isCosmetics=" + isCosmetics +
                ", category='" + category + '\'' +
                ", model='" + model + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", sum=" + sum +
                ", date='" + date + '\'' +
                '}';
    }
}
