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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mainClasses.FaceAndBodyCare;

public class ShowFaceCare {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<FaceAndBodyCare> faceAndBodyCareTableView;

    @FXML
    private TableColumn<FaceAndBodyCare, Long> idColumn;

    @FXML
    private TableColumn<FaceAndBodyCare, String> categoryColumn;

    @FXML
    private TableColumn<FaceAndBodyCare, Double> priceColumn;

    @FXML
    private TableColumn<FaceAndBodyCare, Integer> soldColumn;

    @FXML
    private TableColumn<FaceAndBodyCare, Integer> quantityColumn;

    @FXML
    private TableColumn<FaceAndBodyCare, String> modelColumn;

    @FXML
    private TableColumn<FaceAndBodyCare, String> genderColumn;

    @FXML
    private TableColumn<FaceAndBodyCare, String> descriptionColumn;
    ObservableList<FaceAndBodyCare> cosm = FXCollections.observableArrayList();
//    public ObservableList<FaceAndBodyCare> getFaceAndBodyCare() {
//        try {
////            Socket socket = new Socket("localhost", 11111);
//            ObjectOutputStream oos = new ObjectOutputStream(AdminPanel.getSocket().getOutputStream());
//            ObjectInputStream ois = new ObjectInputStream(AdminPanel.getSocket().getInputStream());
//            Request request = new Request("GET_FaceAndBodyCare");
//            oos.writeObject(request);
//            Request request1 = (Request)ois.readObject();
//            ArrayList<FaceAndBodyCare> list = request1.getFaceAndBodyCare();
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
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 11111);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Request request = new Request("GET_FACE_CARE");
            oos.writeObject(request);
            Request request1 = (Request)ois.readObject();
            ArrayList<FaceAndBodyCare> list = request1.getFaceAndBodyCares();
            cosm.addAll(list);

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
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        faceAndBodyCareTableView.setItems(cosm);


        backBtn.setOnAction(actionEvent -> {
            backBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxmlFiles/showProducts.fxml"));

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
