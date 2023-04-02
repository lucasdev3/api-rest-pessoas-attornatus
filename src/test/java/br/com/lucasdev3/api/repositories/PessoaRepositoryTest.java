package br.com.lucasdev3.api.repositories;

import br.com.lucasdev3.api.domain.Endereco;
import br.com.lucasdev3.api.domain.Pessoa;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Teste do repository pessoa")
class PessoaRepositoryTest {

  @Autowired
  private PessoaRepository pessoaRepository;

  @Test
  @DisplayName("Salvando pessoa quando sucesso")
  void salvar_pessoa_quando_sucesso() {

    Pessoa pessoa = createPessoa();

    Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);

    Assertions.assertThat(pessoaSalva).isNotNull();

    Assertions.assertThat(pessoaSalva.getNome()).isEqualTo(pessoa.getNome());

    Assertions.assertThat(pessoaSalva.getDataNascimento()).isEqualTo(pessoa.getDataNascimento());

    Assertions.assertThat(pessoaSalva.getEnderecos()).isEqualTo(pessoa.getEnderecos());

  }

  @Test
  @DisplayName("Atualizando pessoa quando sucesso")
  void atualizar_pessoa_quando_sucesso() {

    Pessoa pessoa = createPessoa();

    Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);

    Endereco endereco1 = new Endereco(new SalvarEnderecoModel("teste alterado", "22222-444", "66",
        true));
    Endereco endereco2 = new Endereco(new SalvarEnderecoModel("teste2 alterado", "77777-888", "23",
        true));

    List<Endereco> enderecos = new ArrayList<>();
    enderecos.add(endereco1);
    enderecos.add(endereco2);

    pessoaSalva.setNome("Lucas Alterado");
    pessoaSalva.setDataNascimento("20/02/2000");
    pessoaSalva.setEnderecos(enderecos);

    Pessoa pessoaAtualizada = this.pessoaRepository.save(pessoaSalva);

    Assertions.assertThat(pessoaAtualizada).isNotNull();

    Assertions.assertThat(pessoaAtualizada.getNome()).isEqualTo(pessoaSalva.getNome());

    Assertions.assertThat(pessoaAtualizada.getDataNascimento()).isEqualTo(pessoaSalva.getDataNascimento());

    Assertions.assertThat(pessoaAtualizada.getEnderecos()).isEqualTo(pessoaSalva.getEnderecos());

  }


  private Pessoa createPessoa() {
    SalvarEnderecoModel enderecoModel1 = new SalvarEnderecoModel("teste", "41232-333", "33",
        true);
    SalvarEnderecoModel enderecoModel2 = new SalvarEnderecoModel("teste2", "66666-555", "77",
        false);
    SalvarPessoaModel salvarPessoaModel = new SalvarPessoaModel("Lucas", "20/06/1996",
        Arrays.asList(enderecoModel1, enderecoModel2));
    return new Pessoa(salvarPessoaModel);
  }

}