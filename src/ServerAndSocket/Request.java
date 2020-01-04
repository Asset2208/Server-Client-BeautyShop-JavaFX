package ServerAndSocket;

import mainClasses.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    private String code;
    private User user;
    private Staff staff;
    private Cosmetics cosmetic;
    private Basket basket;
    private FaceAndBodyCare faceAndBodyCare;
    private HistoryPurchase historyPurchase;
    private Order order;
    private ArrayList<User> users = null;
    private ArrayList<Staff> staffs = null;
    private ArrayList<Cosmetics> cosmetics = null;
    private ArrayList<FaceAndBodyCare> faceAndBodyCares = null;
    private ArrayList<Basket> baskets = null;
    private ArrayList<HistoryPurchase> historyPurchases = null;
    private ArrayList<Order> orders = null;

    private String text;
    private String text2;
    private boolean aBoolean;
    private int id;
    private int id2;
    private int id3;
    private int id4;

    public Request(String code, User user) {
        this.code = code;
        this.user = user;
    }

    public Request(String code, int id, int id2) {
        this.code = code;
        this.id = id;
        this.id2 = id2;
    }

    public Request(String code, int id) {
        this.code = code;
        this.id = id;
    }

    public Request(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public Request(String code, int id, int id2, int id3) {
        this.code = code;
        this.id = id;
        this.id2 = id2;
        this.id3 = id3;
    }

    public Request(String code, int id, int id2, int id3, int id4) {
        this.code = code;
        this.id4 = id4;
        this.id = id;
        this.id2 = id2;
        this.id3 = id3;
    }

    public Request(String code, String text, String text2) {
        this.code = code;
        this.text = text;
        this.text2 = text2;
    }

    public Request(String code, int id, String text) {
        this.code = code;
        this.text = text;
        this.id = id;
    }

    public int getId2() {
        return id2;
    }

    public int getId4() {
        return id4;
    }

    public void setId4(int id4) {
        this.id4 = id4;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public Request(String code, Cosmetics cosmetic) {
        this.code = code;
        this.cosmetic = cosmetic;
    }

    public Request(String code, Order order) {
        this.code = code;
        this.order = order;
    }

    public Request(String code, HistoryPurchase historyPurchase) {
        this.code = code;
        this.historyPurchase = historyPurchase;
    }

    public Request(String code, ArrayList<Basket> baskets) {
        this.code = code;
        this.baskets = baskets;
    }

    public Request(String code, Staff staff) {
        this.code = code;
        this.staff = staff;
    }

    public Request(String code, Basket basket) {
        this.code = code;
        this.basket = basket;
    }

    public Request(String code, FaceAndBodyCare faceAndBodyCare) {
        this.code = code;
        this.faceAndBodyCare = faceAndBodyCare;
    }

    public HistoryPurchase getHistoryPurchase() {
        return historyPurchase;
    }

    public void setHistoryPurchase(HistoryPurchase historyPurchase) {
        this.historyPurchase = historyPurchase;
    }

    public ArrayList<HistoryPurchase> getHistoryPurchases() {
        return historyPurchases;
    }

    public void setHistoryPurchases(ArrayList<HistoryPurchase> historyPurchases) {
        this.historyPurchases = historyPurchases;
    }

    public int getId3() {
        return id3;
    }

    public void setId3(int id3) {
        this.id3 = id3;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public ArrayList<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(ArrayList<Basket> baskets) {
        this.baskets = baskets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Request(String code) {
        this.code = code;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Cosmetics getCosmetic() {
        return cosmetic;
    }

    public void setCosmetic(Cosmetics cosmetic) {
        this.cosmetic = cosmetic;
    }

    public FaceAndBodyCare getFaceAndBodyCare() {
        return faceAndBodyCare;
    }

    public void setFaceAndBodyCare(FaceAndBodyCare faceAndBodyCare) {
        this.faceAndBodyCare = faceAndBodyCare;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(ArrayList<Staff> staffs) {
        this.staffs = staffs;
    }

    public ArrayList<Cosmetics> getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(ArrayList<Cosmetics> cosmetics) {
        this.cosmetics = cosmetics;
    }

    public ArrayList<FaceAndBodyCare> getFaceAndBodyCares() {
        return faceAndBodyCares;
    }

    public void setFaceAndBodyCares(ArrayList<FaceAndBodyCare> faceAndBodyCares) {
        this.faceAndBodyCares = faceAndBodyCares;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "(REQUEST HANDLER) CODE = " + "[" + code + "]";
    }
}
