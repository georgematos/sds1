package com.devsuperior.dspesquisa.services;

import java.time.Instant;

import com.devsuperior.dspesquisa.dtos.RecordDTO;
import com.devsuperior.dspesquisa.dtos.RecordInsertDTO;
import com.devsuperior.dspesquisa.repositories.GameRepository;
import com.devsuperior.dspesquisa.repositories.RecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.Record;

@Service
public class RecordService {
  
  @Autowired
  private RecordRepository repository;

  @Autowired
  private GameRepository gameRepository;

  @Transactional
  public RecordDTO insert(RecordInsertDTO dto) {

    Record recordEntity = new Record();

    recordEntity.setName(dto.getName());
    recordEntity.setAge(dto.getAge());
    recordEntity.setMoment(Instant.now());

    Game game = gameRepository.getOne(dto.getGameId());
    recordEntity.setGame(game);

    recordEntity = repository.save(recordEntity);

    return new RecordDTO(recordEntity);
  }

}
