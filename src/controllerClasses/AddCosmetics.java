package controllerClasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import ServerAndSocket.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainClasses.Cosmetics;

import javax.swing.*;

public class AddCosmetics {

    private ObservableList<String> categoryChoices = FXCollections.observableArrayList("Lipstick", "Powder", "Ink", "Eye shadow", "Eyeliner", "Eyebrow pencil", "Brow gel", "False eyelashes", "Lip gloss", "Overlacquer", "Primer");
    String category = "";

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
    private Button addCosmeticsBtn;

    @FXML
    void initialize() {
        categoryChoiceBox.setItems(categoryChoices);

        addCosmeticsBtn.setOnAction(actionEvent -> {
            int c = 0;
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

            if (c==0){
                try {
                    Socket socket = new Socket("localhost", 11111);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Request request = new Request("ADD_COSMETICS", new Cosmetics(null, category, Double.parseDouble(priceField.getText()), 0, Integer.parseInt(quantityField.getText()), 1, modelField.getText(), descriptionField.getText()));
                    oos.writeObject(request);
                    Request request1 = (Request)ois.readObject();
                    System.out.println(request1);
                    JOptionPane.showMessageDialog(null, "SUCCESSFUL!");

                    addCosmeticsBtn.getScene().getWindow().hide();
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
