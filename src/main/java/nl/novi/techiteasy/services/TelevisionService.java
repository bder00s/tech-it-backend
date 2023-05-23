package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.TelevisionDto;

import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Remotecontroller;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repositories.RemotecontrollerRepository;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import nl.novi.techiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final RemotecontrollerRepository remotecontrollerRepository;

    private WallBracketRepository wallBracketRepository;

    // Repository Constructor ipv AutoWired /////////


    public TelevisionService(TelevisionRepository televisionRepository, RemotecontrollerRepository remotecontrollerRepository, WallBracketRepository wallBracketRepository) {
        this.televisionRepository = televisionRepository;
        this.remotecontrollerRepository = remotecontrollerRepository;
        this.wallBracketRepository = wallBracketRepository;
    }

    // GET METHODE /// ALLE TELEVISIES
    public List<TelevisionDto> getAllTelevisions() {
        //Lijst van televisies
        List<Television> alltelevisions = televisionRepository.findAll();
        //Lijst van television dto's
        List<TelevisionDto> televisionDtosLijst = new ArrayList<>();

        // Forloop om alle waardes in de nieuwe dto te loopen - vanuit de lijst van alltelevision (Television)
        for (Television television : alltelevisions) {
            // De waardes uit de Dto's worden toegevoegd aan de televisionDtosLijst
            televisionDtosLijst.add(outputTransferTelevisionToDto(television));
        }
        return televisionDtosLijst;
    }


    // GET METHODE /// 1 TELEVISIE
    public TelevisionDto getTelevision(@RequestParam Long id) {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Id/Televisie niet gevonden"));
        TelevisionDto televisionDto = new TelevisionDto();
        outputTransferTelevisionToDto(television);
        return televisionDto;
    }

    // POST METHODE ///
    public String createTv(TelevisionDto televisionDto) {
        //2. teacher object wordt aangemaakt;
        Television television = new Television();
        //3. de velden worden allemaal uit het DTO object gehaald;

//        television.setId(televisionDto.id);
//        television.setName(televisionDto.name);
//        television.setPrice(televisionDto.price);

        //4. het object teacher met de gevulde velden wordt opgeslagen in de database;
        televisionRepository.save(inputTransferDTOtoTelevision(televisionDto));

        //5. de info van de net aangemaakte teacher wordt teruggegeven
        return television.getId() + ", " + television.getName() + ", " + television.getPrice();


    }


    // KOPPEL REMOTE AAN TELEVISION METHODE
    public TelevisionDto assignRemoteControllerToTelevision(Long id, Long remotecontroller_id) throws RecordNotFoundException {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<Remotecontroller> optionalRemotecontroller = remotecontrollerRepository.findById(id);
        if (optionalTelevision.isEmpty() && optionalRemotecontroller.isEmpty()) {
            //Wanneer er geen match gevonden is/ de id's niet bestaan
            throw new RecordNotFoundException("Remote or television with" + remotecontroller_id + " or " + id + " does not exist");
        } else {
            Television television = optionalTelevision.get();
            Remotecontroller remotecontroller = optionalRemotecontroller.get();

            television.setRemotecontroller(remotecontroller);
            Television updatedTelevision = televisionRepository.save(television);
            return outputTransferTelevisionToDto(updatedTelevision);
        // HET RETURN TYPE VAN DEZE METHODE IS EEN TELEVISIONDTO : IN POSTMAN ZAL DIT VERZOEK DE COMPLETE TV INFO + REMOTECONTROLLER INFO WEERGEVEN
        }

    }


    //KOPPEL WALLBRACKET AAN TELEVISION
    public String assignWallbracketToTelevision(Long id, Long wall_brackets_id) throws RecordNotFoundException {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional <WallBracket> optionalWallBracket = wallBracketRepository.findById(wall_brackets_id);
        if(optionalTelevision.isEmpty() && optionalWallBracket.isEmpty()) {
            throw new RecordNotFoundException("Wallbracket or Television with id " + wall_brackets_id + " or " + id + " does not exist");
        } else {
            Television television = optionalTelevision.get();
            WallBracket wallBracket = optionalWallBracket.get();
        List<WallBracket> wallBracketList = television.getWallbrackets();
            wallBracketList.add(wallBracket);
            television.setWallbrackets(wallBracketList);
            televisionRepository.save(television);
            return "Wallbracket id " + wallBracket.getId() + " " + wallBracket.getName() + " gekoppeld aan Television " + television.getId() + " " + television.getName();
        }
    }

    // METHODE OM TELEVISION -> DTO OM TE ZETTEN - IS STEEDS OPNIEUW TE GEBRUIKEN
    // OUTPUT -> VAN SERVICE/DATABASE NAAR KLANT
    public TelevisionDto outputTransferTelevisionToDto(Television television) {
        TelevisionDto televisionDto = new TelevisionDto();
        // de nieuwe dto vullen met de waardes uit Television
        televisionDto.id = television.getId();
        televisionDto.name = television.getName();
        televisionDto.price = television.getPrice();
        televisionDto.brand = television.getBrand();
        televisionDto.type = television.getType();
        televisionDto.remotecontroller = television.getRemotecontroller();
        return televisionDto;
    }

    //INPUT -> VAN KLANT NAAR SERVICE/DATABASE
    public Television inputTransferDTOtoTelevision(TelevisionDto televisionDto) {
        Television television = new Television();
        //de nieuwe televisie vullen met waardes uit TelevisionDto
        television.setId(televisionDto.id);
        television.setName(televisionDto.name);
        television.setPrice(televisionDto.price);
        television.setBrand(televisionDto.brand);
        television.setType(televisionDto.type);
        television.setRemotecontroller(televisionDto.remotecontroller);
        return television;
    }

}
