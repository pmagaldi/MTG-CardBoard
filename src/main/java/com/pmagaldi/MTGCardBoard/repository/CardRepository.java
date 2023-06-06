package com.pmagaldi.MTGCardBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmagaldi.MTGCardBoard.entities.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{
    
}
