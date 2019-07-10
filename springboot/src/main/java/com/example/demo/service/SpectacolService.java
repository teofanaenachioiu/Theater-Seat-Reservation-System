package com.example.demo.service;

import com.example.demo.domain.Spectacol;
import com.example.demo.domain.SpectacolData;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.SpectacolDataRepository;
import com.example.demo.repository.SpectacolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SpectacolService {

    private SpectacolRepository spectacolRepository;
    private SpectacolDataRepository spectacolDataRepository;

    @Autowired
    public SpectacolService(SpectacolRepository spectacolRepository, SpectacolDataRepository spectacolDataRepository){
        this.spectacolRepository = spectacolRepository;
        this.spectacolDataRepository = spectacolDataRepository;
    }

    // ~~~~~~~~~~~~~~~~~~~~~     SPECTACOL      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public List<Spectacol> getAllSpectacole(){ return spectacolRepository.findAll(); }

    public Spectacol getSpectacol(Long id){
        return spectacolRepository.findById(id).get();
    }

    public Spectacol addSpectacol(Spectacol spectacol){
        return spectacolRepository.save(spectacol);
    }

    public void deleteSpectacol(Long id){
        spectacolRepository.deleteById(id);
    }

    @Transactional
    public Spectacol updateSpectacol(Spectacol spectacol){
        Spectacol updatedSpectacol = spectacolRepository.getOne(spectacol.getId());
        updatedSpectacol.setDenumire(spectacol.getDenumire());
        updatedSpectacol.setDescriere(spectacol.getDescriere());
        return updatedSpectacol;
    }

    // ~~~~~~~~~~~~~~~~~~~~~     SPECTACOLDATA     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public List<SpectacolData> getAllSpectacoleData(){
        return spectacolDataRepository.findAll();
    }

    public SpectacolData getSpectacolData(Long id){
        return spectacolDataRepository.findById(id).get();
    }

    public SpectacolData addSpectacolData(SpectacolData spectacolData){
        return spectacolDataRepository.save(spectacolData);
    }

    public void deleteSpectacolData(Long id){
        spectacolDataRepository.deleteById(id);
    }

    @Transactional
    public SpectacolData updateSpectacolData(SpectacolData spectacolData){
        SpectacolData updatedSpectacolData = spectacolDataRepository.getOne(spectacolData.getId());
        updatedSpectacolData.setData(spectacolData.getData());
        updatedSpectacolData.setPret(spectacolData.getPret());
        return updatedSpectacolData;
    }

    public Set<SpectacolData> getLastSpectacolData(){
        return spectacolDataRepository.getLastSpectacolData();
    }

}
