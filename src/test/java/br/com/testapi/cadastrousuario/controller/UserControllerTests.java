package br.com.testapi.cadastrousuario.controller;

import br.com.testapi.cadastrousuario.config.JacksonConfig;
import br.com.testapi.cadastrousuario.model.User;
import br.com.testapi.cadastrousuario.reposiroty.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@Import(JacksonConfig.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private JacksonConfig objectMapper;

    @Test
    public void testCadastrarUsuario() throws Exception {
        User newUser = User.builder()
                .nome("Joao da Silva")
                .email("joao.silva@gmail.com")
                .dataNascimento(LocalDate.of(1990, 1, 15))
                .endereco("123 Rua da Amostra")
                .habilidades(Collections.singletonList("Java"))
                .build();

        when(userRepository.save(newUser)).thenReturn(newUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.objectMapper().writeValueAsString(newUser)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Joao da Silva"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("joao.silva@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endereco").value("123 Rua da Amostra"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.habilidades").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.habilidades[0]").value("Java"));
    }

    @Test
    public void testListarUsuarios() throws Exception {
        User user = User.builder()
                .nome("João da Silva")
                .email("joao.silva@gmail.com")
                .dataNascimento(LocalDate.of(1990, 1, 15))
                .endereco("123 Rua da Amostra")
                .habilidades(Collections.singletonList("Java"))
                .build();

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("João da Silva"));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

