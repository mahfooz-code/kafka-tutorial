/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mahfooz.kafka.model;

/**
 * @author Mahfooz Alam
 */
public class Customer {
    private int customerID;
    private String customerName;

    public Customer(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
    }

    public Customer() {

    }

    public int getID() {
        return customerID;
    }

    public String getName() {
        return customerName;
    }
}
