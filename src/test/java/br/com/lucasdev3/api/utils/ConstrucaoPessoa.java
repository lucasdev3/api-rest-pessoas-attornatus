package br.com.lucasdev3.api.utils;

import br.com.lucasdev3.api.domain.Pessoa;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import java.util.Arrays;

public class ConstrucaoPessoa {

  public static Pessoa createPessoa() {
    SalvarEnderecoModel enderecoModel1 = new SalvarEnderecoModel("teste", "41232-333", "33",
        true);
    SalvarEnderecoModel enderecoModel2 = new SalvarEnderecoModel("teste2", "66666-555", "77",
        false);
    SalvarPessoaModel salvarPessoaModel = new SalvarPessoaModel("Lucas", "20/06/1996",
        Arrays.asList(enderecoModel1, enderecoModel2));
    return new Pessoa(salvarPessoaModel);
  }

  public static Pessoa createPessoaVazia() {
    SalvarEnderecoModel enderecoModel1 = new SalvarEnderecoModel(null, null, null,
        false);
    SalvarEnderecoModel enderecoModel2 = new SalvarEnderecoModel(null, null, null,
        false);
    SalvarPessoaModel salvarPessoaModel = new SalvarPessoaModel(null, null,
        Arrays.asList(enderecoModel1, enderecoModel2));
    return new Pessoa(salvarPessoaModel);
  }

}
