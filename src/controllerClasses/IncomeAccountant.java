package controllerClasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ServerAndSocket.Request;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mainClasses.HistoryPurchase;

public class IncomeAccountant {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private Text nameUserText;

    @FXML
    private Text basketCountText;

    @FXML
    private TextArea cosmeticsTextArea;

    @FXML
    private TextArea faceCareTextArea;

    @FXML
    private Text nameText;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Controller controller = new Controller();
        nameText.setText(controller.getLogName());
        Socket socket = new Socket("localhost", 11111);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Request request2 = new Request("GET_HISTORY");
        oos.writeObject(request2);
        Request request3 = (Request) ois.readObject();
        ArrayList<HistoryPurchase> historyPurchases = request3.getHistoryPurchases();

        double incomeCosmetics = 0;
        double incomeFaceCare = 0;
        for (HistoryPurchase hp : historyPurchases){
            if (hp.getIsCosmetics() == 1){
                incomeCosmetics += hp.getSum();
            }
            else {
                incomeFaceCare += hp.getSum();
            }
        }

        cosmeticsTextArea.setText(Double.toString(incomeCosmetics));
        faceCareTextArea.setText(Double.toString(incomeFaceCare));

        backBtn.setOnAction(actionEvent -> {
            backBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxmlFiles/MainPage.fxml"));

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
