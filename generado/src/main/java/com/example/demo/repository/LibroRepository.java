package com.example.demo.repository;

import com.example.demo.domain.Libro;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
	public interface LibroRepository extends JpaRepository<Libro, Long> {
}
