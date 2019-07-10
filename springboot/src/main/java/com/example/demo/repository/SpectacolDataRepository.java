package com.example.demo.repository;

import com.example.demo.domain.SpectacolData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Set;

public interface SpectacolDataRepository extends JpaRepository<SpectacolData, Long> {

    @Query("select s from SpectacolData s where s.data = :date and s.spectacolMapat.id = :id")
    SpectacolData getSpectacolDataByDate(@Param("date") Long date, @Param("id") Long id);

    @Query("select s from SpectacolData s order by s.data desc")
    Set<SpectacolData> getLastSpectacolData();
}
