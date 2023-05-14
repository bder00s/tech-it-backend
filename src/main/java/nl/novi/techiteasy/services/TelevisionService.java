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


    public Long createTv(TelevisionDto televisionDto) {
        Television television = new Television();
//        televisionRepository.save(transferTelevisionToDto(televisionDto));
        television.setId(televisionDto.id);
        television.setName(televisionDto.name);
        television.setPrice(televisionDto.price);
        televisionRepository.save(television);
        return television.getId();

    }

 /*   public ResponseEntity<TelevisionDto> FindTelevision(Long id){
       List<Television> listOfTelevisions = televisionRepository.findAll();
       Television televisionFound = listOfTelevisions.getTelevision

    }*/


}
