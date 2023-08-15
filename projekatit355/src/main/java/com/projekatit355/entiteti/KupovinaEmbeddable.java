package com.projekatit355.entiteti;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class KupovinaEmbeddable implements Serializable {

    @Column(name = "namestaj_id")
    private int namestajId;

    @Column(name = "korisnik_id")
    private int korisnikId;

    public KupovinaEmbeddable() {
    }

    public int getNamestajId() {
        return namestajId;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setNamestajId(int namestajId) {
        this.namestajId = namestajId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    public KupovinaEmbeddable(int namestajId, int korisnikId) {
        this.namestajId = namestajId;
        this.korisnikId = korisnikId;
    }

    @Override
    public String toString() {
        return "KupovinaEmbeddable [korisnikId=" + korisnikId + ", namestajId=" + namestajId + "]";
    }

}
