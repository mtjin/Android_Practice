package com.example.a82107.mylistview_adapter;

public class SingerItem {
    String name;
    String mobile;
    int resid;

    public SingerItem(String name, String mobile, int resid) {
        this.name = name;
        this.mobile = mobile;
        this.resid = resid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", resid=" + resid +
                '}';
    }
}
