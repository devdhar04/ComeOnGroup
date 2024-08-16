package com.comeon.test.comeon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comeon.test.comeon.entity.Game;
import com.comeon.test.comeon.entity.GameLove;

@Repository
public interface GameLoveRepository extends JpaRepository<GameLove, Long> {

	@Query("SELECT gl.game.id AS gameId, COUNT(gl.game.id) AS loveCount " + "FROM GameLove gl " + "GROUP BY gl.game.id "
			+ "ORDER BY loveCount DESC " + "LIMIT :limit")
	List<Object[]> findMostLovedGames(@Param("limit") int limit);

	@Query("DELETE FROM GameLove gl WHERE gl.player.id = :playerId AND gl.game.id = :gameId")
	void deleteByPlayerIdAndGameId(@Param("playerId") Long playerId, @Param("gameId") Long gameId);

	@Query("SELECT g FROM Game g JOIN GameLove gl ON g.id = gl.game.id WHERE gl.player.id = :playerId")
	List<Game> findAllGamesLovedByPlayer(@Param("playerId") Long playerId);

}
