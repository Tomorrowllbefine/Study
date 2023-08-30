package com.kk.pojo;


public class User {
    private int id;
    private String name;
    private String pwd;
    private String sex;
    private String phoneNum;
    private String qqNum;

    public User() {
    }

    public User(String name, String pwd, String sex, String phoneNum, String qqNum) {
        this.name = name;
        this.pwd = pwd;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.qqNum = qqNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }
}
