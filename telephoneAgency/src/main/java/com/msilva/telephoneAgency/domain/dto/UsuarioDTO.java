package com.msilva.telephoneAgency.domain.dto;

import java.text.SimpleDateFormat;

import com.msilva.telephoneAgency.domain.entity.Usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
//@JsonInclude(Include.NON_NULL)
public class UsuarioDTO {
    private String email;
    private String senha;
    private String nome;
    private Long id;
    private String dt_cadastro;
   
    public UsuarioDTO(Usuario usuario) {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.id = usuario.getId();
    	this.email = usuario.getEmail();
    	this.nome = usuario.getNome();
    	this.senha = usuario.getSenha();   	
    	this.dt_cadastro = usuario.getDt_cadastro() != null ? sdf.format(usuario.getDt_cadastro()) : "";
    }

}
