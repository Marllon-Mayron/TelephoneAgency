package com.msilva.telephoneAgency.domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.msilva.telephoneAgency.domain.dto.UsuarioDTO;
import com.msilva.telephoneAgency.domain.entity.Usuario;
import com.msilva.telephoneAgency.domain.repository.UsuarioRepository;
import com.msilva.telephoneAgency.exception.UsuarioException;
import com.msilva.telephoneAgency.service.UsuarioService;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	
	@Override
	public UsuarioDTO autenticar(String email, String senha) {
		if(this.usuarioRepository.existsByEmail(email)) {
			Usuario usuario = usuarioRepository.findByEmail(email);
			
			if (usuario != null && senha.equals(usuario.getSenha())) {
				
				UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
				
				usuarioDTO.setEmail(usuario.getEmail());
				usuarioDTO.setSenha(usuario.getSenha());
                usuarioDTO.setId(usuario.getId());
                
                
				return usuarioDTO;
            }else {
            	throw new UsuarioException("Senha incorreta");
            }
		}else{
			return null;
		}
	}

	@Override
	public String cadastrar(String email, String senha) {
		String message = validarEmail(email); // Antes de cadastrar, verifique se o email já está em uso
        
		Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);
        novoUsuario.setDt_cadastro(new Date());
        
        try {
        	usuarioRepository.save(novoUsuario);
            message = "Usuario Criado com sucesso!";
        }catch(Exception e) {
        	message = "Erro ao cadastrar: "+e;
        }
        
        return message;
	}

	@Override
	public String validarEmail(String email) {
		if(usuarioRepository.existsByEmail(email)) {
			return "Já existe email cadastrado";
		}else {
			if(!(email.contains("@"))) {
				return "Email Invalido, restarte a aplicação";
			}else {
				return "";
			}
		
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Optional<Usuario> novoUsuarioOptional = usuarioRepository.findById(id);
		Usuario usuario = novoUsuarioOptional.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID " + id));
		
		usuarioRepository.delete(usuario);
		
	}


	@Override
	public UsuarioDTO findUserById(Long id) {
		// TODO Auto-generated method stub
		Optional<Usuario> novoUsuarioOptional = usuarioRepository.findById(id);
		Usuario usuario = novoUsuarioOptional.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID " + id));
		
		UsuarioDTO userDTO = new UsuarioDTO(usuario);
		
		userDTO.setEmail(usuario.getEmail());
		userDTO.setId(id);
		
		return userDTO;
	}


	@Override
	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		
		for (Usuario usuario : usuarios) {
		    UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		    usuarioDTO.setId(usuario.getId());
		    usuarioDTO.setEmail(usuario.getEmail());
		    usuariosDTO.add(usuarioDTO);
		}
		
		return usuariosDTO;
		
	}


	@Override
	public void update(UsuarioDTO usuarioDTO) {
		Optional<Usuario> novoUsuarioOptional = usuarioRepository.findById(usuarioDTO.getId());
		Usuario usuario = novoUsuarioOptional.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID " + usuarioDTO.getId()));
		
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNome(usuarioDTO.getNome());

		usuarioRepository.save(usuario);
	}
	
	

}
