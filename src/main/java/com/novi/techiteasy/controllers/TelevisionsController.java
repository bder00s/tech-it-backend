package com.novi.techiteasy.controllers;

import com.novi.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController

public class TelevisionsController {

    @GetMapping("/televisions")
// Return die naar front-end gaat -> omzetten in JSON
    public ResponseEntity<Object> getAllTelevisions() {
        // Ok code 200 -> het is gelukt
        return ResponseEntity.ok("All televisions");
    }

    @GetMapping("/television/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable long id) {
        if (id != 4 && id >=1 && id <= 100) {
            return ResponseEntity.ok("Television " + id);
        } else if (id != 4 && id > 100){
            throw new IndexOutOfBoundsException("Deze id is te hoog - id's gaan maar tot 100");
        } else {
            throw new RecordNotFoundException("Deze id is niet beschikbaar");
        }
    }

    // if ID herkend wordt: return Televisie + id - else (niet herkend) { throw new RecordNotFoundException();
    //OF:
    //if id is te hoog/te laag { throw new RecordNotFoundException("Foutmelding hier");


    //throw new Index Out Of Bounds Exception("Foutmelding hier")


    @PostMapping("/addtelevision")
    public ResponseEntity<Object> addTelevision(@RequestBody String television)  //normaal is het type de Class waar het over gaat, bijv Television
    {
        return ResponseEntity.created(null).body(television);
    }


    @PutMapping("/updatetelevision/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable long id, @RequestBody String television) {
        return ResponseEntity.accepted().body("Television " + television + " id number is " + id);
    }


    @DeleteMapping("/deletetelevision/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable long id) {

        return ResponseEntity.noContent().build();
    }


}
