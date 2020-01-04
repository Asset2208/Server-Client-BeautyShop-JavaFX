package controllerClasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ServerAndSocket.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainClasses.Cosmetics;

import javax.swing.*;

public class AddQuantityCosmetics {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private TextField quantityField;

    @FXML
    private TableView<Cosmetics> cosmeticsTable;

    @FXML
    private TableColumn<Cosmetics, Long> idColumn;

    @FXML
    private TableColumn<Cosmetics, String> categoryColumn;

    @FXML
    private TableColumn<Cosmetics, Double> priceColumn;

    @FXML
    private TableColumn<Cosmetics, Integer> soldColumn;

    @FXML
    private TableColumn<Cosmetics, Integer> quantityColumn;

    @FXML
    private TableColumn<Cosmetics, String> modelColumn;

    @FXML
    private TableColumn<Cosmetics, String> descriptionColumn;
    ObservableList<Cosmetics> cosm = FXCollections.observableArrayList();


    @FXML
    void addQuantity(MouseEvent event) throws IOException, ClassNotFoundException {
        Cosmetics cosmetics = cosmeticsTable.getSelectionModel().getSelectedItem();
        if (cosmetics == null){
            JOptionPane.showMessageDialog(null, "No selected cosmetics!");
        }
        else if (quantityField.getText().equals("") || !quantityField.getText().matches("[0-9]+")){
            JOptionPane.showMessageDialog(null, "Incorrect quantity!");
        }
        else {
            int quantity = Integer.parseInt(quantityField.getText());
            Socket socket = new Socket("localhost", 11111);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Request request = new Request("GET_COSMETICS_BY_ID", cosmetics.getId().intValue());
            oos.writeObject(request);
            Request request1 = (Request) ois.readObject();
            ArrayList<Cosmetics> cosmetics1 = request1.getCosmetics();

            int new_quantity = cosmetics1.get(0).getQuantity() + quantity;
            Request request2 = new Request("ADD_QUANTITY_COSMETICS", cosmetics.getId().intValue(), new_quantity);
            oos.writeObject(request2);
            JOptionPane.showMessageDialog(null, "Successful operation!");
            backBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxmlFiles/addQuantity.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 11111);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Request request = new Request("GET_COSMETICS");
            oos.writeObject(request);
            Request request1 = (Request)ois.readObject();
            ArrayList<Cosmetics> list = request1.getCosmetics();
            for (Cosmetics cosmetics : list){
                cosm.add(cosmetics);
            }

            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        soldColumn.setCellValueFactory(new PropertyValueFactory<>("sold"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        cosmeticsTable.setItems(cosm);


        backBtn.setOnAction(actionEvent -> {
            backBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxmlFiles/addQuantity.fxml"));

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
