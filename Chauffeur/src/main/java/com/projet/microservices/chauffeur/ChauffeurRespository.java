package com.projet.microservices.chauffeur;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ChauffeurRespository extends JpaRepository<Chauffeur, Integer>{
//    List<Chauffeur> findByTitleContainingOrContentContaining(String text, String textAgain);
}
