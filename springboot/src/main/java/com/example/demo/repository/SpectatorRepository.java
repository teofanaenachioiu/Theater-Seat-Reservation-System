package com.example.demo.repository;

import com.example.demo.domain.Spectator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpectatorRepository extends JpaRepository<Spectator, String> {
}
