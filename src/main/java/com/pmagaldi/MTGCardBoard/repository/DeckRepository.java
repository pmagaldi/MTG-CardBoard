package com.pmagaldi.MTGCardBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmagaldi.MTGCardBoard.entities.Deck;

public interface DeckRepository extends JpaRepository<Deck, Integer>{
    
}
