package com.pmagaldi.MTGCardBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pmagaldi.MTGCardBoard.entities.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{

    final String QUERY_NAME = "SELECT * FROM tb_card WHERE tb_card.name = :name";

    @Query(nativeQuery = true, value = QUERY_NAME)
    Card findByName(String name);
}
