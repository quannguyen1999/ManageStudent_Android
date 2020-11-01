package com.example.myapplication.model;

public class Student {
    private String mssv;
    private String hoTen;
    private String lop;
    private boolean gioiTinh;

    public Student(String mssv, String hoTen, String lop, boolean gioiTinh) {
        this.mssv = mssv;
        this.hoTen = hoTen;
        this.lop = lop;
        this.gioiTinh = gioiTinh;
    }

    public Student() {
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mssv='" + mssv + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", lop='" + lop + '\'' +
                ", gioiTinh=" + gioiTinh +
                '}';
    }
}
