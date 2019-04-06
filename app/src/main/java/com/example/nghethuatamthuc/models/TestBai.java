package com.example.nghethuatamthuc.models;

public class TestBai {
    int id;
    String ten;

    public TestBai() {
    }

    public TestBai(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
