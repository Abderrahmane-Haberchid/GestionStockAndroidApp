package com.example.myapplication;

public class Product {
    private String title, desc, qte, date, cat, etat, id;

    public Product(){ }

    public Product(String title, String desc, String qte, String date, String cat, String etat, String idprod) {
        this.title = title;
        this.desc = desc;
        this.qte = qte;
        this.date = date;
        this.cat = cat;
        this.etat = etat;
        this.id = idprod;
    }
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getQte() {
        return qte;
    }

    public String getDate() {
        return date;
    }

    public String getCat(){
        return cat;
    }

    public String getEtat(){
        return etat;
    }

    public void setEtat(String etat){
        this.etat = etat;
    }

    public void setCat(String cat){
        this.cat = cat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
