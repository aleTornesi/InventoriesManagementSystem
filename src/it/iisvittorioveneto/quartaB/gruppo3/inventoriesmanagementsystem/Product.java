/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class Product {

    private Integer idProduct;
    private String name;
    private String description;
    private String productType;
    private Inventory inventory;
    private Integer quantity;
    private Company manufacturer;

    public Product() {this(null, null, null, null, null, null, null);}

    public Product(int idProduct){
        this(idProduct, null, null, null, null, null, null);
    }

    public Product(Integer idProduct, String name, String description, String productType, Company manufacturer, Inventory inventory, Integer quantity) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.productType = productType;
        this.manufacturer = manufacturer;
        this.inventory = inventory;
        this.quantity = quantity;
    }

    public Product(Integer idProduct, String name, String description, String productType, Company manufacturer) {
        this(idProduct, name, description, productType, manufacturer, null, null);
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProductType() {
        return productType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Company getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Company manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
