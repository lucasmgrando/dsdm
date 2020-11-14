package com.example.demo.repository;

import com.example.demo.domain.Domicilio;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
	public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
