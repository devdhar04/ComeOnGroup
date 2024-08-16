package com.comeon.test.comeon.service;

import java.util.List;

import com.comeon.test.comeon.entity.Game;
import com.comeon.test.comeon.entity.GameLove;
import com.comeon.test.comeon.model.TopGameDTO;

public interface GameLoveService {

	GameLove loveGame(Long playerId, Long gameId);

	void unloveGame(Long playerId, Long gameId);

	List<Game> findAllGamesLovedByPlayer(Long playerId);

	List<GameLove> getAllGames();

	List<TopGameDTO> getTopLovedGames(int x);
}
