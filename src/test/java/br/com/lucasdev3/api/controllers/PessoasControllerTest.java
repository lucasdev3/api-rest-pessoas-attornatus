package br.com.lucasdev3.api.controllers;

import static br.com.lucasdev3.api.utils.ConstrucaoPessoa.createPessoa;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.lucasdev3.api.domain.Pessoa;
import br.com.lucasdev3.api.repositories.PessoaRepository;
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
//    Mockito.when(pessoaRepository.save(Mockito.any())).thenReturn(createPessoa());
    this.mockMvc.perform(post("/pessoas/salvar")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n"
                + "    \"nome\": \"kkkkk666 kkkkk\",\n"
                + "    \"dataNascimento\": \"10/04/2020\",\n"
                + "    \"enderecos\": [\n"
                + "        {\n"
                + "            \"logradouro\": \"teste4\",\n"
                + "            \"cep\": \"12440-230\",\n"
                + "            \"numero\": \"44\"\n"
                + "        }\n"
                + "    ]\n"
                + "}")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Deverá salvar pessoa e retornar um status 406")
  public void salvar_pessoa_pattern_dataNascimento_invalido_retorna_notAcceptable()
      throws Exception {
//    Mockito.when(pessoaRepository.save(Mockito.any())).thenReturn(createPessoa());
    this.mockMvc.perform(post("/pessoas/salvar")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n"
                + "    \"nome\": \"kkkkk666 kkkkk\",\n"
                + "    \"dataNascimento\": \"10-04-2020\",\n"
                + "    \"enderecos\": [\n"
                + "        {\n"
                + "            \"logradouro\": \"teste4\",\n"
                + "            \"cep\": \"12440-230\",\n"
                + "            \"numero\": \"44\"\n"
                + "        }\n"
                + "    ]\n"
                + "}")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(content().json("{\"status\":406,\"message\":\"Data inválida\"}"))
        .andExpect(status().isNotAcceptable());
  }

  @Test
  @DisplayName("Deverá salvar pessoa e retornar um status 406")
  public void salvar_pessoa_ano_dataNascimento_invalida_retorna_notAcceptable() throws Exception {
//    Mockito.when(pessoaRepository.save(Mockito.any())).thenReturn(createPessoa());
    this.mockMvc.perform(post("/pessoas/salvar")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n"
                + "    \"nome\": \"kkkkk666 kkkkk\",\n"
                + "    \"dataNascimento\": \"10/04/2099\",\n"
                + "    \"enderecos\": [\n"
                + "        {\n"
                + "            \"logradouro\": \"teste4\",\n"
                + "            \"cep\": \"12440-230\",\n"
                + "            \"numero\": \"44\"\n"
                + "        }\n"
                + "    ]\n"
                + "}")
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(content().json(
            "{\"status\":406,\"message\":\"Data de nascimento deve ser menor que a data corrente!\"}"))
        .andExpect(status().isNotAcceptable());
  }

}