package br.com.m2msolutions.couchbaselighttest;

import java.util.List;

public class Trajeto {
    private String nome;
    private GeoPoint linestring;

    public Trajeto() {
    }

    public Trajeto(String nome, GeoPoint linestring) {
        this.nome = nome;
        this.linestring = linestring;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public GeoPoint getLinestring() {
        return linestring;
    }

    public void setLinestring(GeoPoint linestring) {
        this.linestring = linestring;
    }
}
