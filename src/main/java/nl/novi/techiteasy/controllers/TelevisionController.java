package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.TelevisionDto;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import nl.novi.techiteasy.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController

public class TelevisionController {


    private final TelevisionService televisionService;
    private final TelevisionRepository televisionRepository;


    // SERVICE CONSTRUCTOR INJECTION//////
    public TelevisionController(TelevisionService televisionService, TelevisionRepository televisionRepository) {
        this.televisionService = televisionService;
        this.televisionRepository = televisionRepository;
    }


    // GET MAPPING - ALLE DATA //////

    @GetMapping("/allTelevisions")
// Return die naar front-end gaat -> omzetten in JSON
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        return ResponseEntity.ok().body(televisionService.getAllTelevisions());
    }

    // GET MAPPING 1 televisie ////
    @GetMapping("/findtelevision/{id}")
    public ResponseEntity<TelevisionDto> findTelevision(@PathVariable Long id){
        return ResponseEntity.ok(televisionService.getTelevision(id));
    }


    // POST MAPPING ///
    @PostMapping("/addtv")
        public ResponseEntity<Object> addTv (@Valid @RequestBody TelevisionDto televisionDto, BindingResult
        bindingResult){
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
                String televisioninfo = televisionService.createTv(televisionDto);
                //Vangt Id op en gebruikt de Id om de response header te vullen
                URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + televisioninfo).toUriString());

                // Geeft een response van de nieuwe ID aan de client
                return ResponseEntity.created(uri).body(televisioninfo);
            }

        }

        // KOPPEL REMOTE AAN TELEVISIE ///
        @PutMapping("/updatetelevision/{id}/remotecontroller/{remotecontroller_id}")
        public ResponseEntity<TelevisionDto> assignRemoteControllerToTelevision(@PathVariable Long id, @PathVariable Long remotecontroller_id){
        return ResponseEntity.ok(televisionService.assignRemoteControllerToTelevision(id, remotecontroller_id));
        }

        // KOPPEL WALLBRACKET AAN TELEVISIE
    @PutMapping("assignWallbracketToTelevision/{id}/wallbracket/{wall_brackets_id}")
    public ResponseEntity<String> assignWallbracketsToTelevision(@PathVariable Long id, @PathVariable Long wall_brackets_id){
        return ResponseEntity.ok(televisionService.assignWallbracketToTelevision(id, wall_brackets_id));
    }

    }

