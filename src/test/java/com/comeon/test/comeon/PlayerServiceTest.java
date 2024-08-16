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

import com.comeon.test.comeon.entity.Player;
import com.comeon.test.comeon.model.PlayerDTO;
import com.comeon.test.comeon.repository.PlayerRepository;
import com.comeon.test.comeon.service.PlayerService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PlayerService playerService;
 

    @Test
    void testGetPlayer() {
        Long playerId = 1L;
        Player player = new Player();
        player.setId(playerId);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

        Player result = playerService.getPlayer(playerId);

        assertNotNull(result);
        assertEquals(playerId, result.getId());
        verify(playerRepository, times(1)).findById(playerId);
    }

    @Test
    void testAddPlayer() {
        PlayerDTO playerDTO1 = new PlayerDTO();
        playerDTO1.setId(1L);
        playerDTO1.setPlayerName("Aladdin");

        PlayerDTO playerDTO2 = new PlayerDTO();
        playerDTO2.setId(2L);
        playerDTO2.setPlayerName("LOKI");

        List<PlayerDTO> playerDTOList = Arrays.asList(playerDTO1, playerDTO2);

        Player player1 = new Player();
        player1.setId(1L);
        player1.setName("Aladdin");

        Player player2 = new Player();
        player2.setId(2L);
        player2.setName("LOKI");

        List<Player> playerList = Arrays.asList(player1, player2);

        when(modelMapper.map(playerDTO1, Player.class)).thenReturn(player1);
        when(modelMapper.map(playerDTO2, Player.class)).thenReturn(player2);
        when(playerRepository.saveAll(any())).thenReturn(playerList);

        List<Player> result = playerService.addPlayer(playerDTOList);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Aladdin", result.get(0).getName());
        assertEquals("LOKI", result.get(1).getName());
        verify(playerRepository, times(1)).saveAll(any());
    }
}