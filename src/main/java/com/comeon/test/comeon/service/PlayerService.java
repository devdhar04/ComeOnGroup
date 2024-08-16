package com.comeon.test.comeon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comeon.test.comeon.entity.Player;
import com.comeon.test.comeon.model.PlayerDTO;
import com.comeon.test.comeon.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	PlayerRepository playerRepository;

	public Player getPlayer(Long playerId) {

		return playerRepository.findById(playerId).get();
	}

	public List<Player> addPlayer(List<PlayerDTO> playerList) {

		return playerRepository.saveAll(convertToEntityList(playerList));
	}

	private List<Player> convertToEntityList(List<PlayerDTO> gameDTOs) {
		return gameDTOs.stream().map(gameDTO -> modelMapper.map(gameDTO, Player.class)).collect(Collectors.toList());
	}

}
