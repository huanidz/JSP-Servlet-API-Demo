package com.studentproject.model;

public class StudentModel {
    int id;
    String hoTen;
    String queQuan;
    String sdt;
    int classID;

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", hoTen='" + hoTen + '\'' +
                ", queQuan='" + queQuan + '\'' +
                ", sdt='" + sdt + '\'' +
                ", classID=" + classID +
                '}';
    }

    public StudentModel() {
    }

    public StudentModel(int id, String hoTen, String queQuan, String sdt, int classID) {
        this.id = id;
        this.hoTen = hoTen;
        this.queQuan = queQuan;
        this.sdt = sdt;
        this.classID = classID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }
}
