package com.msilva.telephoneAgency.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msilva.telephoneAgency.domain.dto.ContatoDTO;
import com.msilva.telephoneAgency.domain.entity.Contato;
import com.msilva.telephoneAgency.domain.repository.ContatoRepository;
import com.msilva.telephoneAgency.exception.ContatoJaExistenteException;
import com.msilva.telephoneAgency.service.ContatoService;

@Service
public class ContatoServiceImpl implements ContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	public ContatoServiceImpl(ContatoRepository contatoRepository) {
		super();
		this.contatoRepository = contatoRepository;
	}
	
	@Override
	public ContatoDTO findById(Long id) {
		Optional<Contato> novoContatoOptional = contatoRepository.findById(id);
		Contato contato = novoContatoOptional.orElseThrow(() -> new RuntimeException("Contato de ID " + id+" não localizado"));
		ContatoDTO contatoDTO = new ContatoDTO(contato);
		return contatoDTO;
	}
	
	@Override
	public ContatoDTO findByCelular(String contato_celular) {
		Optional<Contato> novoContatoOptional = contatoRepository.findByCelular(contato_celular);
		Contato contato = novoContatoOptional.orElseThrow(() -> new RuntimeException("Celular com o numero " + contato_celular+" não localizado"));
		ContatoDTO contatoDTO = new ContatoDTO(contato);
		return contatoDTO;
	}

	@Override
	public void cadastrar(ContatoDTO contato) throws ContatoJaExistenteException {
		if(contato != null) {
			Optional<Contato> contatoRegistry = contatoRepository.findByCelular(contato.getCelular());
			if(contatoRegistry.isPresent()) {
				throw new ContatoJaExistenteException("Numero já registrado.");
			}else {
				adicionarContato(contato);
			}
		}
	}

	private void adicionarContato(ContatoDTO contato) throws ContatoJaExistenteException {
		try {
			contatoRepository.save(contato.toEntity());
		}catch(Exception e) {
			throw new ContatoJaExistenteException("Erro ao cadastrar contato!");
		}
	}

	@Override
	public List<ContatoDTO> buscarPorFiltro(boolean exibirFavorito, boolean exibirInativos) {
		
		List<Contato> contatos = null;
		
		if(exibirFavorito == false && exibirInativos == false) {
			contatos = this.contatoRepository.findBySnAtivo("S");
		}else if(exibirFavorito == true && exibirInativos == false) {
			contatos = this.contatoRepository.findBySnFavorito("S");
		}else if(exibirFavorito == false && exibirInativos == true) {
			contatos = this.contatoRepository.findBySnAtivo("N");
		}else if(exibirFavorito == true && exibirInativos == true){
			contatos = this.contatoRepository.findBySnFavoritoAndSnAtivo();
		}
			
		List<ContatoDTO> contatosDTO = new ArrayList<ContatoDTO>();
		
		for(Contato contato : contatos) {
			ContatoDTO contatoDTO = new ContatoDTO(contato);
			contatosDTO.add(contatoDTO);
		}
		
		return contatosDTO;
	}

	@Override
	public void atualizar(ContatoDTO contatoDTO) throws Exception {
		if(contatoDTO != null) {
			Optional<Contato> possivelContato = contatoRepository.findByCelular(contatoDTO.getCelular());
			if(possivelContato.isPresent()) {
				Contato contatoAntigo = possivelContato.orElseThrow(() -> new RuntimeException("Contato de numero " + contatoDTO.getCelular()+" não localizado"));
				contatoAntigo = contatoDTO.toEntity();
			try {	
				contatoRepository.save(contatoAntigo);
			}catch(Exception e) {
				throw new Exception("Erro ao atualizar contato!");
			}
			}else {
				throw new Exception("Contato inexistente!");
			}
		}else {
			throw new Exception("Requisição vazia");
		}
	}

	@Override
	public List<ContatoDTO> buscaContatosFavoritos() {
		List<Contato> contatos = this.contatoRepository.findBySnFavorito("S");
		List<ContatoDTO> contatosFavoritosDTO = new ArrayList<ContatoDTO>();
		
		for(Contato contato : contatos) {
			ContatoDTO contatoDTO = new ContatoDTO(contato);
			contatosFavoritosDTO.add(contatoDTO);
		}
		
		return contatosFavoritosDTO;
	}

}
