package br.com.lucasdev3.api.controllers;

import static br.com.lucasdev3.api.utils.ConstrucaoPessoa.createPessoa;

import br.com.lucasdev3.api.domain.Pessoa;
import br.com.lucasdev3.api.models.ResponseModel;
import br.com.lucasdev3.api.models.pessoas.ListarPessoaModel;
import br.com.lucasdev3.api.services.PessoaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste do controller pessoa")
class PessoasControllerTest {

  @InjectMocks
  private PessoasController pessoasController;

  @Mock
  private PessoaService pessoaServiceMock;

  @BeforeEach
  void setup() {
    List<ListarPessoaModel> pessoas = List.of(new ListarPessoaModel(createPessoa()));
    BDDMockito.when(this.pessoaServiceMock.buscar()).thenReturn(pessoas);
  }

  @Test
  @DisplayName("Listando pessoas retorno do controller quando sucesso")
  void lista_retorno_do_controller_buscar_quando_sucesso() {

    Pessoa pessoaEsperada = createPessoa();

    ResponseModel<List<ListarPessoaModel>> body = this.pessoasController.buscar().getBody();
    Assertions.assertThat(body).isNotNull();

    List<ListarPessoaModel> pessoas = Objects.requireNonNull(body).getData();

//    Assertions.assertThat(pessoas.get(0).getNome()).isEqualTo(pessoaEsperada.getNome());
//    Assertions.assertThat(pessoas.get(0).getDataNascimento()).isEqualTo(pessoaEsperada.getDataNascimento());
//    Assertions.assertThat(pessoas.get(0).getEnderecos()).isEqualTo(pessoaEsperada.getEnderecos());


  }

}