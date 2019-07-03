package service;

import model.Rezervare;
import model.Spectacol;
import model.TipUser;
import model.User;
import org.springframework.stereotype.Component;
import persistence.repository.RezervareRepository;
import persistence.repository.SpectacolRepository;
import persistence.repository.UserRepository;

import java.util.Set;
import java.util.TreeSet;

@Component
public class MyService {

    UserRepository repository;
    SpectacolRepository spectacolRepository;
    RezervareRepository rezervareRepository;
    private Set<User> loggedClients;

    public MyService(UserRepository repository, SpectacolRepository spectacolRepository,
                     RezervareRepository rezervareRepository) {
        this.repository=repository;
        this.spectacolRepository = spectacolRepository;
        this.rezervareRepository = rezervareRepository;
        loggedClients = new TreeSet<>();
//        this.repository.initialize();
//        this.repository.save(new User("manager", "parola",TipUser.MANAGER));
//        this.repository.close();

    }

    public User[] getAllUsers(){
        this.repository.initialize();
        User[] users =this.repository.findAll();
        this.repository.close();
        return users;
    }

    public synchronized Spectacol findShow(Integer id){
        this.spectacolRepository.initialize();
        Spectacol show =this.spectacolRepository.findOne(id);
        this.spectacolRepository.close();
        return show;
    }

    public Spectacol addShow(Spectacol spectacol) throws MyAppException{
        this.spectacolRepository.initialize();
        Integer showId =this.spectacolRepository.save(spectacol);
        this.spectacolRepository.close();
        if(showId == null) throw  new MyAppException("Show can not be added.");
        Spectacol show =  new Spectacol(spectacol.getDenumire(),spectacol.getDescriere());
        show.setID(showId);
        return show;
    }

    public Spectacol updateShow(Integer id, Spectacol spectacol){
        this.spectacolRepository.initialize();
        Spectacol show = this.spectacolRepository.update(id,spectacol);
        this.spectacolRepository.close();
        return show;
    }

    public Spectacol deleteShow(Integer integer){
        this.spectacolRepository.initialize();
        Spectacol spectacol= this.spectacolRepository.delete(integer);
        this.spectacolRepository.close();
        return spectacol;
    }

    public Spectacol[] getAllShows(){
        this.spectacolRepository.initialize();
        Spectacol[] shows =this.spectacolRepository.findAll();
        this.spectacolRepository.close();
        return shows;
    }

    public synchronized User login(String username, String password) throws MyAppException {
        repository.initialize();
        System.out.println("Intra in login");
        User user = repository.findOne(username);
        System.out.println("USER: " + user);
        repository.close();

        if (user != null) {
            if (loggedClients.contains(user))
                throw new MyAppException("User already logged in");

            String userHash = user.getHash();
            try {
                if (!PasswordStorage.verifyPassword(password, userHash))
                    throw new MyAppException("Invalid password");
                else {
                    loggedClients.add(user);
                    System.out.println("Am intrat la " + username);
                    return user;
                }
            } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException e) {
                throw new MyAppException("Authentication failed");
            }
        }
        else{
            throw new MyAppException("Invalid username.");
        }
    }

    public synchronized User signup(String username, String password) throws MyAppException {
        repository.initialize();
        User user = repository.findOne(username);
        if (user != null) {
            repository.close();
            throw new MyAppException("Existent username.");
        }
        try {
            String hash = PasswordStorage.createHash(password);
            user = new User(username, hash, TipUser.CLIENT);
            this.repository.save(user);
            return user;
        } catch (PasswordStorage.CannotPerformOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized void logout(String username) throws MyAppException {
        repository.initialize();
        User user = repository.findOne(username);
        repository.close();
        loggedClients.remove(user);
    }


    public Rezervare addRezervare(Rezervare rezervare) throws MyAppException{
        this.rezervareRepository.initialize();
        this.spectacolRepository.initialize();
        Integer rezervareId =this.rezervareRepository.save(rezervare);
        this.rezervareRepository.close();
        this.spectacolRepository.close();
        if(rezervareId == null) throw  new MyAppException("Reservation can not be added.");
        rezervare.setID(rezervareId);
        return rezervare;
    }

    public Rezervare deleteRezervare(Integer integer){
        this.rezervareRepository.initialize();
        Rezervare rezervare= this.rezervareRepository.delete(integer);
        this.rezervareRepository.close();
        return rezervare;
    }

    public Rezervare[] getAllReservations(){
        this.rezervareRepository.initialize();
        Rezervare[] rezervares =this.rezervareRepository.findAll();
        this.rezervareRepository.close();
        return rezervares;
    }

    public Rezervare[] getAllReservationsSpectacol(Spectacol spectacol){
        this.rezervareRepository.initialize();
        Rezervare[] rezervares =this.rezervareRepository.findAll(spectacol);
        this.rezervareRepository.close();
        return rezervares;
    }
}
