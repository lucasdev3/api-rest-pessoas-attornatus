package br.com.lucasdev3.api.controllers;

import static br.com.lucasdev3.api.utils.ConstrucaoPessoa.createPessoa;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.lucasdev3.api.domain.Pessoa;
import br.com.lucasdev3.api.repositories.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste do controller pessoa")
class PessoasControllerTest {

  @InjectMocks
  PessoasController pessoasController;

  @MockBean
  private PessoaRepository pessoaRepository;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Deverá buscar lista de pessoas e retornar um status 200")
  public void buscar_pessoas_retorna_ok() throws Exception {
    Mockito.when(pessoaRepository.findAll()).thenReturn(List.of(createPessoa()));
    this.mockMvc.perform(get("/pessoas"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Deverá buscar lista de pessoas e retornar um status 404")
  public void buscar_pessoas_retorna_notFound() throws Exception {
    Mockito.when(pessoaRepository.findAll()).thenReturn(Collections.emptyList());
    this.mockMvc.perform(get("/pessoas"))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Deverá buscar pessoa pelo id e retornar um status 200")
  public void buscar_pessoas_pelo_id_retorna_ok() throws Exception {
    Optional<Pessoa> pessoa = Optional.of(createPessoa());
    Mockito.when(pessoaRepository.findById(1L)).thenReturn(pessoa);
    this.mockMvc.perform(get("/pessoas/1"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Deverá buscar pessoa pelo id e retornar um status 404")
  public void buscar_pessoas_pelo_id_retorna_notfound() throws Exception {
    Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());
    this.mockMvc.perform(get("/pessoas/1"))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Deverá salvar pessoa e retornar um status 201")
  public void salvar_pessoa_retorna_created() throws Exception {
    this.mockMvc.perform(post("/pessoas/salvar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(createPessoa()))
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Deverá salvar pessoa e retornar um status 406")
  public void salvar_pessoa_pattern_dataNascimento_invalido_retorna_notAcceptable()
      throws Exception {
    Pessoa pessoa = createPessoa();
    // Setando pattern invalido
    pessoa.setDataNascimento("20-06-1990");
    this.mockMvc.perform(post("/pessoas/salvar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(pessoa))
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(content().json("{\"status\":406,\"message\":\"Data inválida\"}"))
        .andExpect(status().isNotAcceptable());
  }

  @Test
  @DisplayName("Deverá salvar pessoa e retornar um status 406")
  public void salvar_pessoa_ano_dataNascimento_invalida_retorna_notAcceptable() throws Exception {
    Pessoa pessoa = createPessoa();
    pessoa.setDataNascimento("20/06/2050");
    this.mockMvc.perform(post("/pessoas/salvar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(pessoa))
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(content().json(
            "{\"status\":406,\"message\":\"Data de nascimento deve ser menor que a data corrente!\"}"))
        .andExpect(status().isNotAcceptable());
  }

  @Test
  @DisplayName("Deverá atualizar pessoa e retornar um status 200")
  public void atualizar_pessoa_retorna_ok() throws Exception {
    Pessoa pessoa = createPessoa();
    pessoa.setId(1L);
    pessoa.setNome("teste update");
    Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
    this.mockMvc.perform(put("/pessoas/atualizar/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(pessoa))
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(content().json(
            "{\"data\":\"Pessoa atualizada com sucesso!\"}"))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Deverá atualizar pessoa e retornar um status 404")
  public void atualizar_pessoa_retorna_notFound() throws Exception {
    Pessoa pessoa = createPessoa();
    pessoa.setId(1L);
    Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());
    this.mockMvc.perform(put("/pessoas/atualizar/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(pessoa))
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(content().json("{\"status\":404,\"message\":\"Nenhuma pessoa encontrada!\"}"))
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Deverá deletar pessoa e retornar um status 200")
  public void deleta_pessoa_retorna_ok() throws Exception {
    Pessoa pessoa = createPessoa();
    pessoa.setId(1L);
    Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
    this.mockMvc.perform(delete("/pessoas/deletar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(content().json(
            "{\"data\":\"Pessoa deletada\"}"))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Deverá deletar pessoa e retornar um status 404")
  public void deleta_pessoa_retorna_404() throws Exception {
    Pessoa pessoa = createPessoa();
    pessoa.setId(1L);
    Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());
    this.mockMvc.perform(delete("/pessoas/deletar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(content().json("{\"status\":404,\"message\":\"Nenhuma pessoa encontrada!\"}"))
        .andExpect(status().isNotFound());
  }

}