package com.hoangtuan.nhht.w08;

public class Class {

    private int classID;
    private String className;

    public Class (int idClass, String className){
        this.classID = idClass;
        this.className = className;
    }

    int getClassID(){
        return this.classID;
    }

    String getClassName(){
        return this.className;
    }
}
