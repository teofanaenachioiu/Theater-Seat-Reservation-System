package com.example.demo.controller;

import com.example.demo.domain.Rezervare;
import com.example.demo.domain.Spectacol;
import com.example.demo.domain.SpectacolData;
import com.example.demo.domain.Spectator;
import com.example.demo.dto.RezervareDto;
import com.example.demo.dto.SpectacolDto;
import com.example.demo.service.AuthService;
import com.example.demo.service.RezervareService;
import com.example.demo.service.SpectacolService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RequestMapping("/rez")
@RestController
@CrossOrigin
public class RezervareController {
    private RezervareService rezervareService;
    private SpectacolService spectacolService;
    private AuthService authService;

    //id = id-ul spectacolData, pentru fiecare spectacolData o sa avem o lista de emitatori connectati la clientii care
    //au deschis pagina unde este spectacolData-ul respectiv
    private final Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    @Autowired
    public RezervareController(RezervareService rezervareService, SpectacolService spectacolService, AuthService authService) {
        this.rezervareService = rezervareService;
        this.spectacolService = spectacolService;
        this.authService = authService;
    }

    // ~~~~~~~~~~~~~~~~~~~~~     REZERVARI     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @PostMapping(value = "/rezervareByDate")
    public ResponseEntity<List<RezervareDto>> getRezervariByDate(@RequestBody ObjectNode objectNode){

        Long data =  Long.valueOf(objectNode.get("data").asText());
        Long idSpectacol = Long.valueOf(objectNode.get("idSpectacol").asText());

        Set<Rezervare> rezervari = rezervareService.getReservationsByDateAndSpecId(data, idSpectacol);
        List<RezervareDto> rezervareDtos = new ArrayList<>();

        for (Rezervare rezervare : rezervari) {
            rezervareDtos.add(rezervareDtoConverter(rezervare));
        }

        return new ResponseEntity<>(rezervareDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/rezervare/{id}")
    public ResponseEntity<List<RezervareDto>> getRezervareBySpectatorId(@PathVariable String id){
        Set<Rezervare> rezervari = rezervareService.getReservationBySpectatorId(id);
        List<RezervareDto> rezervareDtos = new ArrayList<>();
        List<Rezervare> lista = new ArrayList<>(rezervari);
        lista.sort((x,y)->{
            if(x.getSpectacolDataMapat().getData() > y.getSpectacolDataMapat().getData()){
                return -1;
            }
            else if(x.getSpectacolDataMapat().getData() < y.getSpectacolDataMapat().getData()){
                return 1;
            }
            else{
                return 0;
            }
        });
        for (Rezervare rezervare : lista) {
            rezervareDtos.add(rezervareDtoConverter(rezervare));
        }

        return new ResponseEntity<>(rezervareDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/rezervare")
    public ResponseEntity<RezervareDto> addRezervare(@RequestBody ObjectNode objectNode)  {

        Integer pozitieX =  Integer.valueOf(objectNode.get("pozitieX").asText());
        Integer pozitieY =  Integer.valueOf(objectNode.get("pozitieY").asText());
        Integer nrScaun =  Integer.valueOf(objectNode.get("nrScaun").asText());
        Long idSpectacolData =  Long.valueOf(objectNode.get("idSpectacolData").asText());
        String idSpectator = objectNode.get("idSpectator").asText();

        SpectacolData spectacolData = spectacolService.getSpectacolData(idSpectacolData);
        Spectator spectator = authService.getSpectatorById(idSpectator);

        Rezervare rezervare = rezervareService.addRezervare(Rezervare.builder()
                .pozitieX(pozitieX)
                .pozitieY(pozitieY)
                .nrScaun(nrScaun)
                .spectacolDataMapat(spectacolData)
                .spectatorMapat(spectator)
                .build());
        if(rezervare == null){
            return new ResponseEntity<>(new RezervareDto(), HttpStatus.BAD_REQUEST);
        }
        try {
            ////// trimitem la fiecare SseEmitter care apartine SpectacolData-ului cu id-ul dat, mesajul de rezervare noua
            if (sseEmitters.containsKey(idSpectator)) {
                try {
                    for (SseEmitter sseEmitter : this.sseEmitters.values()) {
                        sseEmitter.send(rezervareDtoConverter(rezervare), MediaType.APPLICATION_JSON);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


        return new ResponseEntity<>(rezervareDtoConverter(rezervare), HttpStatus.OK);
    }

    @DeleteMapping(value = "/rezervare/{id}")
    public ResponseEntity<String> deleteRezervareById(@PathVariable Long id){
        Rezervare rezervare = rezervareService.deleteReservareById(id);
        ////// trimitem la fiecare SseEmitter care apartine SpectacolData-ului cu id-ul dat, mesajul de rezervare noua
        try {
            for (SseEmitter sseEmitter : this.sseEmitters.values()) {
                sseEmitter.send(rezervareDtoConverter(rezervare), MediaType.APPLICATION_JSON);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    // ~~~~~~~~~~~~~~~~~~~~~     SSE     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /*Dam subscribe la un SseEmitter pentru SpectacolData-ul cu id-ul precizat
    * dupa ce am dat subscribe, mergem in functia addRezervare si o sa trimitem un mesaj cu ajutorul SseEmitterului
    * atunci cand se face o rezervare pe SpectacolData-ul nostru
    */
    @GetMapping(value = "/subscribe/{id}")
    public SseEmitter subscribe(@PathVariable String id) {

        System.out.println("l-am bagat pe " + id);
        SseEmitter sseEmitter = new SseEmitter(60 * 15000L); // timeout dupa 10minute
        sseEmitters.put(id, sseEmitter);

        sseEmitter.onCompletion(()->{
            System.out.println("l-am scros pe " + id);
            sseEmitters.remove(id);
        });
        sseEmitter.onTimeout(()->{
            sseEmitters.remove(id);
        });
        sseEmitter.onError((x)->{
            sseEmitters.remove(id);
        });

        return sseEmitter;
    }

    @GetMapping(value = "/unsubscribe/{id}")
    public void unsubscribe(@PathVariable String id) {
        this.sseEmitters.get(id).complete();
    }


    // ~~~~~~~~~~~~~~~~~~~~~     CONVERTERS     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private RezervareDto rezervareDtoConverter(Rezervare rezervare){
        return RezervareDto.builder()
                .id(rezervare.getId())
                .pozitieX(rezervare.getPozitieX())
                .pozitieY(rezervare.getPozitieY())
                .nrScaun(rezervare.getNrScaun())
                .pret(rezervare.getSpectacolDataMapat().getPret())
                .data(rezervare.getSpectacolDataMapat().getData())
                .denumire(rezervare.getSpectacolDataMapat().getSpectacolMapat().getDenumire())
                .descriere(rezervare.getSpectacolDataMapat().getSpectacolMapat().getDescriere())
                .build();
    }
}
