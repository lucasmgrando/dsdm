package com.example.demo.repository;

import com.example.demo.domain.Localidad;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
	public interface LocalidadRepository extends JpaRepository<Localidad, Long> {
}
