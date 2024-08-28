package com.msilva.telephoneAgency.service;

import java.util.List;

import com.msilva.telephoneAgency.domain.dto.UsuarioDTO;


public interface UsuarioService {
	UsuarioDTO autenticar(String email, String senha);
	
	String cadastrar(String email, String senha);
	
	String validarEmail(String email);
	
	void update(UsuarioDTO usuarioDTO);
	
	void delete(Long id);
	
	UsuarioDTO findUserById(Long id);
	
	List<UsuarioDTO> findAll();
}
