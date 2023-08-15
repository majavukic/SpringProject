package com.projekatit355.kontroleri;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekatit355.entiteti.KorisnikPersonalniPodaci;
import com.projekatit355.helper.NePostojiResursException;
import com.projekatit355.repozitorijum.KorisnikPersonalniPodaciRepository;

@RestController
@RequestMapping("/projekatit355")
@CrossOrigin("http://localhost:4200")
public class KorisnikPersonalniPodaciController {

    @Autowired
    private KorisnikPersonalniPodaciRepository _korisnikPersonalniPodaciRepository;

    @GetMapping("/korisnikPersonalniPodaci")
    public ResponseEntity<List<KorisnikPersonalniPodaci>> getAllKorisnikPersonalniPodaci() {

        List<KorisnikPersonalniPodaci> korisnikPersonalniPodaci = new ArrayList<>();

        this._korisnikPersonalniPodaciRepository.findAll().forEach(korisnikPersonalniPodaci::add);

        if (korisnikPersonalniPodaci.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(korisnikPersonalniPodaci, HttpStatus.OK);
    }

    @GetMapping("/korisnikPersonalniPodaci/{id}")
    public ResponseEntity<KorisnikPersonalniPodaci> getKorisnikPersonalniPodaciById(@PathVariable int id) {

        KorisnikPersonalniPodaci korisnikPersonalniPodaci = _korisnikPersonalniPodaciRepository
                .findById(id)
                .orElseThrow(
                        () -> new NePostojiResursException(
                                "KorisnikPersonalniPodaci with id " + id + " doesn't exist."));

        return new ResponseEntity<>(korisnikPersonalniPodaci, HttpStatus.OK);
    }

    @PostMapping("/korisnikPersonalniPodaci")
    public ResponseEntity<KorisnikPersonalniPodaci> createKorisnikPersonalniPodaci(
            @RequestBody KorisnikPersonalniPodaci korisnikPersonalniPodaci) {

        return new ResponseEntity<>(_korisnikPersonalniPodaciRepository.save(korisnikPersonalniPodaci),
                HttpStatus.CREATED);
    }

    @PutMapping("/korisnikPersonalniPodaci/{id}")
    public ResponseEntity<KorisnikPersonalniPodaci> updateKorisnikPersonalniPodaci(@PathVariable int id,
            @RequestBody KorisnikPersonalniPodaci korisnikPersonalniPodaci) {

        KorisnikPersonalniPodaci _korisnikPersonalniPodaci = _korisnikPersonalniPodaciRepository
                .findById(id)
                .orElseThrow(
                        () -> new NePostojiResursException(
                                "KorisnikPersonalniPodaci with id " + id + " doesn't exist."));

        if (korisnikPersonalniPodaci.getIme() != null)
            _korisnikPersonalniPodaci.setIme(korisnikPersonalniPodaci.getIme());

        if (korisnikPersonalniPodaci.getPrezime() != null)
            _korisnikPersonalniPodaci.setPrezime(korisnikPersonalniPodaci.getPrezime());

        if (korisnikPersonalniPodaci.getEmailAdresa() != null)
            _korisnikPersonalniPodaci.setEmailAdresa(korisnikPersonalniPodaci.getEmailAdresa());

        return new ResponseEntity<>(_korisnikPersonalniPodaciRepository.save(_korisnikPersonalniPodaci), HttpStatus.OK);
    }

    @DeleteMapping("/korisnikPersonalniPodaci/{id}")
    public ResponseEntity<HttpStatus> deleteKorisnikPersonalniPodaci(@PathVariable int id) {

        if (!_korisnikPersonalniPodaciRepository.existsById(id)) {
            throw new NePostojiResursException("KorisnikPersonalniPodaci with id " + id + " doesn't exist.");
        }

        _korisnikPersonalniPodaciRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}