package com.allenanker.webstore.domain;

public class CartItem {
    private Product product;
    private int num;
    private double subTotal
            ;

    public double getItemTotalPrice() {
        return num * product.getShop_price();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
