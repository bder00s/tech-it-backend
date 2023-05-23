package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.dtos.CiModuleDto;
import nl.novi.techiteasy.dtos.TelevisionDto;
import nl.novi.techiteasy.repositories.CiModuleRepository;
import nl.novi.techiteasy.services.CiModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CiModuleController {

    private CiModuleService ciModuleService;
    private CiModuleRepository ciModuleRepository;

    public CiModuleController(CiModuleService ciModuleService, CiModuleRepository ciModuleRepository) {
        this.ciModuleService = ciModuleService;
        this.ciModuleRepository = ciModuleRepository;
    }

    @GetMapping("/allCimodules")
// Return die naar front-end gaat -> omzetten in JSON
    public ResponseEntity<List<CiModuleDto>> getAllCiModules() {
        return ResponseEntity.ok().body(ciModuleService.getAllCiModules());
    }

    //KOPPEL TELEVISIE AAN CIMODULE

    @PutMapping("/updatecimodule/{id}/television/{television_id}")
    public ResponseEntity<String> assignTelevisionToCiModule(@PathVariable Long id, @PathVariable Long television_id){
        return ResponseEntity.ok(ciModuleService.assignTelevisionToCiModule(id, television_id));
    }
}
