package com.kamila.backofficeteste.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; //isso faz a persistencia do banco de dados

import com.kamila.backofficeteste.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);
}
