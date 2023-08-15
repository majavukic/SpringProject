package com.projekatit355.kontroleri;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekatit355.entiteti.Korisnik;
import com.projekatit355.entiteti.KorisnikPersonalniPodaci;
import com.projekatit355.helper.NePostojiResursException;
import com.projekatit355.repozitorijum.KorisnikPersonalniPodaciRepository;
import com.projekatit355.repozitorijum.KorisnikRepository;

@RestController
@RequestMapping("/projekatit355")
@CrossOrigin("http://localhost:4200")
public class KorisnikController {

    @Autowired
    private KorisnikRepository _korisnikRepository;

    @Autowired
    private KorisnikPersonalniPodaciRepository _korisnikPersonalniPodaciRepository;

    @GetMapping("/korisnici")
    public ResponseEntity<List<Korisnik>> getAll() {

        List<Korisnik> korisnici = new ArrayList<Korisnik>();

        this._korisnikRepository.findAll().forEach(korisnici::add);

        if (korisnici.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }

    @GetMapping("/korisnici/{id}")
    public ResponseEntity<Korisnik> getKorisnikById(@PathVariable("id") int id) {

        Korisnik korisnik = _korisnikRepository.findById(id)
                .orElseThrow(() -> new NePostojiResursException("Not found Korisnik with id = " + id));

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @PutMapping("/korisnici/{id}")
    public ResponseEntity<Korisnik> updateKorisnik(@PathVariable("id") int id, @RequestBody Korisnik korisnik) {

        Korisnik _korisnik = _korisnikRepository.findById(id)
                .orElseThrow(() -> new NePostojiResursException("Not found Korisnik with id = " + id));

        if (korisnik.getKorisnickoIme() != null)
            _korisnik.setKorisnickoIme(korisnik.getKorisnickoIme());

        if (korisnik.getLozinka() != null)
            _korisnik.setLozinka(korisnik.getLozinka());

        return new ResponseEntity<>(_korisnikRepository.save(_korisnik), HttpStatus.OK);
    }

    @PostMapping("/korisnikPersonalniPodaci/{korisnikPersonalniPodaciId}/korisnici")
    public ResponseEntity<Korisnik> createKorisnik(@PathVariable int korisnikPersonalniPodaciId,
            @RequestBody Korisnik theKorisnik) {

        KorisnikPersonalniPodaci tempKorisnikPersonalniPodaci = _korisnikPersonalniPodaciRepository
                .findById(korisnikPersonalniPodaciId)
                .orElseThrow(() -> new NePostojiResursException(
                        "KorisnikPersonalniPodaci with id " + korisnikPersonalniPodaciId + " doesn't exist."));

        theKorisnik.setKorisnikPersonalniPodaci(tempKorisnikPersonalniPodaci);

        _korisnikRepository.save(theKorisnik);

        return new ResponseEntity<>(theKorisnik, HttpStatus.CREATED);
    }

    @DeleteMapping("/korisnici/{id}")
    public ResponseEntity<HttpStatus> deleteKorisnik(@PathVariable int id) {

        Korisnik korisnik = _korisnikRepository
                .findById(id)
                .orElseThrow(() -> new NePostojiResursException("Korisnik with id " + id + " doesn't exist."));

        _korisnikRepository.deleteById(korisnik.getId()); // delete PersonalniPodaci also

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
