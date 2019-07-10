package com.example.demo.repository;

import com.example.demo.domain.Rezervare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezervareRepository extends JpaRepository<Rezervare, Long> {
}
