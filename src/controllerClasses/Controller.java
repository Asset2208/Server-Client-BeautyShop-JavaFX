package controllerClasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import ServerAndSocket.Request;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class Controller {

    private static String logName;

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        Controller.logName = logName;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button enterBtn;

    @FXML
    void initialize() {
        enterBtn.setOnAction(actionEvent -> {
            String login = loginField.getText();
            String password = passwordField.getText();

            try {
                Socket socket = new Socket("localhost", 11111);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Request request = new Request("CHECK_LOGIN_PASSWORD", login, password);
                oos.writeObject(request);
                Request requestAndReply2 = (Request) ois.readObject();
                String answer = requestAndReply2.getText();
                System.out.println(answer);
                if (loginField.getText().equals("admin") && passwordField.getText().equals("admin1234")){
                    enterBtn.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxmlFiles/adminPanel.fxml"));

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
                else if (answer.equals("userExists")){
                    setLogName(login);
                    enterBtn.getScene().getWindow().hide();
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
                }
                else if (answer.equals("staffExistsCourier")){
                    setLogName(login);
                    enterBtn.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxmlFiles/courierPanel.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    JOptionPane.showMessageDialog(null, "Courier entering!");
                }
                else if (answer.equals("staffExistsCEO")){
                    setLogName(login);
                    enterBtn.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxmlFiles/adminPanel.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    JOptionPane.showMessageDialog(null, "CEO entering!");
                }
                else if (answer.equals("staffExistsMainAccountant")){
                    setLogName(login);
                    enterBtn.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxmlFiles/incomeAccountant.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    JOptionPane.showMessageDialog(null, "Main accountant entering!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Incorrect login or password! ");
                    loginField.setText("");
                    passwordField.setText("");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });



        signUpBtn.setOnAction(actionEvent -> {
            signUpBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxmlFiles/signUpPage.fxml"));

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

