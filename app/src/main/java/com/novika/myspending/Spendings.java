package com.novika.myspending;

public class Spendings {

    String name, date;
    int id, nominal;

    public Spendings(String name, String date, int id, int nominal) {
        this.name = name;
        this.date = date;
        this.id = id;
        this.nominal = nominal;
    }

    public Spendings() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
}
