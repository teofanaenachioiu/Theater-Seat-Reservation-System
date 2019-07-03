package restServices.restControllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Rezervare;
import model.Spectacol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MyAppException;
import service.MyService;

@CrossOrigin
@RestController
@RequestMapping("/theater")
public class ReservationController {
    @Autowired
    private MyService reservationService;

    public ReservationController() {
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public ResponseEntity<Rezervare[]> getAll() {
        System.out.println("Intra");
        return new ResponseEntity<Rezervare[]>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBySpectacol(@PathVariable Integer id) {
        Spectacol show = reservationService.findShow(id);
        if (show == null)
            return new ResponseEntity<String>("No show", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(reservationService.getAllReservationsSpectacol(show), HttpStatus.OK);
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ObjectNode objectNode) {
        try {
            System.out.println("NODE: " + objectNode.toString());
            Integer idSpectacol = Integer.parseInt(objectNode.get("idSpectacol").asText());
            Integer pozitieX = Integer.parseInt(objectNode.get("pozitieX").asText());
            Integer pozitieY = Integer.parseInt(objectNode.get("pozitieY").asText());
            Integer numar = Integer.parseInt(objectNode.get("numar").asText());
            float pret = (float)(Integer.parseInt(objectNode.get("numar").asText()));
            Spectacol spectacol = reservationService.findShow(idSpectacol);
            if (spectacol == null){
                return new ResponseEntity<String>("Id invalid", HttpStatus.NOT_FOUND);
            }
            Rezervare rezervare = new Rezervare(pozitieX,pozitieY,numar,pret,spectacol);
            rezervare = reservationService.addRezervare(rezervare);
            return new ResponseEntity<Rezervare>(rezervare, HttpStatus.OK);
        } catch (MyAppException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @RequestMapping(value = "/shows/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> delete(@PathVariable Integer id) {
//        System.out.println("Se sterge show-ul ... " + id.toString());
//        Spectacol show = reservationService.deleteShow(id);
//        return new ResponseEntity<Spectacol>(show, HttpStatus.OK);
//
//    }
//
//    @RequestMapping(value = "/shows/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Spectacol> update(@RequestBody ObjectNode objectNode) {
//        System.out.println("NODE: " + objectNode.toString());
//        String name = objectNode.get("denumire").asText();
//        Integer id = Integer.valueOf(objectNode.get("id").asText());
//        String description = objectNode.get("descriere").asText();
//        Spectacol sp = reservationService.updateShow(id, new Spectacol(name, description));
//        return new ResponseEntity<Spectacol>(sp, HttpStatus.OK);
//    }

}
