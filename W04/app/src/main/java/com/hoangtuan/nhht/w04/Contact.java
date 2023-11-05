package com.hoangtuan.nhht.w04;

public class Contact {
    private String fullname;
    private String phoneNumber;
    private int imgResource;

    public Contact(String fullname,String phoneNumber,int imgResource){
        this.fullname=fullname;
        this.phoneNumber=phoneNumber;
        this.imgResource=imgResource;
    }

    public String getFullname(){
        return fullname;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public int getImgResource(){
        return imgResource;
    }
}
