package com.projekatit355.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekatit355.entiteti.Namestaj;

public interface NamestajRepository extends JpaRepository<Namestaj, Integer> {

    Namestaj findByNaziv(String naziv);
}
