package com.example.studentapp;

public class DummyHelperClass {
    String _phone_No;
    String getfName;
    String getEmail;
    String getHAddress;
    String getIAddress;
    String getPassword;
    String getAge;
    String getGender;

    public DummyHelperClass(){

    }

    public DummyHelperClass(String getfName, String getEmail, String getHAddress, String getIAddress, String getPassword, String getAge, String getGender,String _phone_No) {

        this._phone_No = _phone_No;
        this.getfName = getfName;
        this.getEmail = getEmail;
        this.getHAddress = getHAddress;
        this.getIAddress = getIAddress;
        this.getPassword = getPassword;
        this.getAge = getAge;
        this.getGender = getGender;
    }

    public String get_phone_No() {
        return _phone_No;
    }

    public void set_phone_No(String _phone_No) {
        this._phone_No = _phone_No;
    }

    public String getGetfName() {
        return getfName;
    }

    public void setGetfName(String getfName) {
        this.getfName = getfName;
    }

    public String getGetEmail() {
        return getEmail;
    }

    public void setGetEmail(String getEmail) {
        this.getEmail = getEmail;
    }

    public String getGetHAddress() {
        return getHAddress;
    }

    public void setGetHAddress(String getHAddress) {
        this.getHAddress = getHAddress;
    }

    public String getGetIAddress() {
        return getIAddress;
    }

    public void setGetIAddress(String getIAddress) {
        this.getIAddress = getIAddress;
    }

    public String getGetPassword() {
        return getPassword;
    }

    public void setGetPassword(String getPassword) {
        this.getPassword = getPassword;
    }

    public String getGetAge() {
        return getAge;
    }

    public void setGetAge(String getAge) {
        this.getAge = getAge;
    }

    public String getGetGender() {
        return getGender;
    }

    public void setGetGender(String getGender) {
        this.getGender = getGender;
    }
}
