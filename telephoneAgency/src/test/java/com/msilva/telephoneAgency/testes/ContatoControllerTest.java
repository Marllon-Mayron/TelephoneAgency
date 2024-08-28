package com.msilva.telephoneAgency.testes;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.msilva.telephoneAgency.domain.dto.ContatoDTO;
import com.msilva.telephoneAgency.service.ContatoService;

@SpringBootTest
public class ContatoControllerTest {
	
	@MockBean
    private ContatoService contatoService;

	
	@Test
	public void findByIdContato() {
		ContatoDTO contato = new ContatoDTO();
		contato.setId(1L); 
		contato.setNome("Vitu");
		contato.setEmail("vitu@gmail.com");
		contato.setCelular("81999249592");
		contato.setTelefone("34559924");
		contato.setSnFavorito("S"); 
		contato.setSnAtivo("S"); 
		contato.setDhCad(LocalDateTime.now()); 
		
		final var expectedId = contato.getId();
		Assertions.assertEquals(expectedId, 1);
	}

    @Test
    public void findByCelular() {
        String celular = "81999249592";
        
        ContatoDTO mockContato = new ContatoDTO();
        mockContato.setCelular(celular);
        Mockito.when(contatoService.findByCelular(celular)).thenReturn(mockContato);

        ContatoDTO contato = contatoService.findByCelular(celular);

        Assertions.assertNotNull(contato);
        Assertions.assertEquals(celular, contato.getCelular());
    }
}
