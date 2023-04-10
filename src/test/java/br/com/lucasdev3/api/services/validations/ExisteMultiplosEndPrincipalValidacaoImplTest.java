package br.com.lucasdev3.api.services.validations;

import br.com.lucasdev3.api.domain.Endereco;
import br.com.lucasdev3.api.exceptions.DataNascimentoException;
import br.com.lucasdev3.api.exceptions.LimiteEnderecoPrincipalException;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class ExisteMultiplosEndPrincipalValidacaoImplTest {

  @Test
  public void devera_retornar_limite_atingido_endereco_principal() {
    SalvarEnderecoModel salvarEnderecoModel = new SalvarEnderecoModel("teste", "0000-000", "30", true);
    SalvarEnderecoModel salvarEnderecoModel2 = new SalvarEnderecoModel("teste2", "0000-000", "31", true);

    SalvarPessoaModel salvarPessoaModel = new SalvarPessoaModel();
    salvarPessoaModel.setNome("lucas");
    salvarPessoaModel.setDataNascimento("20/06/1996");
    salvarPessoaModel.setEnderecos(Arrays.asList(salvarEnderecoModel, salvarEnderecoModel2));

    ExisteMultiplosEndPrincipalValidacaoImpl validacao = new ExisteMultiplosEndPrincipalValidacaoImpl();

    Assertions.assertThrows(LimiteEnderecoPrincipalException.class, () -> validacao.validacao(null, salvarPessoaModel),
        "Limite de endereço principal atingido!");



  }

}