package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.CiModuleDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.CiModule;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.CiModuleRepository;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CiModuleService {

    private CiModuleRepository ciModuleRepository;
    private TelevisionRepository televisionRepository;

    public CiModuleService(CiModuleRepository ciModuleRepository, TelevisionRepository televisionRepository) {
        this.ciModuleRepository = ciModuleRepository;
        this.televisionRepository = televisionRepository;
    }


    // GET ALLE CIMODULES
    public List<CiModuleDto> getAllCiModules() {
        //Lijst van televisies
        List<CiModule> allcimodules = ciModuleRepository.findAll();
        //Lijst van television dto's
        List<CiModuleDto> ciModuleDtoList = new ArrayList<>();

        // Forloop om alle waardes in de nieuwe dto te loopen - vanuit de lijst van alltelevision (Television)
        for (CiModule ciModule : allcimodules) {
            // De waardes uit de Dto's worden toegevoegd aan de televisionDtosLijst
            ciModuleDtoList.add(outputTransferCiModuleToDto(ciModule));
        }
        return ciModuleDtoList;
    }


    // KOPPEL REMOTE AAN TELEVISION METHODE
    public String assignTelevisionToCiModule(Long id, Long television_id) throws RecordNotFoundException {
        Optional<CiModule> optionalCiModule = ciModuleRepository.findById(id);
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalCiModule.isEmpty() && optionalTelevision.isEmpty()) {
            //Wanneer er geen match gevonden is/ de id's niet bestaan
            throw new RecordNotFoundException("Television or CiModule with id " + television_id + " or " + id + " does not exist");
        } else {
            CiModule ciModule = optionalCiModule.get();
            Television television = optionalTelevision.get();

            ciModule.setTelevision(television);
            CiModule updatedCiModule = ciModuleRepository.save(ciModule);
            outputTransferCiModuleToDto(updatedCiModule);
            return   "CI module " + ciModule.getName() + " gekoppeld aan televisie " + television_id + " " + television.getName();
            // HET RETURN TYPE VAN DEZE METHODE IS EEN STRING : IN POSTMAN ZAL DIT VERZOEK EEN STRING VAN DE GEKOPPELDE WAARDES + INFO TERUGGEVEN

        }
    }


    // OUTPUT -> VAN SERVICE/DATABASE NAAR KLANT
    public CiModuleDto outputTransferCiModuleToDto(CiModule ciModule) {
        CiModuleDto ciModuleDto = new CiModuleDto();

        ciModuleDto.id = ciModule.getId();
        ciModuleDto.name = ciModule.getName();
        ciModuleDto.price = ciModule.getPrice();
        ciModuleDto.type = ciModule.getType();
        ciModuleDto.television = ciModule.getTelevision();
        return ciModuleDto;
    }

    //INPUT -> VAN KLANT NAAR SERVICE/DATABASE
    public CiModule inputTransferDTOtoCiModule(CiModuleDto ciModuleDto) {
        CiModule ciModule = new CiModule();

        ciModule.setId(ciModuleDto.id);
        ciModule.setName(ciModuleDto.name);
        ciModule.setPrice(ciModuleDto.price);
        ciModule.setType(ciModuleDto.type);
        ciModule.setTelevision(ciModuleDto.television);
        return ciModule;
    }


}
