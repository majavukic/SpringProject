package com.projekatit355.entiteti;

import javax.persistence.*;

@Entity
@Table(name = "vrsta_namestaja")
public class VrstaNamestaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vrsta_namestaja_id")
    private int id;

    @Column(name = "vrsta_namestaja_naziv")
    private String naziv;

    public VrstaNamestaja() {
    }

    public VrstaNamestaja(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "VrstaNamestaja [id=" + id + ", naziv=" + naziv + "]";
    }

}
