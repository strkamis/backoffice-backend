package com.kamila.backofficeteste.repositories;

import org.springframework.data.jpa.repository.JpaRepository; //isso faz a persistencia do banco de dados

import com.kamila.backofficeteste.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
