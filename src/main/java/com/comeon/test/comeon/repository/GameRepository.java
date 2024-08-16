package com.comeon.test.comeon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comeon.test.comeon.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	// Add custom queries as needed
}