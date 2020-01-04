package mainClasses;

import java.io.Serializable;

public class Basket implements Serializable {
    private Long id;
    private Long idUser;
    private Long idGood;
    private int isCosmetics;
    private String category;
    private String model;
    private int quantity;
    private double price;

    public Basket(){}
    public Basket(Long id, Long idUser, Long idGood, int isCosmetics, String category, String model, int quantity, double price) {
        this.id = id;
        this.idUser = idUser;
        this.idGood = idGood;
        this.isCosmetics = isCosmetics;
        this.category = category;
        this.model = model;
        this.quantity = quantity;
        this.price = price;
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

//    @Override
//    public String getSection(){
//        return "isCosmetics=" + isCosmetics;
//    }

    @Override
    public String toString() {
        return isCosmetics + " " + idGood + " " + idUser;
//                "Basket{" +
//                "id=" + id +
//                ", idUser=" + idUser +
//                ", idGood=" + idGood +

//                ", category='" + category + '\'' +
//                ", model='" + model + '\'' +
//                ", quantity=" + quantity +
//                ", price=" + price +
//                '}';
    }
}
