package com.projekatit355.entiteti;

import javax.persistence.*;

@Entity
@Table(name = "kupovina")
public class Kupovina {

    @EmbeddedId
    KupovinaEmbeddable kupovina;

    @ManyToOne
    @MapsId("namestajId")
    @JoinColumn(name = "namestaj_id")
    Namestaj namestaj;

    @ManyToOne
    @MapsId("korisnikId")
    @JoinColumn(name = "korisnik_id")
    Korisnik korisnik;

    public Kupovina() {
    }

    public KupovinaEmbeddable getKupovina() {
        return kupovina;
    }

    public void setKupovina(KupovinaEmbeddable kupovina) {
        this.kupovina = kupovina;
    }

    public Namestaj getNamestaj() {
        return namestaj;
    }

    public void setNamestaj(Namestaj namestaj) {
        this.namestaj = namestaj;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public String toString() {
        return "Kupovina [korisnik=" + korisnik + ", kupovina=" + kupovina + ", namestaj=" + namestaj + "]";
    }

    public Kupovina(KupovinaEmbeddable kupovina, Namestaj namestaj, Korisnik korisnik) {
        this.kupovina = kupovina;
        this.namestaj = namestaj;
        this.korisnik = korisnik;
    }

}
