package com.comeon.test.comeon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comeon.test.comeon.entity.Game;
import com.comeon.test.comeon.model.GameDTO;
import com.comeon.test.comeon.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	GameRepository gameRepository;

	public Game getGame(Long gameId) {

		return gameRepository.findById(gameId).get();
	}

	public List<Game> addGame(List<GameDTO> gameList) {

		return gameRepository.saveAll(convertToEntityList(gameList));
	}

	public List<Game> getAllGames() {
		return gameRepository.findAll();
	}

	private List<Game> convertToEntityList(List<GameDTO> gameList) {
		return gameList.stream().map(gameDTO -> modelMapper.map(gameDTO, Game.class)).collect(Collectors.toList());
	}

}
