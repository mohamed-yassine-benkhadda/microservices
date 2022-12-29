package com.microservices.locataire;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocataireRespository extends JpaRepository<Locataire, Integer> {
}
