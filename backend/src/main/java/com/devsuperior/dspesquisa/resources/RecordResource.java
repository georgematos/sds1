package com.devsuperior.dspesquisa.resources;

import java.time.Instant;

import com.devsuperior.dspesquisa.dtos.RecordDTO;
import com.devsuperior.dspesquisa.dtos.RecordInsertDTO;
import com.devsuperior.dspesquisa.services.RecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/records")
public class RecordResource {

  @Autowired
  private RecordService recordService;

  @PostMapping
  public ResponseEntity<RecordDTO> insert(@RequestBody RecordInsertDTO dto) {
    RecordDTO newDTO = recordService.insert(dto);
    return ResponseEntity.ok().body(newDTO);
  }

  @GetMapping
  public ResponseEntity<Page<RecordDTO>> findAll(
    @RequestParam(value = "min", defaultValue = "") String min,
    @RequestParam(value = "max", defaultValue = "") String max,
    @RequestParam(value = "page", defaultValue = "0") Integer page,
    @RequestParam(value = "linesPerPage", defaultValue = "0") Integer linesPerPage,
    @RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
    @RequestParam(value = "direction", defaultValue = "DESC") String direction
  ) {
    
    Instant minDate = "".equals(min) ? null : Instant.parse(min); // se a string não vier vazia faz o parse para Instant
    Instant maxDate = "".equals(max) ? null : Instant.parse(max);

    if (linesPerPage == 0) linesPerPage = Integer.MAX_VALUE;

    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

    var list = recordService.findByMoments(minDate, maxDate, pageRequest);
    return ResponseEntity.ok().body(list);
  }
}
