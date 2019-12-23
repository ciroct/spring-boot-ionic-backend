package com.ciro.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ciro.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
