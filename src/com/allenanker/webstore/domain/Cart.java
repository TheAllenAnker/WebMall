package com.allenanker.webstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> itemMap = new HashMap<>();
    private Collection cartItems;
    private double total;

    public void addCartItem(CartItem item) {
        String pid = item.getProduct().getPid();
        if (itemMap.containsKey(pid)) {
            int oldNum = itemMap.get(pid).getNum();
            itemMap.get(pid).setNum(oldNum + item.getNum());
        } else {
            itemMap.put(item.getProduct().getPid(), item);
        }
    }

    public Collection getCartItems() {
        return itemMap.values();
    }

    public void removeCartItem(String pid) {
        itemMap.remove(pid);
    }

    public void clearCart() {
        itemMap.clear();
    }

    public double getTotal() {
        total = 0;
        for (CartItem item : itemMap.values()) {
            total += item.getSubTotal();
        }

        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCartItems(Collection cartItems) {
        this.cartItems = cartItems;
    }
}
