package com.yaoxiang.domain;

import java.util.Random;

public class User {
    private String id;
    private String name;
    private String password;
    private boolean status;
    public User() {
        //调用createID方法，生成id
        id = createID();
        //修改status为true
        status = true;
    }
    public User(String name, String password) {
        id = createID();
        this.name = name;
        this.password = password;
        status = true;
    }
    //用户无法设置，是自动生成的，格式为：heima+5位数字的随机数
    public String createID(){
        StringBuilder sb=new StringBuilder("heimma");

        Random r=new Random();
        for (int i = 0; i < 5; i++) {
            int num = r.nextInt(10);
            sb.append(num);
        }
        return "";
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
