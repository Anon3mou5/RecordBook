package com.example.addrecyclerview;

public class data {
    String lname,usn,gender,age,fname;
    int count;

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public data(String lname, String usn, String gender, String age, String fname,int count) {
        this.lname = lname;
        this.usn = usn;
        this.gender = gender;
        this.age = age;
        this.fname = fname;
        this.count=count;
    }
}
