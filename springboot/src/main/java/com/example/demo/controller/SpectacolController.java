package com.example.demo.controller;

import com.example.demo.domain.Spectacol;
import com.example.demo.domain.SpectacolData;
import com.example.demo.domain.Spectator;
import com.example.demo.dto.SpectacolDataDto;
import com.example.demo.dto.SpectacolDto;
import com.example.demo.service.SpectacolService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RequestMapping("/spec")
@RestController
public class SpectacolController {

    private SpectacolService spectacolService;

    @Autowired
    public SpectacolController(SpectacolService spectacolService) {
        this.spectacolService = spectacolService;
    }

    // ~~~~~~~~~~~~~~~~~~~~~     SPECTACOL      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @GetMapping("/spectacol")
    public ResponseEntity<List<SpectacolDto>> getSpectacole() {
        List<Spectacol> spectacols = spectacolService.getAllSpectacole();
        List<SpectacolDto> spectacolDtos = new ArrayList<>();

        for (Spectacol spectacol : spectacols) {
            spectacolDtos.add(spectacolDtoConverter(spectacol));
        }
        return new ResponseEntity<>(spectacolDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/spectacol/{id}")
    public ResponseEntity<SpectacolDto> getSpectacolById(@PathVariable Long id){
        Spectacol spectacol = spectacolService.getSpectacol(id);

        return new ResponseEntity<>(spectacolDtoConverter(spectacol), HttpStatus.OK);
    }

    @PostMapping(value = "/spectacol")
    public ResponseEntity<SpectacolDto> addSpectacol(@RequestBody ObjectNode objectNode){

        String denumire = objectNode.get("denumire").asText();
        String descriere = objectNode.get("descriere").asText();

        Spectacol spectacol1 = spectacolService.addSpectacol(Spectacol.builder()
                .denumire(denumire)
                .descriere(descriere)
                .build());

        SpectacolDto spectacolDto = spectacolDtoConverter(spectacol1);
        return new ResponseEntity<>(spectacolDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/spectacol/{id}")
    public ResponseEntity<String> deleteSpectacolById(@PathVariable Long id){
        spectacolService.deleteSpectacol(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping(value = "/spectacol")
    public ResponseEntity<SpectacolDto> updateSpectacol(@RequestBody ObjectNode objectNode){

        Long id = Long.valueOf(objectNode.get("id").asText());
        String denumire = objectNode.get("denumire").asText();
        String description = objectNode.get("descriere").asText();

        Spectacol spectacol = Spectacol.builder()
                .denumire(denumire)
                .descriere(description)
                .build();
        spectacol.setId(id);

        Spectacol spectacol1 = spectacolService.updateSpectacol(spectacol);

        return new ResponseEntity<>(spectacolDtoConverter(spectacol1), HttpStatus.OK);
    }


    // ~~~~~~~~~~~~~~~~~~~~~     SPECTACOLDATA     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @GetMapping("/spectacolData")
    public ResponseEntity<List<SpectacolDataDto>> getSpectacoleData() {
        List<SpectacolData> spectacols = spectacolService.getAllSpectacoleData();
        List<SpectacolDataDto> spectacolDataDtos = new ArrayList<>();

        for (SpectacolData spectacolData : spectacols) {
            spectacolDataDtos.add(spectacolDataDtoConverter(spectacolData));
        }
        return new ResponseEntity<>(spectacolDataDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/spectacolData/{id}")
    public ResponseEntity<SpectacolDataDto> getSpectacolDataById(@PathVariable Long id){
        SpectacolData spectacolData = spectacolService.getSpectacolData(id);

        return new ResponseEntity<>(spectacolDataDtoConverter(spectacolData), HttpStatus.OK);
    }

    @GetMapping(value = "/spectacolDataLast")
    public ResponseEntity<Long> getLastSpectacolData(){
        Set<SpectacolData> list = spectacolService.getLastSpectacolData();
        Long timp = 0L;

        if(list.size() == 0){
            return new ResponseEntity<>(timp, HttpStatus.OK);
        }

        timp = spectacolService.getLastSpectacolData().iterator().next().getData();
        return new ResponseEntity<>(timp, HttpStatus.OK);
    }

    @GetMapping(value = "/spectacolDataLastAll")
    public ResponseEntity<SpectacolDataDto> getLastSpectacolDataAll(){
        Set<SpectacolData> spectacolDataList = spectacolService.getLastSpectacolData();

        if(spectacolDataList.size() == 0){
            return new ResponseEntity<SpectacolDataDto>(new SpectacolDataDto(), HttpStatus.NOT_FOUND);
        }

//        SpectacolData spectacolData = spectacolService.getLastSpectacolData().iterator().next();
        SpectacolData spectacolData = spectacolDataList.iterator().next();
        return new ResponseEntity<SpectacolDataDto>(spectacolDataDtoConverter(spectacolData), HttpStatus.OK);
    }

    @PostMapping(value = "/spectacolData")
    public ResponseEntity<SpectacolDataDto> addSpectacolData(@RequestBody ObjectNode objectNode){

        Long idSpectacol = Long.valueOf(objectNode.get("idSpectacol").asText());
        Float pret = Float.valueOf(objectNode.get("pret").asText());
        Long data = Long.valueOf(objectNode.get("data").asText());
        Spectacol spectacol = spectacolService.getSpectacol(idSpectacol);

        SpectacolData spectacolData1 = spectacolService.addSpectacolData(SpectacolData.builder()
                .pret(pret)
                .data(data)
                .spectacolMapat(spectacol)
                .build());

        return new ResponseEntity<>(spectacolDataDtoConverter(spectacolData1), HttpStatus.OK);
    }

    @DeleteMapping(value = "/spectacolData/{id}")
    public ResponseEntity<String> deleteSpectacolDataById(@PathVariable Long id){
        spectacolService.deleteSpectacolData(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping(value = "/spectacolData")
    public ResponseEntity<SpectacolDataDto> updateSpectacolData(@RequestBody ObjectNode objectNode){

        Long id = Long.valueOf(objectNode.get("id").asText());
//        Long idSpectacol = Long.valueOf(objectNode.get("idSpectacol").asText());
        Float pret = Float.valueOf(objectNode.get("pret").asText());
        Long data = Long.valueOf(objectNode.get("data").asText());

//        Spectacol spectacol = spectacolService.getSpectacol(idSpectacol);

        SpectacolData spectacolData = SpectacolData.builder()
                .pret(pret)
                .data(data)
//                .spectacolMapat(spectacol)
                .build();
        spectacolData.setId(id);

        spectacolData = spectacolService.updateSpectacolData(spectacolData);

        return new ResponseEntity<>(spectacolDataDtoConverter(spectacolData), HttpStatus.OK);
    }

    // ~~~~~~~~~~~~~~~~~~~~~     CONVERTERS     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private SpectacolDto spectacolDtoConverter(Spectacol spectacol){
        return SpectacolDto.builder()
                .id(spectacol.getId())
                .denumire(spectacol.getDenumire())
                .descriere(spectacol.getDescriere())
                .build();
    }

    private SpectacolDataDto spectacolDataDtoConverter(SpectacolData spectacolData){
        return SpectacolDataDto.builder()
                .id(spectacolData.getId())
                .pret(spectacolData.getPret())
                .data(spectacolData.getData())
                .spectacolId(spectacolData.getSpectacolMapat().getId())
                .build();
    }

}
