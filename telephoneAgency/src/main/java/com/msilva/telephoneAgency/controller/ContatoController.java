package com.msilva.telephoneAgency.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msilva.telephoneAgency.domain.dto.ContatoDTO;
import com.msilva.telephoneAgency.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	
	@Autowired
	private ContatoService contatoService;

	@GetMapping
	public ResponseEntity<List<ContatoDTO>> buscar(@RequestParam boolean snFavorito, @RequestParam boolean snAtivo) {
	    List<ContatoDTO> contatos = contatoService.buscarPorFiltro(snFavorito, snAtivo);
	    return ResponseEntity.ok().body(contatos);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> buscarId(@PathVariable Long id){
		try {
	        ContatoDTO contatoDTO = contatoService.findById(id);
	        return ResponseEntity.ok(contatoDTO);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar registro --> "+e);
	    }
	}
	
	@GetMapping("/cell/{celular}")
	public ResponseEntity<?> buscarCelular(@PathVariable String celular){
		try {
	        ContatoDTO contatoDTO = contatoService.findByCelular(celular);
	        return ResponseEntity.ok(contatoDTO);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar registro --> "+e);
	    }
	}
	
	@PostMapping("/criar")
	public ResponseEntity<Void> criarContato(@RequestBody ContatoDTO contatoDTO) {
		contatoService.cadastrar(contatoDTO);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/atualizar/{id}")
	public ResponseEntity<?> atualizarContato(@RequestBody ContatoDTO contatoDTO) throws Exception{
		contatoService.atualizar(contatoDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/favoritos")
	public ResponseEntity<List<ContatoDTO>> buscaContatosFavoritos() {
		List<ContatoDTO> contatos = contatoService.buscaContatosFavoritos();
		return ResponseEntity.ok().body(contatos);
	}
}
