package com.msilva.telephoneAgency.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.msilva.telephoneAgency.domain.entity.Contato;


@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
	Optional<Contato> findById(Long id);
	Optional<Contato> findByCelular(String contato_celular);
	List<Contato> findBySnFavorito(String snFavorito);
	List<Contato> findBySnAtivo(String snAtivo);
	List<Contato> findAll();
	
	@Query("SELECT c FROM Contato c WHERE c.snFavorito = 'S' AND c.snAtivo = 'N'")
	List<Contato> findBySnFavoritoAndSnAtivo();
}
