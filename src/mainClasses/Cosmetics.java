package mainClasses;

import java.io.Serializable;

public class Cosmetics extends Good implements Serializable {
    private String model;
    private String description;

    public Cosmetics(Long id, String category, double price, int sold, int quantity, int isActive, String model, String description) {
        super(id, category, price, sold, quantity, isActive);
        this.model = model;
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    @Override
    public String toString() {
        return "ID: " + getId() + "\n" +
                "Category: " + getCategory() + "\n" +
                "Price: " + getPrice() + "\n" +
                "Sold: " + getSold() + "\n" +
                "Quantity: " + getQuantity() + "\n" +
                "Model: " + model + "\n" +
                "Description: " + description;
    }
}
