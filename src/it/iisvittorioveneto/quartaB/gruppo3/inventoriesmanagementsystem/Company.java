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
public class Company {

    private Integer id;
    private String name;
    private String CAP;
    private String phoneNumber;
    private String email;
    private List<Product> products;

    public Company() {
        this(null,null, null, null, null, new LinkedList<>());
    }

    public Company(Integer integer, String name, String CAP, String phoneNumber, String email, Collection<Product> products) {
        this.name = name;
        this.CAP = CAP;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.products = Arrays.asList(products.toArray(Product[]::new));
    }

    public Company(int idCompany) {
        this(idCompany, null, null, null, null, new LinkedList<>());
    }

    public String getName() {
        return name;
    }

    public String getCAP() {
        return CAP;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Product[] getProducts() {
        return products.toArray(Product[]::new);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}
