package com.comeon.test.comeon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comeon.test.comeon.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	// Add custom queries as needed
}