package com.example.pccuong.appbook.model.ObjectClass;

/**
 * Created by PCCuong on 2/26/2017.
 */

public class Users {
    String name;
    String encrypted_password;
    String address;
    String EmailDocQuyen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailDocQuyen() {
        return EmailDocQuyen;
    }

    public void setEmailDocQuyen(String emailDocQuyen) {
        EmailDocQuyen = emailDocQuyen;
    }
}
