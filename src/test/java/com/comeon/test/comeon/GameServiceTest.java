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
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.comeon.test.comeon.entity.Game;
import com.comeon.test.comeon.model.GameDTO;
import com.comeon.test.comeon.repository.GameRepository;
import com.comeon.test.comeon.service.GameService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private GameService gameService;

    

    @Test
    void testGetGame() {
        Long gameId = 1L;
        Game game = new Game();
        game.setId(gameId);

        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        Game result = gameService.getGame(gameId);

        assertNotNull(result);
        assertEquals(gameId, result.getId());
        verify(gameRepository, times(1)).findById(gameId);
    }

    @Test
    void testAddGame() {
        GameDTO gameDTO1 = new GameDTO();
        gameDTO1.setId(1L);
        gameDTO1.setGameName("IGI");

        GameDTO gameDTO2 = new GameDTO();
        gameDTO2.setId(2L);
        gameDTO2.setGameName("COD");

        List<GameDTO> gameDTOList = Arrays.asList(gameDTO1, gameDTO2);

        Game game1 = new Game();
        game1.setId(1L);
        game1.setName("IGI");

        Game game2 = new Game();
        game2.setId(2L);
        game2.setName("COD");

        List<Game> gameList = Arrays.asList(game1, game2);

        when(modelMapper.map(gameDTO1, Game.class)).thenReturn(game1);
        when(modelMapper.map(gameDTO2, Game.class)).thenReturn(game2);
        when(gameRepository.saveAll(any())).thenReturn(gameList);

        List<Game> result = gameService.addGame(gameDTOList);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("IGI", result.get(0).getName());
        assertEquals("COD", result.get(1).getName());
        verify(gameRepository, times(1)).saveAll(any());
    }

    @Test
    void testGetAllGames() {
        Game game1 = new Game();
        game1.setId(1L);
        game1.setName("IGI");

        Game game2 = new Game();
        game2.setId(2L);
        game2.setName("COD");

        List<Game> gameList = Arrays.asList(game1, game2);

        when(gameRepository.findAll()).thenReturn(gameList);

        List<Game> result = gameService.getAllGames();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("IGI", result.get(0).getName());
        assertEquals("COD", result.get(1).getName());
        verify(gameRepository, times(1)).findAll();
    }
}