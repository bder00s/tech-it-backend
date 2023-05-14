package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.TelevisionDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import nl.novi.techiteasy.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController

public class TelevisionsController {


    private final TelevisionService televisionService;
    private final TelevisionRepository televisionRepository;


    // SERVICE CONSTRUCTOR INJECTION//////
    public TelevisionsController(TelevisionService televisionService, TelevisionRepository televisionRepository) {
        this.televisionService = televisionService;
        this.televisionRepository = televisionRepository;
    }



    // GET MAPPING - ALLE DATA //////

    @GetMapping("/allTelevisions")
// Return die naar front-end gaat -> omzetten in JSON
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        return ResponseEntity.ok().body(televisionService.getAllTelevisions());
    }


    // GET MAPPING - SPECIFIEK REQUEST //////

    @GetMapping("/television/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable Long id) throws IndexOutOfBoundsException {
        Optional<Television> imaginaryOptionalTV = televisionRepository.findTelevisionByName(String name);
        //Maakt een denkbeeldige optionele tv -> gaat kijken of er een bijbehorende id in de database is
        Television television;
        if (imaginaryOptionalTV.isEmpty()) {
            throw new RecordNotFoundException("Id " + id + " doesn't exist");
        } else {
            television = imaginaryOptionalTV.get();
            return ResponseEntity.ok().body(television);
        }


    /*    @GetMapping("/findtelevision")
                public ResponseEntity<TelevisionDto> findTelevision(@RequestParam String name, TelevisionDto televisionDto){
            return ResponseEntity.ok(televisionRepository.findTelevisionByName(televisionDto.name));
        }
    }*/

// if ID herkend wordt: return Televisie + id - else (niet herkend) { throw new RecordNotFoundException();
//        OF:
//        if id is te hoog/te laag { throw new RecordNotFoundException("Foutmelding hier");
// throw new Index Out Of Bounds Exception("Foutmelding hier")

@PostMapping("/addtv")
    public ResponseEntity<Long> addTv(@Valid @RequestBody TelevisionDto televisionDto BindingResult bindingResult) {
            //bindingResult test het resultaat en mogelijke errors
            if (bindingResult.hasFieldErrors()) {
                //Stringbuilder aangemaakt die de message gaat samenvoegen
                StringBuilder stringbuilder = new StringBuilder();
                // Wanneer er een fieldError is, wordt deze in een message aan de gebruiker teruggegeven
                for (FieldError fieldError : bindingResult.getFieldErrors()) {
                    stringbuilder.append(fieldError.getField() + ": ");
                    stringbuilder.append(fieldError.getDefaultMessage());
                    stringbuilder.append("\n");
                }
                return ResponseEntity.badRequest().body(stringbuilder.toString());

            } else {
                // als er geen fieldErrors zijn, gaan we door naar deze return
                // Er komt een link naar de servicelaag
                Long newId = televisionService.createTv(televisionDto);
                //Vangt Id op en gebruikt de Id om de response header te vullen
                URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + newId).toUriString());

                // Geeft een response van de nieuwe ID aan de client
                return ResponseEntity.created(uri).body(newId);
            }

}


}