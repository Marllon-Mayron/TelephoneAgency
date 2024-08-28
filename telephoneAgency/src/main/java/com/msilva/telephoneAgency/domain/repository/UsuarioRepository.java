package com.msilva.telephoneAgency.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msilva.telephoneAgency.domain.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	//JPA VERIFICA SE O EMAIL EXISTE NA COLUNA EMAIL_USUARIO
	boolean existsByEmail(String email);
	Usuario findByEmail(String email);
	Optional<Usuario> findById(Long id);
	List<Usuario> findAll();
}
