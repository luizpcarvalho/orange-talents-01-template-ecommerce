package br.com.zup.mercadolivre.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Transactional
class NovoUsuarioTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper jsonMapper;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void deveCriarUmUsuarioComLoginESenha() throws Exception {
        NovoUsuarioRequest novoUsuarioRequest = new NovoUsuarioRequest("user@email.com", "abc123");

        mockMvc.perform(post("/usuario")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonMapper.writeValueAsString(novoUsuarioRequest)))
                .andExpect(status().isOk());

        Optional<Usuario> usuarioSalvo = usuarioRepository.findByLogin("user@email.com");

        assertAll(() -> assertTrue(usuarioSalvo.isPresent()),
                  () -> assertEquals("user@email.com", usuarioSalvo.get().getLogin()),
                  () -> assertNotNull(usuarioSalvo.get().getSenha()));

    }

    @Test
    public void naoDeveCriarUmUsuarioQuandoOLoginEstiverVazio() throws Exception {
        NovoUsuarioRequest novoUsuarioRequest = new NovoUsuarioRequest(null, "abc123");

        mockMvc.perform(post("/usuario")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonMapper.writeValueAsString(novoUsuarioRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[?(@.campo == 'login')].mensagem")
                        .value("must not be blank"));
    }

}