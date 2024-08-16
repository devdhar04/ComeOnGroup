package com.comeon.test.comeon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comeon.test.comeon.entity.Game;
import com.comeon.test.comeon.entity.GameLove;
import com.comeon.test.comeon.entity.Player;
import com.comeon.test.comeon.exceptions.GameNotFoundException;
import com.comeon.test.comeon.exceptions.PlayerNotFoundException;
import com.comeon.test.comeon.model.TopGameDTO;
import com.comeon.test.comeon.repository.GameLoveRepository;
import com.comeon.test.comeon.repository.GameRepository;
import com.comeon.test.comeon.repository.PlayerRepository;

@Service
public class GameLoveServiceImpl implements GameLoveService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private GameLoveRepository gameLoveRepository;

	@Override
	public GameLove loveGame(Long playerId, Long gameId) {
		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new PlayerNotFoundException("Player not found with id : " + playerId));
		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new GameNotFoundException("Game not found with id: " + gameId));

		GameLove gameLove = new GameLove();
		gameLove.setPlayer(player);
		gameLove.setGame(game);

		return gameLoveRepository.save(gameLove);
	}

	@Override
	public void unloveGame(Long playerId, Long gameId) {
		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new PlayerNotFoundException("Player not found with id : " + playerId));
		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new GameNotFoundException("Game not found with id: " + gameId));

		gameLoveRepository.deleteByPlayerIdAndGameId(player.getId(), game.getId());
	}

	@Override
	public List<Game> findAllGamesLovedByPlayer(Long playerId) {
		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new PlayerNotFoundException("Player not found with id : " + playerId));
		return gameLoveRepository.findAllGamesLovedByPlayer(player.getId());
	}

	@Override
	public List<GameLove> getAllGames() {
		return gameLoveRepository.findAll();
	}

	@Override
	public List<TopGameDTO> getTopLovedGames(int x) {
		// Implement logic to fetch top loved games (e.g., by query or custom logic)
		List<Object[]> results = gameLoveRepository.findMostLovedGames(x);

		return results.stream().map(result -> {
			Long gameId = (Long) result[0];
			Long loveCount = (Long) result[1];
			return new TopGameDTO(gameId, loveCount);
		}).collect(Collectors.toList());
	}

}
