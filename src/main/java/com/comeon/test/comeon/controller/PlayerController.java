package com.comeon.test.comeon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comeon.test.comeon.entity.Player;
import com.comeon.test.comeon.model.PlayerDTO;
import com.comeon.test.comeon.service.PlayerService;

@RestController
@RequestMapping("/v1/api/player")
public class PlayerController {

	@Autowired
	PlayerService playerService;

	@PostMapping("/addPlayer")
	public ResponseEntity<List<Player>> addGame(@RequestBody List<PlayerDTO> playerDTO) {
		return new ResponseEntity<>(playerService.addPlayer(playerDTO), HttpStatus.CREATED);
	}
}
