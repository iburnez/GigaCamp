package com.wgu.gigacamp.Model;

public class Account {

    int accountID;
    String fName;
    String lName;
    String email;
    String password;
    String phoneNum;

    public Account (int accountID, String fName, String lName, String email, String password, String phoneNum){
        setAccountID(accountID);
        setfName(fName);
        setlName(lName);
        setEmail(email);
        setPassword(password);
        setPhoneNum(phoneNum);

    }

    /**
     * _______________________________________________________________________
     *                                  SETTERS
     * -----------------------------------------------------------------------
     */

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * _______________________________________________________________________
     *                                  GETTERS
     * -----------------------------------------------------------------------
     */

    public int getAccountID() {
        return accountID;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

}
