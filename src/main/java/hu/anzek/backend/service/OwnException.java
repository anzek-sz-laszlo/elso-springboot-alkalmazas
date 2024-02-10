/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.service;


/**
 *
 * @author User
 */
public class OwnException extends Exception {
    
    private static final long serialVersionUID = 1L;
    private String message;

    public OwnException() {
    }

    public OwnException(String message) {
        super(message);
    }
}
