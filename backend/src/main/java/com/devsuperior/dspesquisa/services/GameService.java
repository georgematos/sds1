package com.devsuperior.dspesquisa.services;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.dspesquisa.dtos.GameDTO;
import com.devsuperior.dspesquisa.repositories.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
  
  @Autowired
  private GameRepository repository;

  @Transactional(readOnly = true)
  public List<GameDTO> findAll() {
    return repository.findAll().stream().map(game -> new GameDTO(game))
      .collect(Collectors.toList());
  }

}
