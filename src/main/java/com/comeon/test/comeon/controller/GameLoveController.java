package com.comeon.test.comeon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comeon.test.comeon.entity.Game;
import com.comeon.test.comeon.entity.GameLove;
import com.comeon.test.comeon.model.GameLoveDTO;
import com.comeon.test.comeon.model.TopGameDTO;
import com.comeon.test.comeon.service.GameLoveService;

@RestController
@RequestMapping("/v1/api/gamelove")
public class GameLoveController {

	@Autowired
	private GameLoveService gameLoveService;

	@PostMapping("/love")
	public ResponseEntity<GameLove> loveGame(@RequestBody GameLoveDTO gameLove) {

		return new ResponseEntity<>(gameLoveService.loveGame(gameLove.getPlayerId(), gameLove.getGameId()),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/unlove")
	public ResponseEntity<Void> unloveGame(@RequestBody GameLoveDTO gameLove) {
		gameLoveService.unloveGame(gameLove.getPlayerId(), gameLove.getGameId());
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/loves/{playerId}")
	public ResponseEntity<List<Game>> getGamesLovedByPlayer(@PathVariable Long playerId) {
		return new ResponseEntity<>(gameLoveService.findAllGamesLovedByPlayer(playerId), HttpStatus.OK);
	}

	@GetMapping("/topgame/{x}")
	public List<TopGameDTO> getTopLovedGames(@PathVariable int x) {
		return gameLoveService.getTopLovedGames(x);
	}
}