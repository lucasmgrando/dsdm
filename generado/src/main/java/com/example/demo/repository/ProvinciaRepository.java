package com.example.demo.repository;

import com.example.demo.domain.Provincia;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
	public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
}
