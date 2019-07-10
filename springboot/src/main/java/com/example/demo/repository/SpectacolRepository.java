package com.example.demo.repository;

import com.example.demo.domain.Spectacol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpectacolRepository extends JpaRepository<Spectacol, Long> {
}
