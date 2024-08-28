package com.msilva.telephoneAgency.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msilva.telephoneAgency.domain.dto.UsuarioDTO;
import com.msilva.telephoneAgency.service.UsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UserController {
	@Autowired
	private UsuarioService usuarioService;
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		try {
			usuarioService.delete(id);
			return ResponseEntity.ok("Usuario deletado!");
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao deletar usuario --> "+e);
		}		
	}
	@GetMapping("{id}")
	public ResponseEntity<?> selectUser(@PathVariable Long id){
		try {
	        UsuarioDTO userDTO = usuarioService.findUserById(id);
	        return ResponseEntity.ok(userDTO);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar usuario --> "+e);
	    }
	}
	@GetMapping("")
	public ResponseEntity<?> selectAll(){
		List<UsuarioDTO> usuarios = usuarioService.findAll();
		return ResponseEntity.ok().body(usuarios);
	}
	@PostMapping("create")
	public ResponseEntity<String> createUser(@RequestBody UsuarioDTO newUser){
		return ResponseEntity.ok(usuarioService.cadastrar(newUser.getEmail(), newUser.getSenha()));
	}
	@PostMapping("update")
	public ResponseEntity<String> updateUser(@RequestBody UsuarioDTO userDTO){
		usuarioService.update(userDTO);
		return ResponseEntity.ok("Usuário atualizado com sucesso!");
	}
}
