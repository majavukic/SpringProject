package com.projekatit355.entiteti;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "korisnik")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korisnik_id")
    private int id;

    @Column(name = "korisnicko_ime")
    private String korisnickoIme;

    @Column(name = "lozinka")
    private String lozinka;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_personalni_podaci_id")
    private KorisnikPersonalniPodaci korisnikPersonalniPodaci;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "kupovina", joinColumns = { @JoinColumn(name = "korisnik_id") }, inverseJoinColumns = {
            @JoinColumn(name = "namestaj_id") })
    private Set<Namestaj> namestaji = new HashSet<>();

    public Korisnik() {
    }

    public Korisnik(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public KorisnikPersonalniPodaci getKorisnikPersonalniPodaci() {
        return korisnikPersonalniPodaci;
    }

    public void setKorisnikPersonalniPodaci(KorisnikPersonalniPodaci korisnikPersonalniPodaci) {
        this.korisnikPersonalniPodaci = korisnikPersonalniPodaci;
    }

    public Set<Namestaj> getNamestaji() {
        return namestaji;
    }

    public void setNamestaji(Set<Namestaj> namestaji) {
        this.namestaji = namestaji;
    }

    public void addNamestaj(Namestaj namestaj) {
        this.namestaji.add(namestaj);
        namestaj.getKorisnici().add(this);
    }

    public void removeNamestaj(int namestajId) {
        Namestaj namestaj = this.namestaji.stream().filter(t -> t.getId() == namestajId).findFirst().orElse(null);

        if (namestaj != null) {
            this.namestaji.remove(namestaj);
            namestaj.getKorisnici().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Korisnik [korisnikPersonalniPodaci=" + korisnikPersonalniPodaci
                + ", id="
                + id + ", lozinka=" + lozinka + ", korisnickoIme=" + korisnickoIme + "]";
    }
}
