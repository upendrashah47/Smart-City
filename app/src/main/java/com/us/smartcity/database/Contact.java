package com.us.smartcity.database;

import javax.xml.transform.sax.SAXResult;

/**
 * Created by Bhaskar on 17-02-2016.
 */
public class Contact {

    String fname,lname,email,pwd,repwd,mno,dob;


    public void setName(String name)
    {
        this.fname=name;
    }
    public String getName()
    {
        return this.fname;
    }
    public void setLName(String lname)
    {
        this.lname=lname;
    }
    public String getLname()
    {
        return this.lname;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setPwd(String pwd)
    {
        this.pwd=pwd;
    }
    public String getPwd()
    {
        return this.pwd;
    }
    public void setMno(String mno)
    {
        this.mno=mno;
    }
    public String getMno()
    {
        return this.mno;
    }
    public void setDob(String dob)
    {
        this.dob=dob;
    }
    public String getDob()
    {
        return this.dob;
    }
}
