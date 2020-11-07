package com.example.demo.repository;

import com.example.demo.Persona;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
	public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
