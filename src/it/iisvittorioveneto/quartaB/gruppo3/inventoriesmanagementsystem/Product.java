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

    private String name;
    private String description;
    private String productType;
    private Company manufacturer;
    private final List<InventoryProduct> inventoryProducts;
    private List<Tag> tags;

    public Product() {
        this(null);
    }

    public Product(String name){
        this(name, null, null, null);
    }

    public Product(String name, String description, String productType, Company manufacturer, Inventory[] inventories, int[] quantities, Collection<Tag> tags) {
        this.name = name;
        this.description = description;
        this.productType = productType;
        this.manufacturer = manufacturer;
        this.tags = new LinkedList<>(tags);
        this.inventoryProducts = new LinkedList<>();
        for (int i = 0; i < inventories.length; i++) {
            this.inventoryProducts.add(new InventoryProduct(inventories[i], this, quantities[i]));
        }
    }

    public Product(String name, String description, String productType, Company manufacturer) {
        this(name, description, productType, manufacturer, new Inventory[0], new int[0], new LinkedList<>());
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

    public InventoryProduct[] getInventoryProducts() {
        return this.inventoryProducts.toArray(InventoryProduct[]::new);
    }

    public Inventory[] getInventories() {
        List<Inventory> inventories = new LinkedList<>();
        for (InventoryProduct ip: this.inventoryProducts) {
            inventories.add(ip.getInventory());
        }
        return (Inventory[]) inventories.toArray();
    }

    public void addInventoryProduct(InventoryProduct inventoryProduct) {
        for (InventoryProduct ip : this.inventoryProducts) {
            if (ip.getInventory() == inventoryProduct.getInventory()) {
                throw new IllegalArgumentException("There already is an element with this inventory");
            }
        }
        this.inventoryProducts.add(inventoryProduct);
    }

    public void addTags(Tag tag) {
        this.tags.add(tag);
    }

    public Tag[] getTags() {
        return this.tags.toArray(Tag[]::new);
    }

}
