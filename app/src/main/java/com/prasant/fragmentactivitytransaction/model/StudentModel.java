package com.prasant.fragmentactivitytransaction.model;

import java.io.Serializable;

public class StudentModel implements Serializable {

    private int std_Id;
    private String std_name;
    private String std_email;
    private String std_phone;

    public int getStd_Id() {
        return std_Id;
    }

    public void setStd_Id(int std_Id) {
        this.std_Id = std_Id;
    }

    public String getStd_name() {
        return std_name;
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }

    public String getStd_email() {
        return std_email;
    }

    public void setStd_email(String std_email) {
        this.std_email = std_email;
    }

    public String getStd_phone() {
        return std_phone;
    }

    public void setStd_phone(String std_phone) {
        this.std_phone = std_phone;
    }
}
