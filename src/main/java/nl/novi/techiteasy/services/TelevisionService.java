package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.TelevisionDto;

import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;

    // Repository Constructor ipv AutoWired /////////
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }


    // METHODE OM TELEVISION -> DTO OM TE ZETTEN - IS STEEDS OPNIEUW TE GEBRUIKEN
    public TelevisionDto transferTelevisionToDto(Television television) {
        TelevisionDto televisionDto = new TelevisionDto();
        // de nieuwe dto vullen met de waardes uit Television
        televisionDto.id = television.getId();
        televisionDto.name = television.getName();
        televisionDto.price = television.getPrice();
        return televisionDto;
    }


    // GET METHODE ///
    public List<TelevisionDto> getAllTelevisions() {
        //Lijst van televisies
        List<Television> alltelevisions = televisionRepository.findAll();
        //Lijst van television dto's
        List<TelevisionDto> televisionDtosLijst = new ArrayList<>();

        // Forloop om alle waardes in de nieuwe dto te loopen - vanuit de lijst van alltelevision (Television)
        for (Television television : alltelevisions) {
            // De waardes uit de Dto's worden toegevoegd aan de televisionDtosLijst
            televisionDtosLijst.add(transferTelevisionToDto(television));
        }
        return televisionDtosLijst;
    }


    public String createTv(TelevisionDto televisionDto) {
        //2. teacher object wordt aangemaakt;
        Television television = new Television();
        //3. de velden worden allemaal uit het DTO object gehaald;
        television.setId(televisionDto.id);
        television.setName(televisionDto.name);
        television.setPrice(televisionDto.price);
        //4. het object teacher met de gevulde velden wordt opgeslagen in de database;
        televisionRepository.save(television);

        //5. de info van de net aangemaakte teacher wordt teruggegeven
        String televisionInfo = television.getId() + ", " + television.getName() + ", " + television.getPrice();
        return televisionInfo;

    }




}
