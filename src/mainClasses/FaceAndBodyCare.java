package mainClasses;

import java.io.Serializable;

public class FaceAndBodyCare extends Good implements Serializable {
    private String model;
    private String description;
    private String gender;

    public FaceAndBodyCare(Long id, String category, double price, int sold, int quantity, int isActive, String model, String description, String gender) {
        super(id, category, price, sold, quantity, isActive);
        this.model = model;
        this.description = description;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
