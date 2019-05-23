package restServices.restControllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Spectacol;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MyAppException;
import service.MyService;

@CrossOrigin
@RestController
@RequestMapping("/theater")
public class ShowsController {
    @Autowired
    private MyService showService;

    public ShowsController() {
    }

    @RequestMapping(value = "/shows", method = RequestMethod.GET)
    public ResponseEntity<Spectacol[]> getAll() {
        System.out.println("Intra");
        return new ResponseEntity<Spectacol[]>( showService.getAllShows(), HttpStatus.OK);
    }

    @RequestMapping(value = "/shows/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id){
        Spectacol show=showService.findShow(id);
        if (show==null)
            return new ResponseEntity<String>("No show", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<String>("Show updated", HttpStatus.OK);
    }

    @RequestMapping(value = "/shows", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ObjectNode objectNode) {
        try {
            System.out.println("NODE: "+ objectNode.toString());
            String name = objectNode.get("denumire").asText();
            String description = objectNode.get("descriere").asText();
            Spectacol spec = showService.addShow(new Spectacol(name,description));
            return new ResponseEntity<Spectacol>(spec, HttpStatus.OK);
        } catch (MyAppException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/shows/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        System.out.println("Se sterge show-ul ... "+id.toString());
        Spectacol show= showService.deleteShow(id);
        return new ResponseEntity<Spectacol>(show, HttpStatus.OK);

    }

    @RequestMapping(value = "/shows/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Spectacol> update(@RequestBody ObjectNode objectNode) {
        System.out.println("NODE: "+ objectNode.toString());
        String name = objectNode.get("denumire").asText();
        Integer id = Integer.valueOf(objectNode.get("id").asText());
        String description = objectNode.get("descriere").asText();
        Spectacol sp = showService.updateShow(id, new Spectacol(name,description));
        return new ResponseEntity<Spectacol>(sp, HttpStatus.OK);
    }

}
