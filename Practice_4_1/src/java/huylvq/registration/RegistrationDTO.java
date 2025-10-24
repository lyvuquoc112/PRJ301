/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylvq.registration;

import java.io.Serializable;

/**
 *
 * @author hanly
 */
public class RegistrationDTO implements Serializable{
    private String username;
    private String password;
    private String lastname;
    private boolean isAdimn;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, String lastname, boolean isAdimn) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.isAdimn = isAdimn;
    }
    

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the isAdimn
     */
    public boolean isIsAdimn() {
        return isAdimn;
    }

    /**
     * @param isAdimn the isAdimn to set
     */
    public void setIsAdimn(boolean isAdimn) {
        this.isAdimn = isAdimn;
    }
    
}
