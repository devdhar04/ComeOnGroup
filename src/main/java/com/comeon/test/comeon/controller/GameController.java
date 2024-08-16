package com.comeon.test.comeon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comeon.test.comeon.entity.Game;
import com.comeon.test.comeon.model.GameDTO;
import com.comeon.test.comeon.service.GameService;

@RestController
@RequestMapping("/v1/api/game")
public class GameController {

	@Autowired
	GameService gameService;

	@PostMapping("/addGame")
	public ResponseEntity<List<Game>> addGame(@RequestBody List<GameDTO> gameDTO) {
		return new ResponseEntity<>(gameService.addGame(gameDTO), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Game>> getAllGames() {
		return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
	}

}
