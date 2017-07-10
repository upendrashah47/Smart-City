package com.us.smartcity.database;

/**
 * Created by rahulpatil on 16-Feb-16.
 */
public class User {
    int id;
    String FirstName;
    String LastName;
    String Emailid;
    String Password;
    long Mobileno;
    int date;

    public User(){

    }
    public User(int id, String FirstName, String LastName, String Emailid, String Password, long Mobileno, int date){
        this.id=id;
        this.FirstName =FirstName;
        this.LastName=LastName;
        this.Emailid=Emailid;
        this.Password=Password;
        this.Mobileno=Mobileno;
        this.date=date;
    }
    public User(String FirstName, String LastName, String Emailid, String Password, long Mobileno, int date){
        this.FirstName =FirstName;
        this.LastName=LastName;
        this.Emailid=Emailid;
        this.Password=Password;
        this.Mobileno=Mobileno;
        this.date=date;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
       this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getEmailid() {
        return Emailid;
    }

    public void setEmailid(String emailid) {
        this.Emailid = emailid;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public long getMobileno() {
        return Mobileno;
    }

    public void setMobileno(long mobileno) {
        this.Mobileno = mobileno;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

   }
