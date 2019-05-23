package restServices.restControllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistence.repository.RepositoryException;
import service.MyService;
import service.MyAppException;

@CrossOrigin
@RestController
@RequestMapping("/theater/auth")
class LoginController {
    @Autowired
    private MyService loginService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public User[] getAllUsers() {
        System.out.println("Intra");
        return loginService.getAllUsers();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody ObjectNode objectNode) {
        try {
            String username = objectNode.get("username").asText();
            String password = objectNode.get("password").asText();
            System.out.println("Username: " + username);
            System.out.println("Parola: " + password);
            User user = loginService.login(username, password);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (MyAppException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<String> signup(@RequestBody ObjectNode objectNode) {
        String username = objectNode.get("username").asText();
        String password = objectNode.get("password").asText();
        String rePassword = objectNode.get("rePassword").asText();

        try {
            loginService.signup(username, password, rePassword);

            return new ResponseEntity<>( "User intregistrat.", HttpStatus.OK);
        } catch (MyAppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String probaError(RepositoryException e) {
        return e.getMessage();
    }
}
