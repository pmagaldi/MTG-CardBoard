package com.pmagaldi.MTGCardBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmagaldi.MTGCardBoard.repository.CardRepository;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    
    
}
