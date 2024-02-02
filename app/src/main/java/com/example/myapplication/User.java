package com.example.myapplication;

public class User {
    private String name, iduser, statut;

    public User(){  }

    public User(String iduser, String name, String statut) {
        this.name = name;
        this.iduser = iduser;
        this.statut = statut;
    }

    public String getName() {
        return name;
    }

    public String getIduser() {
        return iduser;
    }

    public String getStatut() {
        return statut;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
