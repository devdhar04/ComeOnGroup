package com.comeon.test.comeon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.comeon.test.comeon.entity.Game;
import com.comeon.test.comeon.entity.GameLove;
import com.comeon.test.comeon.entity.Player;
import com.comeon.test.comeon.model.TopGameDTO;
import com.comeon.test.comeon.repository.GameLoveRepository;
import com.comeon.test.comeon.repository.GameRepository;
import com.comeon.test.comeon.repository.PlayerRepository;
import com.comeon.test.comeon.service.GameLoveServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GameLoveServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameLoveRepository gameLoveRepository;

    @InjectMocks
    private GameLoveServiceImpl gameLoveService;

    

    @Test
    void testLoveGame() {
        Long playerId = 1L;
        Long gameId = 1L;
        Player player = new Player();
        player.setId(playerId);
        Game game = new Game();
        game.setId(gameId);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        GameLove gameLove = new GameLove();
        gameLove.setPlayer(player);
        gameLove.setGame(game);

        when(gameLoveRepository.save(any(GameLove.class))).thenReturn(gameLove);

        GameLove result = gameLoveService.loveGame(playerId, gameId);

        assertNotNull(result);
        assertEquals(playerId, result.getPlayer().getId());
        assertEquals(gameId, result.getGame().getId());
        verify(gameLoveRepository, times(1)).save(any(GameLove.class));
    }

    @Test
    void testUnloveGame() {
        Long playerId = 1L;
        Long gameId = 1L;
        Player player = new Player();
        player.setId(playerId);
        Game game = new Game();
        game.setId(gameId);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        gameLoveService.unloveGame(playerId, gameId);

        verify(gameLoveRepository, times(1)).deleteByPlayerIdAndGameId(playerId, gameId);
    }

    @Test
    void testFindAllGamesLovedByPlayer() {
        Long playerId = 1L;
        Player player = new Player();
        player.setId(playerId);
        Game game1 = new Game();
        game1.setId(1L);
        Game game2 = new Game();
        game2.setId(2L);
        List<Game> games = Arrays.asList(game1, game2);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
        when(gameLoveRepository.findAllGamesLovedByPlayer(playerId)).thenReturn(games);

        List<Game> result = gameLoveService.findAllGamesLovedByPlayer(playerId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(gameLoveRepository, times(1)).findAllGamesLovedByPlayer(playerId);
    }

    @Test
    void testGetTopLovedGames() {
        int x = 2;
        Object[] result1 = new Object[]{1L, 10L};
        Object[] result2 = new Object[]{2L, 5L};
        List<Object[]> results = Arrays.asList(result1, result2);

        when(gameLoveRepository.findMostLovedGames(x)).thenReturn(results);

        List<TopGameDTO> topGames = gameLoveService.getTopLovedGames(x);

        assertNotNull(topGames);
        assertEquals(2, topGames.size());
        assertEquals(1L, topGames.get(0).getGameId());
        assertEquals(10L, topGames.get(0).getLoveCount());
        assertEquals(2L, topGames.get(1).getGameId());
        assertEquals(5L, topGames.get(1).getLoveCount());
    }
}