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
    private List<InventoryProduct> inventoriesProducts;

    public Product() {this(null, null, null, new LinkedList<>(), new LinkedList<>());}

    public Product(String name, String description, String productType, Collection<Inventory> inventories, List<Integer> quantity) {
        this.name = name;
        this.description = description;
        this.productType = productType;
        this.inventoriesProducts = new LinkedList<>();
        if (inventories.size() != quantity.size()) throw new IllegalArgumentException("You have to pass the same number of inventories and quantities");
        Inventory[] inventoriesArray = inventories.toArray(Inventory[]::new);
        Integer[] quantityArray = quantity.toArray(Integer[]::new);
        for (int i = 0; i < inventories.size(); i++) {
            this.inventoriesProducts.add(new InventoryProduct(inventoriesArray[i], this, quantityArray[i]));
        }
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

    public InventoryProduct[] getInventoriesProducts() {
        return inventoriesProducts.toArray(InventoryProduct[]::new);
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

    public void addInventoryProduct(Inventory inventory, int quantity) {
        this.inventoriesProducts.add(new InventoryProduct(inventory, this, quantity));
    }

    public void removeInventoryProduct(InventoryProduct inventoryProduct) {
        this.inventoriesProducts.remove(inventoryProduct);
    }
}
