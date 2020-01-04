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
import mainClasses.Cosmetics;
import mainClasses.FaceAndBodyCare;
import mainClasses.HistoryPurchase;
import mainClasses.User;

public class HistoryUser {

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
    private TextArea historyTextArea;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Controller controller = new Controller();
        String login = controller.getLogName();
        System.out.println(login);
        Socket socket = new Socket("localhost", 11111);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Request request = new Request("GET_USERS_BY_LOGIN", login);
        oos.writeObject(request);
        Request request1 = (Request) ois.readObject();
        ArrayList<User> list = request1.getUsers();

        Request request2 = new Request("GET_HISTORY_BY_ID", list.get(0).getId().intValue());
        oos.writeObject(request2);
        Request request3 = (Request) ois.readObject();
        ArrayList<HistoryPurchase> historyPurchases = request3.getHistoryPurchases();
        System.out.println(historyPurchases.toString());

        Request request4 = new Request("GET_COSMETICS");
        oos.writeObject(request4);
        Request request5 = (Request) ois.readObject();
        ArrayList<Cosmetics> cosmetics = request5.getCosmetics();
//        System.out.println(cosmetics.toString());

        Request request6 = new Request("GET_FACE_CARE");
        oos.writeObject(request6);
        Request request7 = (Request)ois.readObject();
        ArrayList<FaceAndBodyCare> faceAndBodyCares = request7.getFaceAndBodyCares();
//        System.out.println(faceAndBodyCares.toString());

        for (HistoryPurchase hp : historyPurchases){
            if (hp.getIsCosmetics() == 1){
                for (Cosmetics cs : cosmetics){
                    if (hp.getIdGood().equals(cs.getId())){
                        System.out.println("Cosmetics history");
                        historyTextArea.appendText(hp.getDate() + " " + list.get(0).getFirstName() + " " + list.get(0).getLastName() + " bought " + hp.getQuantity() + " " + cs.getCategory() + " by model " + cs.getModel() + " in sum of " + hp.getSum() + " tenges.\n");
                    }
                }
            }
            else if (hp.getIsCosmetics() == 0){
                for (FaceAndBodyCare fb : faceAndBodyCares){
                    if (hp.getIdGood().equals(fb.getId())){
                        System.out.println("face history");
                        historyTextArea.appendText(hp.getDate() + " " + list.get(0).getFirstName() + " " + list.get(0).getLastName() + " bought " + hp.getQuantity() + " " + fb.getCategory() + " by model " + fb.getModel() + " in sum of " + hp.getSum()  + " tenges.\n");
                    }
                }
            }
        }


        backBtn.setOnAction(actionEvent -> {
            backBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxmlFiles/userPanel.fxml"));

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
