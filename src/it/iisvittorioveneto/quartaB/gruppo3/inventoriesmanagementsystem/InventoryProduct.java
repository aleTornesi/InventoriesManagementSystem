/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem;

/**
 *
 * @author User
 */
public class InventoryProduct {

    private Inventory inventory;
    private Product product;
    private int quantity;

    public InventoryProduct() {
    }

    public InventoryProduct(Inventory inventory, Product product, int quantity) {
        this.inventory = inventory;
        this.product = product;
        this.quantity = quantity;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
