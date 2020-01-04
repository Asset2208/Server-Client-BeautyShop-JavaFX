package controllerClasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import ServerAndSocket.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainClasses.Cosmetics;
import mainClasses.FaceAndBodyCare;

import javax.swing.*;

public class AddFaceCare {
    private ObservableList<String> categoryChoices = FXCollections.observableArrayList("Moisturizer", "Serum", "Face wash", "Face bar", "Cream (face or hand)", "Make-up remover", "Exfoliator", "Self-tanner");
    String category = "";
    @FXML
    private void handleMaleBox(){
        if(maleCheckBox.isSelected()){
            femaleCheckBox.setSelected(false);
        }
    }

    @FXML
    private void handleFemaleBox(){
        if(femaleCheckBox.isSelected()){
            maleCheckBox.setSelected(false);
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField descriptionField;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private Button backBtn;

    @FXML
    private Button addFaceCareBtn;

    @FXML
    private CheckBox maleCheckBox;

    @FXML
    private CheckBox femaleCheckBox;

    @FXML
    void initialize() {
        categoryChoiceBox.setItems(categoryChoices);
        addFaceCareBtn.setOnAction(actionEvent -> {
            int c = 0;
            String gender = "Female";
            if(categoryChoiceBox.getValue() != null) {
                category = categoryChoiceBox.getValue();
            }
            else {
                category = "";
                c++;
                JOptionPane.showMessageDialog(null, "You didn't choose Category!");
            }
            if (modelField.getText().equals("")){
                c++;
                JOptionPane.showMessageDialog(null, "You didn't entered model!");
            }
            else if (priceField.getText().equals("")){
                c++;
                JOptionPane.showMessageDialog(null, "You didn't entered price!");
            }
            else if (quantityField.getText().equals("")){
                c++;
                JOptionPane.showMessageDialog(null, "You didn't entered quantity!");
            }
            else if (descriptionField.getText().equals("")){
                c++;
                JOptionPane.showMessageDialog(null, "You didn't entered description!");
            }
            if (c == 0){
                if(maleCheckBox.isSelected()){
                    gender = "Male";
                }

                try {
                    Socket socket = new Socket("localhost", 11111);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Request request = new Request("ADD_FACE_CARE", new FaceAndBodyCare(null, category, Double.parseDouble(priceField.getText()), 0, Integer.parseInt(quantityField.getText()), 1, modelField.getText(), descriptionField.getText(), gender));
                    oos.writeObject(request);
                    Request request1 = (Request)ois.readObject();
                    System.out.println(request1);
                    JOptionPane.showMessageDialog(null, "SUCCESSFUL!");

                    addFaceCareBtn.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxmlFiles/addProduct.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        backBtn.setOnAction(actionEvent -> {
            backBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxmlFiles/addProduct.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
