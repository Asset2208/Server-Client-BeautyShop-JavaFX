package controllerClasses;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mainClasses.Basket;
import mainClasses.Cosmetics;
import mainClasses.User;

import javax.swing.*;

public class ShowCosmeticsUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

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

    @FXML
    private TextField quantityTextArea;

    ObservableList<Cosmetics> cosm = FXCollections.observableArrayList();
//    public ObservableList<Cosmetics> getCosmetics() {
//        try {
////            Socket socket = new Socket("localhost", 11111);
//            ObjectOutputStream oos = new ObjectOutputStream(AdminPanel.getSocket().getOutputStream());
//            ObjectInputStream ois = new ObjectInputStream(AdminPanel.getSocket().getInputStream());
//            Request request = new Request("GET_COSMETICS");
//            oos.writeObject(request);
//            Request request1 = (Request)ois.readObject();
//            ArrayList<Cosmetics> list = request1.getCosmetics();
//            cosm.addAll(list);
//
//            oos.close();
//            ois.close();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return cosm;
//    }
    @FXML
    void addToBasket(MouseEvent event) throws IOException, ClassNotFoundException {
        Cosmetics cosmetics = cosmeticsTable.getSelectionModel().getSelectedItem();
        if (cosmetics == null){
            JOptionPane.showMessageDialog(null, "No selected product!");
        }
        else if (quantityTextArea.getText().equals("") || !quantityTextArea.getText().matches("[0-9]+")){
            JOptionPane.showMessageDialog(null, "Incorrect quantity!");
        }
        else if (Integer.parseInt(quantityTextArea.getText()) < cosmetics.getQuantity()){
            int quantity = Integer.parseInt(quantityTextArea.getText());
            Controller controller = new Controller();
            String login = controller.getLogName();
            Socket socket = new Socket("localhost", 11111);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Request request = new Request("GET_USERS_BY_LOGIN", login);
            oos.writeObject(request);
            Request request1 = (Request) ois.readObject();
            ArrayList<User> list = request1.getUsers();

            Request request4 = new Request("GET_BASKET_BY_ID", list.get(0).getId().intValue());
            oos.writeObject(request4);
            Request request5 = (Request) ois.readObject();
            ArrayList<Basket> baskets = request5.getBaskets();
            int c = 0;

            for (Basket basket: baskets){
                if (basket.getIdGood().equals(cosmetics.getId()) && basket.getIsCosmetics() == 1){
                    Request request2 = new Request("UPDATE_BASKET", basket.getIdGood().intValue(), basket.getQuantity() + quantity, basket.getIdUser().intValue(), 1);
                    oos.writeObject(request2);
                    c++;
                    JOptionPane.showMessageDialog(null, "Added to basket!");
                }
            }
            if (c==0){
                Basket basket = new Basket(null, list.get(0).getId(), cosmetics.getId(), 1, cosmetics.getCategory(), cosmetics.getModel(), quantity, cosmetics.getPrice());
                Request request2 = new Request("ADD_TO_BASKET", basket);
                oos.writeObject(request2);
                JOptionPane.showMessageDialog(null, "Added to basket!");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Input quantity is more than exists!");
        }
    }

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 11111);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Request request = new Request("GET_COSMETICS_AVAILABLE", 1);
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
            loader.setLocation(getClass().getResource("/fxmlFiles/showProductsUser.fxml"));

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
