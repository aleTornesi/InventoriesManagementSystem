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

    private String name;
    private String CAP;
    private String phoneNumber;
    private String email;

    public Company() {
        this(null, null, null, null);
    }

    public Company(String name, String CAP, String phoneNumber, String email) {
        this.name = name;
        this.CAP = CAP;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Company(String name) {
        this(name, null, null, null);
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
}
