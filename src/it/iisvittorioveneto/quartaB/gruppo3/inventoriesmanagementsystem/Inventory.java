/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class Inventory {

    private Integer idInventory;
    private User owner;
    private String name;
    private float full;
    private List<Product> products;


    public Inventory() {this((User) null);}

    public Inventory(int idInventory) {
        this(idInventory, null, null, 0, new LinkedList<>());
    }

    public Inventory(String name) {
        this(null, null, name, 0, new LinkedList<>());
    }

    public Inventory(User user) {
        this(null, user, null, 0, new LinkedList<>());
    }

    public Inventory(Integer idInventory, User owner, String name, float full, Collection<Product> products) {
        this.idInventory = idInventory;
        this.owner = owner;
        this.name = name;
        this.full = full;
        // this.products = Arrays.asList(products.toArray(Product[]::new));
        this.products = new LinkedList<>(products);
    }

    public Inventory(Integer idInventory, User owner, String name, float full) {
        this(idInventory, owner, name, full, new LinkedList<>());
    }

    public int getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(int idInventory){
        this.idInventory = idInventory;
    }

    public String getName() {
        return name;
    }

    public float getFull() {
        return full;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFull(float full) {
        this.full = full;
    }

    public Product[] getProducts() {
        return products.toArray(Product[]::new);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
