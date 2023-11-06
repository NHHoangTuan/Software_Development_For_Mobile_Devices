package com.hoangtuan.nhht.w08;

public class Student {

    private String fullName;
    private String studentID;

    private double avg;

    private int classID;

    public Student(String fullName, String studentID, int classID, double avg){
        this.fullName = fullName;
        this.studentID = studentID;
        this.classID = classID;
        this.avg = avg;
    }

    String getFullName(){
        return this.fullName;
    }

    String getStudentID(){
        return this.studentID;
    }

    int getClassID(){
        return this.classID;
    }

    double getAvg(){
        return this.avg;
    }

}
