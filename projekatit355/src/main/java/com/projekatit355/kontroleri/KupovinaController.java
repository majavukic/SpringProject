package com.projekatit355.kontroleri;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekatit355.entiteti.Korisnik;
import com.projekatit355.entiteti.Kupovina;
import com.projekatit355.entiteti.KupovinaEmbeddable;
import com.projekatit355.entiteti.Namestaj;
import com.projekatit355.helper.NePostojiResursException;
import com.projekatit355.repozitorijum.KupovinaRepository;
import com.projekatit355.repozitorijum.KorisnikRepository;
import com.projekatit355.repozitorijum.NamestajRepository;

@RestController
@RequestMapping("/projekatit355")
@CrossOrigin("http://localhost:4200")
public class KupovinaController {

    @Autowired
    private KupovinaRepository _kupovinaRepository;

    @Autowired
    private KorisnikRepository _korisnikRepository;

    @Autowired
    private NamestajRepository _namestajRepository;

    @GetMapping("/kupovine")
    public ResponseEntity<List<Kupovina>> getAllKupovina() {

        List<Kupovina> kupovine = new ArrayList<>();

        this._kupovinaRepository.findAll().forEach(kupovine::add);

        if (kupovine.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(kupovine, HttpStatus.OK);
    }

    @PostMapping("/korisnici/{korisnikId}/namestaji/{namestajId}")
    public ResponseEntity<Kupovina> dodajKorisnikaINamestaj(@PathVariable int korisnikId,
            @PathVariable int namestajId) {

        Korisnik korisnik = _korisnikRepository
                .findById(korisnikId)
                .orElseThrow(() -> new NePostojiResursException("Korisnik with id " + korisnikId + " doesn't exist"));

        Namestaj tempNamestaj = _namestajRepository
                .findById(namestajId)
                .orElseThrow(() -> new NePostojiResursException("Namestaj with id " + namestajId + " doesn't exist"));

        Kupovina kupovina = new Kupovina();

        KupovinaEmbeddable kupovinaId = new KupovinaEmbeddable();
        kupovinaId.setKorisnikId(korisnik.getId());
        kupovinaId.setNamestajId(tempNamestaj.getId());

        kupovina.setKupovina(kupovinaId);
        kupovina.setKorisnik(korisnik);
        kupovina.setNamestaj(tempNamestaj);

        _kupovinaRepository.save(kupovina);

        return new ResponseEntity<>(kupovina, HttpStatus.CREATED);
    }

}
