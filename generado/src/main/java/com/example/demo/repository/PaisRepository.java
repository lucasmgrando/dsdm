package com.example.demo.repository;

import com.example.demo.domain.Pais;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
	public interface PaisRepository extends JpaRepository<Pais, Long> {
}
