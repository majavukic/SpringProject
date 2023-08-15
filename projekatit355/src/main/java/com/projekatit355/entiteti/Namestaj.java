package com.projekatit355.entiteti;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "namestaj")
public class Namestaj {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "namestaj_id")
        private int id;

        @Column(name = "namestaj_naziv")
        private String naziv;

        @Column(name = "cena")
        private int cena;

        @Column(name = "slika")
        private String slika;

        @Column(name = "boja")
        private String boja;

        @ManyToOne
        @JoinColumn(name = "vrsta_namestaja_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        private VrstaNamestaja vrstaNamestaja;

        @ManyToMany(fetch = FetchType.LAZY, cascade = {
                        CascadeType.PERSIST,
                        CascadeType.MERGE
        })
        @JoinTable(name = "kupovina", joinColumns = { @JoinColumn(name = "namestaj_id") }, inverseJoinColumns = {
                        @JoinColumn(name = "korisnik_id") })
        @JsonIgnore
        private Set<Korisnik> korisnici = new HashSet<>();

        public Namestaj() {
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

        public int getCena() {
                return cena;
        }

        public void setCena(int cena) {
                this.cena = cena;
        }

        public String getSlika() {
                return slika;
        }

        public void setSlika(String slika) {
                this.slika = slika;
        }

        public String getBoja() {
                return boja;
        }

        public void setBoja(String boja) {
                this.boja = boja;
        }

        public VrstaNamestaja getVrstaNamestaja() {
                return vrstaNamestaja;
        }

        public void setVrstaNamestaja(VrstaNamestaja vrstaNamestaja) {
                this.vrstaNamestaja = vrstaNamestaja;
        }

        public Set<Korisnik> getKorisnici() {
                return korisnici;
        }

        public void setKorisnici(Set<Korisnik> korisnici) {
                this.korisnici = korisnici;
        }

        public Namestaj(String naziv, int cena, String slika, String boja) {
                this.naziv = naziv;
                this.cena = cena;
                this.slika = slika;
                this.boja = boja;
        }

}
