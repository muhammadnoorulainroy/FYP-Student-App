package com.example.studentapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class ParentSessionManager {

    SharedPreferences puserSession;
    SharedPreferences.Editor peditor;
    Context pcontext;

    public static final String PSESSION_USERSESSION = "puserLoginSession";
    public static final String PSESSION_REMEMBERME = "prememberMe";

    //User Session Variables
    private static final String PIS_LOGIN = "PIsLoggedIn";

    public static final String PKEY_NAME = "fullName";
    public static final String PKEY_EMAIL = "email";
    public static final String PKEY_HADDRESS = "haddress";
    public static final String PKEY_PHONE_NO = "phone_No";
    public static final String PKEY_PASSWORD = "password";

    //Remember Me Variables
    private static final String PIS_REMEMBERME = "PIsRememberMe";

    public static final String PKEY_SESSIONPHONE_NO = "phone_No";
    public static final String PKEY_SESSIONPASSWORD = "password";


    //Constructor
    public ParentSessionManager(Context _context, String sessionName) {
        pcontext = _context;
        puserSession = pcontext.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        peditor = puserSession.edit();
    }

    //Login Sessions
    public void createLoginSession(String fullName, String email, String haddress, String phone_No, String password) {
        peditor.putBoolean(PIS_LOGIN, true);

        peditor.putString(PKEY_NAME, fullName);
        peditor.putString(PKEY_EMAIL, email);
        peditor.putString(PKEY_HADDRESS, haddress);
        peditor.putString(PKEY_PHONE_NO, phone_No);
        peditor.putString(PKEY_PASSWORD, password);

        peditor.commit();
    }

    public HashMap<String, String> getUserDetailFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(PKEY_NAME, puserSession.getString(PKEY_NAME, null));
        userData.put(PKEY_EMAIL, puserSession.getString(PKEY_EMAIL, null));
        userData.put(PKEY_HADDRESS, puserSession.getString(PKEY_HADDRESS, null));
        userData.put(PKEY_PASSWORD, puserSession.getString(PKEY_PASSWORD, null));
        userData.put(PKEY_PHONE_NO, puserSession.getString(PKEY_PHONE_NO, null));

        return userData;
    }

    public boolean checkLogin() {

        if (puserSession.getBoolean(PIS_LOGIN, false)) {
            return true;
        } else {
            return false;
        }
    }

    public void logoutUserFromSession() {
        peditor.clear();
        peditor.commit();
    }


    //RememberMe Sessions
    public void createRememberMeSession(String phone_No, String password) {
        peditor.putBoolean(PIS_REMEMBERME, true);

        peditor.putString(PKEY_SESSIONPHONE_NO, phone_No);
        peditor.putString(PKEY_SESSIONPASSWORD, password);

        peditor.commit();
    }

    public HashMap<String, String> getRememberMeDetailFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();


        userData.put(PKEY_SESSIONPHONE_NO, puserSession.getString(PKEY_SESSIONPHONE_NO, null));
        userData.put(PKEY_SESSIONPASSWORD, puserSession.getString(PKEY_SESSIONPASSWORD, null));

        return userData;
    }

    public boolean checkRememberMe() {

        if (puserSession.getBoolean(PIS_REMEMBERME, false)) {
            return true;
        } else {
            return false;
        }
    }
}
