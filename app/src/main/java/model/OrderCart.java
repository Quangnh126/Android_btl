package model;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderCart implements Serializable {
    public int id;
    public String username;
    public String address;
    public int subtotal;
//    ArrayList<ItemCart> itemCarts;

    public OrderCart(int id, String username, String address, int subtotal) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

//    public List<ItemCart> getItemCarts() {
//        return itemCarts;
//    }
//
//    public void setItemCarts(ArrayList<ItemCart> itemCarts) {
//        this.itemCarts = itemCarts;
//    }
}
