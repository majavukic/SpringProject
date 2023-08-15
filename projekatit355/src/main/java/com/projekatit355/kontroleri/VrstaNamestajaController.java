package com.projekatit355.kontroleri;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekatit355.entiteti.VrstaNamestaja;
import com.projekatit355.helper.NePostojiResursException;
import com.projekatit355.repozitorijum.VrstaNamestajaRepository;

@RestController
@RequestMapping("/projekatit355")
@CrossOrigin("http://localhost:4200")
public class VrstaNamestajaController {

    @Autowired
    private VrstaNamestajaRepository _vrstaNamestajaRepository;

    @GetMapping("/vrstaNamestaja")
    public ResponseEntity<List<VrstaNamestaja>> getAllVrstaNamestaja() {

        List<VrstaNamestaja> vrstaNamestaja = new ArrayList<>();

        this._vrstaNamestajaRepository.findAll().forEach(vrstaNamestaja::add);

        if (vrstaNamestaja.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(vrstaNamestaja, HttpStatus.OK);
    }

    @GetMapping("/vrstaNamestaja/{id}")
    public ResponseEntity<VrstaNamestaja> getVrstaNamestajaById(@PathVariable int id) {

        VrstaNamestaja vrstaNamestaja = _vrstaNamestajaRepository
                .findById(id)
                .orElseThrow(() -> new NePostojiResursException("VrstaNamestaja with id " + id + " doesn't exist."));

        return new ResponseEntity<>(vrstaNamestaja, HttpStatus.OK);
    }

    @PostMapping("/vrstaNamestaja")
    public ResponseEntity<VrstaNamestaja> createVrstaNamestaja(@RequestBody VrstaNamestaja vrstaNamestaja) {

        return new ResponseEntity<>(_vrstaNamestajaRepository.save(vrstaNamestaja), HttpStatus.CREATED);
    }

    @PutMapping("/vrstaNamestaja/{id}")
    public ResponseEntity<VrstaNamestaja> updateVrstaNamestaja(@PathVariable int id,
            @RequestBody VrstaNamestaja vrstaNamestaja) {

        VrstaNamestaja _vrstaNamestaja = _vrstaNamestajaRepository
                .findById(id)
                .orElseThrow(() -> new NePostojiResursException("VrstaNamestaja with id " + id + " doesn't exist."));

        if (vrstaNamestaja.getNaziv() != null)
            _vrstaNamestaja.setNaziv(vrstaNamestaja.getNaziv());

        return new ResponseEntity<>(_vrstaNamestajaRepository.save(_vrstaNamestaja), HttpStatus.OK);
    }

    @DeleteMapping("/vrstaNamestaja/{id}")
    public ResponseEntity<HttpStatus> deleteVrstaNamestaja(@PathVariable int id) {

        if (!_vrstaNamestajaRepository.existsById(id)) {
            throw new NePostojiResursException("VrstaNamestaja with id " + id + " doesn't exist.");
        }

        _vrstaNamestajaRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
