package ServerAndSocket;

import mainClasses.DataBase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class ClientHandler extends Thread{
    private Socket socket = null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private Connection connection = null;

    public ClientHandler(Socket socket, Connection connection) {
        this.socket = socket;
        this.connection = connection;

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        DataBase db = new DataBase();
        try {
            while (true){
                Request request = (Request) ois.readObject();
                System.out.println(request);

                if (request.getCode().equals("ADD_USER")){
                    db.addUser(request.getUser());
                    oos.writeObject(new Request("SUCCESS_ADDING"));
                }
                else if (request.getCode().equals("CHECK_LOGIN")){
                    if (db.checkUsername(request.getText())){
                        Request request1 = new Request("CHECK_LOGIN_EXIST");
                        request1.setaBoolean(true);
                        oos.writeObject(request1);
                    }
                    else {
                        Request request1 = new Request("CHECK_LOGIN_DONT_EXIST");
                        request1.setaBoolean(false);
                        oos.writeObject(request1);
                    }
                }
                else if (request.getCode().equals("CHECK_LOGIN_STAFF")){
                    if (db.checkStaffName(request.getText())){
                        Request request1 = new Request("CHECK_STAFF_EXIST");
                        request1.setaBoolean(true);
                        oos.writeObject(request1);
                    }
                    else {
                        Request request1 = new Request("CHECK_STAFF_DONT_EXIST");
                        request1.setaBoolean(false);
                        oos.writeObject(request1);
                    }
                }
                else if (request.getCode().equals("ADD_COSMETICS")){
                    db.addCosmetics(request.getCosmetic());
                    oos.writeObject(new Request("SUCCESS_ADDING"));
                }
                else if (request.getCode().equals("ADD_FACE_CARE")){
                    db.addFaceAndBodyCare(request.getFaceAndBodyCare());
                    oos.writeObject(new Request("SUCCESS_ADDING"));
                }
                else if (request.getCode().equals("GET_COSMETICS")){
                    Request request1 = new Request("GET_COSMETICS_REPLY");
                    request1.setCosmetics(db.getAllCosmertics());
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("GET_FACE_CARE")){
                    Request request1 = new Request("GET_FACE_CARE");
                    request1.setFaceAndBodyCares(db.getAllFaceCare());
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("ADD_STAFF")){
                    db.addStaff(request.getStaff());
                    oos.writeObject(new Request("SUCCESS_ADDING"));
                }
                else if (request.getCode().equals("GET_STAFF")){
                    Request request1 = new Request("GET_STAFF");
                    request1.setStaffs(db.getAllStaffs());
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("DELETE_STAFF")){
                    db.deleteStaff(request.getText());
                    oos.writeObject(new Request("SUCCESS_DELETING"));
                }
                else if (request.getCode().equals("CHECK_LOGIN_PASSWORD")){
                    Request request1 = new Request("CHECK_LOGIN_PASSWORD");
                    request1.setText(db.checkLoginPassword(request.getText(), request.getText2()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("GET_USERS_BY_LOGIN")){
                    Request request1 = new Request("GET_USERS_BY_LOGIN");
                    request1.setUsers(db.getUsersByLogin(request.getText()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("ADD_TO_BASKET")){
                    db.addBasket(request.getBasket());
                    oos.writeObject(new Request("SUCCESS_ADDING"));
                }
                else if (request.getCode().equals("GET_BASKET_BY_ID")){
                    Request request1 = new Request("GET_BASKET_BY_ID");
                    request1.setBaskets(db.getBasketByIDUser(request.getId()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("UPDATE_BASKET")){
                    db.updateBasket(request.getId(), request.getId2(), request.getId3(), request.getId4());
                    oos.writeObject(new Request("SUCCESS_UPDATING"));
                }
                else if (request.getCode().equals("BUY_COSMETICS")){
                    db.buyFromBasket(request.getId(), request.getId2(), request.getId3());
//                    oos.writeObject(new Request("SUCCESS_UPDATING"));
                }
                else if (request.getCode().equals("BUY_FACE_CARE")){
                    db.buyFromBasketFaceCare(request.getId(), request.getId2(), request.getId3());
//                    oos.writeObject(new Request("SUCCESS_UPDATING"));
                }
                else if (request.getCode().equals("DELETE_FROM_BASKET")){
                    db.deleteFromBasket(request.getId());
                }
                else if (request.getCode().equals("ADD_TO_HISTORY")){
                    db.addHistory(request.getHistoryPurchase());
                    oos.writeObject(new Request("SUCCESS_ADDING"));
                }
                else if (request.getCode().equals("GET_HISTORY_BY_ID")){
                    Request request1 = new Request("GET_HISTORY_BY_ID");
                    request1.setHistoryPurchases(db.getHistoryByID(request.getId()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("GET_HISTORY")){
                    Request request1 = new Request("GET_HISTORY");
                    request1.setHistoryPurchases(db.getAllHistory());
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("GET_USERS")){
                    Request request1 = new Request("GET_USERS");
                    request1.setUsers(db.getAllUsers());
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("DELETE_FROM_CHOSEN_BASKET")){
                    db.deleteFromChosenBasket(request.getId(), request.getId2(),request.getId3());
                }
                else if (request.getCode().equals("GET_FACE_CARE_AVAILABLE")){
                    Request request1 = new Request("GET_FACE_CARE_AVAILABLE");
                    request1.setFaceAndBodyCares(db.getActiveFaceCare(request.getId()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("GET_COSMETICS_AVAILABLE")){
                    Request request1 = new Request("GET_COSMETICS_AVAILABLE");
                    request1.setCosmetics(db.getActiveCosmetics(request.getId()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("SET_ORDER")){
                    db.addOrder(request.getOrder());
                    oos.writeObject(new Request("SUCCESS_ADDING"));
                }
                else if (request.getCode().equals("GET_ORDERS_BY_ID")){
                    Request request1 = new Request("GET_ORDERS_BY_ID");
                    request1.setOrders(db.getOrdersByID(request.getId()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("GET_ORDERS")){
                    Request request1 = new Request("GET_ORDERS");
                    request1.setOrders(db.getAllOrders());
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("GET_STAFF_BY_LOGIN")){
                    Request request1 = new Request("GET_STAFF_BY_LOGIN");
                    request1.setStaffs(db.getStaffByLogin(request.getText()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("UPDATE_ORDER_COURIER_ID")){
                    db.updateCourier(request.getId(), request.getId2());
                    oos.writeObject(new Request("SUCCESS_UPDATING"));
                }
                else if (request.getCode().equals("GET_ORDERS_BY_ID_COURIER")){
                    Request request1 = new Request("GET_ORDERS_BY_ID");
                    request1.setOrders(db.getOrdersByIDCourier(request.getId()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("UPDATE_ORDER_STATUS")){
                    db.updateStatus(request.getId(), request.getText());
                    oos.writeObject(new Request("SUCCESS_UPDATING"));
                }
                else if (request.getCode().equals("GET_COSMETICS_BY_ID")){
                    Request request1 = new Request("GET_COSMETICS_BY_ID");
                    request1.setCosmetics(db.getCosmeticsByID(request.getId()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("ADD_QUANTITY_COSMETICS")){
                    db.updateQuantityCosmetics(request.getId(), request.getId2());
                    oos.writeObject(new Request("SUCCESS_UPDATING"));
                }
                else if (request.getCode().equals("GET_FACECARE_BY_ID")){
                    Request request1 = new Request("GET_FACECARE_BY_ID");
                    request1.setFaceAndBodyCares(db.getFaceCareByID(request.getId()));
                    oos.writeObject(request1);
                }
                else if (request.getCode().equals("ADD_QUANTITY_FACECARE")){
                    db.updateQuantityFaceCare(request.getId(), request.getId2());
                    oos.writeObject(new Request("SUCCESS_UPDATING"));
                }
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
