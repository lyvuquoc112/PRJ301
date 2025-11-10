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
// tên của bảng ở DB viết hoa chữ cái đầu + chức năng + error
public class RegistrationCreateError implements Serializable {

    private String usernameLenghtErr;
    private String passwordLenghtErr;
    private String confirmLenghtErr;
    private String fullNameLenghtErr;
    private String usernameIsExisted;
    // insert chỉ áp dụng đúng chỗ đó
    // nên sử dụng refactor, enscapule file
    public RegistrationCreateError() {
    }

    /**
     * @return the usernameLenghtErr
     */
    public String getUsernameLenghtErr() {
        return usernameLenghtErr;
    }

    /**
     * @param usernameLenghtErr the usernameLenghtErr to set
     */
    public void setUsernameLenghtErr(String usernameLenghtErr) {
        this.usernameLenghtErr = usernameLenghtErr;
    }

    /**
     * @return the passwordLenghtErr
     */
    public String getPasswordLenghtErr() {
        return passwordLenghtErr;
    }

    /**
     * @param passwordLenghtErr the passwordLenghtErr to set
     */
    public void setPasswordLenghtErr(String passwordLenghtErr) {
        this.passwordLenghtErr = passwordLenghtErr;
    }

    /**
     * @return the confirmLenghtErr
     */
    public String getConfirmLenghtErr() {
        return confirmLenghtErr;
    }

    /**
     * @param confirmLenghtErr the confirmLenghtErr to set
     */
    public void setConfirmLenghtErr(String confirmLenghtErr) {
        this.confirmLenghtErr = confirmLenghtErr;
    }

    /**
     * @return the fullNameLenghtErr
     */
    public String getFullNameLenghtErr() {
        return fullNameLenghtErr;
    }

    /**
     * @param fullNameLenghtErr the fullNameLenghtErr to set
     */
    public void setFullNameLenghtErr(String fullNameLenghtErr) {
        this.fullNameLenghtErr = fullNameLenghtErr;
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

}
