package com.projekatit355.entiteti;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "korisnik_personalni_podaci")
public class KorisnikPersonalniPodaci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korisnik_personalni_podaci_id")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "email_adresa")
    private String emailAdresa;

    @OneToOne(mappedBy = "korisnikPersonalniPodaci")
    @JsonIgnore
    private Korisnik korisnik;

    public KorisnikPersonalniPodaci() {
    }

    public KorisnikPersonalniPodaci(String ime, String prezime, String emailAdresa, Korisnik korisnik) {
        this.ime = ime;
        this.prezime = prezime;
        this.emailAdresa = emailAdresa;
        this.korisnik = korisnik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmailAdresa() {
        return emailAdresa;
    }

    public void setEmailAdresa(String emailAdresa) {
        this.emailAdresa = emailAdresa;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public String toString() {
        return "KorisnikPersonalniPodaci [emailAdresa=" + emailAdresa + ", id=" + id + ", ime=" + ime + ", korisnik="
                + korisnik + ", prezime=" + prezime + "]";
    }
}
