package com.comeon.test.comeon.model;

public class TopGameDTO {

	private Long gameId;
	private Long loveCount;

	public TopGameDTO(Long gameId, Long loveCount) {
		this.gameId = gameId;
		this.loveCount = loveCount;
	}

	// Getters and setters
	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Long getLoveCount() {
		return loveCount;
	}

	public void setLoveCount(Long loveCount) {
		this.loveCount = loveCount;
	}
}