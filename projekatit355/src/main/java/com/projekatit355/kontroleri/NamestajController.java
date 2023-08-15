package com.projekatit355.kontroleri;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekatit355.entiteti.Namestaj;
import com.projekatit355.entiteti.VrstaNamestaja;
import com.projekatit355.helper.NePostojiResursException;
import com.projekatit355.repozitorijum.NamestajRepository;
import com.projekatit355.repozitorijum.VrstaNamestajaRepository;

@RestController
@RequestMapping("/projekatit355")
@CrossOrigin("http://localhost:4200")
public class NamestajController {

    @Autowired
    private NamestajRepository _namestajRepository;

    @Autowired
    private VrstaNamestajaRepository _vrstaNamestajaRepository;

    @GetMapping("/namestaji")
    public ResponseEntity<List<Namestaj>> getAllNamestaji() {

        List<Namestaj> namestaji = new ArrayList<Namestaj>();

        this._namestajRepository.findAll().forEach(namestaji::add);

        if (namestaji.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(namestaji, HttpStatus.OK);
    }

    @GetMapping("/namestaji/{id}")
    public ResponseEntity<Namestaj> getNamestajById(@PathVariable("id") int id) {

        Namestaj namestaj = _namestajRepository.findById(id)
                .orElseThrow(() -> new NePostojiResursException("Not found Namestaj with id = " + id));

        return new ResponseEntity<>(namestaj, HttpStatus.OK);
    }

    @PutMapping("/namestaji/{id}")
    public ResponseEntity<Namestaj> updateNamestaj(@PathVariable("id") int id, @RequestBody Namestaj namestaj) {

        Namestaj _namestaj = _namestajRepository.findById(id)
                .orElseThrow(() -> new NePostojiResursException("Not found Namestaj with id = " + id));

        if (namestaj.getCena() != 0)
            _namestaj.setCena(namestaj.getCena());

        if (namestaj.getSlika() != null)
            _namestaj.setSlika(namestaj.getSlika());

        if (namestaj.getBoja() != null)
            _namestaj.setBoja(namestaj.getBoja());

        if (namestaj.getNaziv() != null)
            _namestaj.setNaziv(namestaj.getNaziv());

        return new ResponseEntity<>(_namestajRepository.save(_namestaj), HttpStatus.OK);
    }

    @PostMapping("/vrstaNamestaja/{vrstaNamestajaId}/namestaji")
    public ResponseEntity<Namestaj> createNamestaj(@PathVariable int vrstaNamestajaId,
            @RequestBody Namestaj theNamestaj) {

        VrstaNamestaja tempVrstaNamestaja = _vrstaNamestajaRepository
                .findById(vrstaNamestajaId)
                .orElseThrow(() -> new NePostojiResursException(
                        "VrstaNamestaja with id " + vrstaNamestajaId + " doesn't exist."));

        theNamestaj.setVrstaNamestaja(tempVrstaNamestaja);

        _namestajRepository.save(theNamestaj);

        return new ResponseEntity<>(theNamestaj, HttpStatus.CREATED);
    }

    @DeleteMapping("/namestaji/{id}")
    public ResponseEntity<HttpStatus> deleteNamestaj(@PathVariable int id) {

        _namestajRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
