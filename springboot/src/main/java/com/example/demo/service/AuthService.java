package com.example.demo.service;

import com.example.demo.domain.Manager;
import com.example.demo.domain.Spectator;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.SpectacolDataRepository;
import com.example.demo.repository.SpectacolRepository;
import com.example.demo.repository.SpectatorRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class AuthService {

    private SpectatorRepository spectatorRepository;
    private ManagerRepository managerRepository;

    private List<Spectator> spectatoriAutentificati = new ArrayList<>();
    private List<Manager> manageriAutentificati = new ArrayList<>();

    @Autowired
    public AuthService(SpectatorRepository spectatorRepository, ManagerRepository managerRepository){
        this.spectatorRepository = spectatorRepository;
        this.managerRepository = managerRepository;

    }

    //~~~~~~~~~~~~~~~~~~  SPECTATOR  ~~~~~~~~~~~~~~~~~~~~~~~~~

    public void signUpSpectator(Spectator spectator){
        spectatorRepository.save(spectator);
    }

    public Spectator getSpectatorById(String id){
        return spectatorRepository.findById(id).get();
    }

    public void loginSpectator(Spectator spectator) throws Exception {
        Optional<Spectator> optional = spectatorRepository.findById(spectator.getNume());

        if(optional.isPresent()){
            Spectator rezSpectator = optional.get();

            if(rezSpectator.getPassword().equals(spectator.getPassword())){
                 if(spectatoriAutentificati.contains(rezSpectator))
                     throw new Exception("Spectator deja connectat!");
                 else
                     spectatoriAutentificati.add(rezSpectator);
            }
            else
                throw new Exception("Parola gresita!");
        }
        else
            throw new Exception("Username gresit!");
    }

    public void logoutSpectator(Spectator spectator){
        Spectator rezSpectator = spectatorRepository.findById(spectator.getNume()).get();
        spectatoriAutentificati.remove(rezSpectator);
    }

    //~~~~~~~~~~~~~~~~~~  MANAGER  ~~~~~~~~~~~~~~~~~~~~~~~~~

    public void loginManager(Manager manager) throws Exception {
        Optional<Manager> optional = managerRepository.findById(manager.getNume());

        if(optional.isPresent()){
            Manager rezManager = optional.get();

            if(rezManager.getPassword().equals(manager.getPassword())){
                if(manageriAutentificati.contains(rezManager))
                    throw new Exception("Manager already connected!");
                else
                    manageriAutentificati.add(rezManager);
            }
            else
                throw new Exception("Wrong password!");
        }
        else
            throw new Exception("Wrong username!");
    }

    public void logoutManager(Manager manager){
        Manager rezManager = managerRepository.findById(manager.getNume()).get();
        manageriAutentificati.remove(rezManager);
    }

}
