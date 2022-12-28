package com.microservices.mission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRespository extends JpaRepository<Mission, Integer> {

}
