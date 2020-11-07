package com.example.demo.repository;

import com.example.demo.Autor;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
	public interface AutorRepository extends JpaRepository<Autor, Long> {
}
