package com.devsuperior.dspesquisa.resources;

import java.util.List;

import com.devsuperior.dspesquisa.dtos.GameDTO;
import com.devsuperior.dspesquisa.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/games")
public class GameResource {

  @Autowired
  private GameService gameService;

  @GetMapping
  public ResponseEntity<List<GameDTO>> findAll() {
    var list = gameService.findAll();
    return ResponseEntity.ok().body(list);
  }

}
