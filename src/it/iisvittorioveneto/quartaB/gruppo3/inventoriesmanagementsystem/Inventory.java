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
public class Inventory {

    private User owner;
    private String name;
    private float full;
    private List<InventoryProduct> inventoriesProducts;

    public Inventory() {this((User) null);}

    public Inventory(String name) {
        this(null, name, 0, new LinkedList<>(), new LinkedList<>());
    }

    public Inventory(User user) {
        this(user, null, 0, new LinkedList<>(), new LinkedList<>());
    }



    public Inventory(User owner, String name, float full, Collection<Product> products, Collection<Integer> quantities) {
        this.owner = owner;
        this.name = name;
        this.full = full;
        this.inventoriesProducts = new LinkedList<>();
        if (products.size() != quantities.size()) throw new IllegalArgumentException("You have to pass the same number of products and quantities");
        Product[] productsArray = products.toArray(Product[]::new);
        Integer[] quantitiesArray = quantities.toArray(Integer[]::new);
        for (int i = 0; i < productsArray.length; i++) {
            this.inventoriesProducts.add(new InventoryProduct(this, productsArray[i], quantitiesArray[i]));
        }
    }

    public String getName() {
        return name;
    }

    public float getFull() {
        return full;
    }

    public User getOwner() {
        return owner;
    }

    public InventoryProduct[] getInventoriesProducts() {
        return this.inventoriesProducts.toArray(InventoryProduct[]::new);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFull(float full) {
        this.full = full;
    }

    public void addInventoryProduct(Inventory inventory, Product product, int quantity) {
        this.inventoriesProducts.add(new InventoryProduct(inventory, product, quantity));
    }

    public void removeInventoryProduct(InventoryProduct inventoryProduct) {
        this.inventoriesProducts.remove(inventoryProduct);
    }
}
