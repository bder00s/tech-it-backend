package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;


@RestController

public class TelevisionsController {


    private final TelevisionRepository tvrepository;

    // REPOSITORY CONSTRUCTOR//////
    public TelevisionsController(TelevisionRepository tvrepository) {
        this.tvrepository = tvrepository;
    }


    // GET MAPPING - ALLE DATA //////

    @GetMapping("/allTelevisions")
// Return die naar front-end gaat -> omzetten in JSON
    public ResponseEntity<Iterable<Television>> getAllTelevisions() {
        // Ok code 200 -> het is gelukt
        return ResponseEntity.ok(tvrepository.findAll());
    }


    // GET MAPPING - SPECIFIEK REQUEST //////

    @GetMapping("/television/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable Long id) throws IndexOutOfBoundsException {
        Optional<Television> imaginaryOptionalTV = tvrepository.findById(id);
        //Maakt een denkbeeldige optionele tv -> gaat kijken of er een bijbehorende id in de database is
        Television television;
        if (imaginaryOptionalTV.isEmpty()) {
            throw new RecordNotFoundException("Id " + id + " doesn't exist");
        } else {
            television = imaginaryOptionalTV.get();
            return ResponseEntity.ok().body(television);
        }

    }

// if ID herkend wordt: return Televisie + id - else (niet herkend) { throw new RecordNotFoundException();
//        OF:
//        if id is te hoog/te laag { throw new RecordNotFoundException("Foutmelding hier");
// throw new Index Out Of Bounds Exception("Foutmelding hier")

    // POST MAPPING /////
    @PostMapping("/addtelevision")
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) {
        tvrepository.save(television);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + television.getId()).toUriString());
        return ResponseEntity.created(uri).body(television);
    }
//
//    @PutMapping("/updatetelevision/{id}")
//    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody String television) {
//       if (tvrepository.)
//
//
//    }


// PUT MAPPING //////////

//    @PutMapping("/updatetelevision/{id}")
//    public ResponseEntity<Television> updateTelevision(@PathVariable long id, @RequestBody String television) {
//        if (tvDatabase.contains(id)) {
//            tvDatabase.set(id,);
//        }
//        return ResponseEntity.accepted().body("Television " + television + " id number is " + id);
//    }


// DELETE MAPPING //////////
//    @DeleteMapping("/deletetelevision/{id}")
//    public ResponseEntity<Object> deleteTelevision(@PathVariable long id) {
//
//        return ResponseEntity.noContent().build();
//    }


}