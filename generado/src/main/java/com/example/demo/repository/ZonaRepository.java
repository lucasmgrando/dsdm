package com.example.demo.repository;

import com.example.demo.domain.Zona;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
	public interface ZonaRepository extends JpaRepository<Zona, Long> {
}
