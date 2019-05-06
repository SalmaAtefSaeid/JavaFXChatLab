/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonservicejavafxlab1;

import java.io.Serializable;


/**
 *
 * @author pc
 */
public class Client implements Serializable {
    String name;
    String message;
    byte[] imagePath;

    public Client(String name, String message, byte[] imagePath) {
        this.name = name;
        this.message = message;
        this.imagePath = imagePath;
    }

    public byte[] getImagePath() {
        return imagePath;
    }

    public void setImagePath(byte[] imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Client(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public Client() {
    }
    
}
