package com.example.mylogin.pojo;

public class Student_info {
    int s_id;
    String fname;
    String lname;
    String dept_id;
    String pcource_id;
    String scource_id;
    public Student_info(){

    }
    public Student_info(int s_id, String fname, String lname,String dept_id, String pcource_id, String scource_id){
        this.s_id = s_id;
        this.fname = fname;
        this.lname = lname;
        this.dept_id = dept_id;
        this.pcource_id = pcource_id;
        this.scource_id = scource_id;
    }

    public Student_info(String fname,String lname, String dept_id, String pcource_id, String scource_id){
        this.fname = fname;
        this.lname = lname;
        this.dept_id = dept_id;
        this.pcource_id = pcource_id;
        this.scource_id = scource_id;
    }
    public int getID(){
        return this.s_id;
    }

    public void setID(int s_id){
        this.s_id = s_id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getPcource_id() {
        return pcource_id;
    }

    public void setPcource_id(String pcource_id) {
        this.pcource_id = pcource_id;
    }

    public String getScource_id() {
        return scource_id;
    }

    public void setScource_id(String scource_id) {
        this.scource_id = scource_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
