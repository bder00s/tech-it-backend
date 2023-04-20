package com.novi.techiteasy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
        return ResponseEntity.ok("Television " + id);
    }

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
