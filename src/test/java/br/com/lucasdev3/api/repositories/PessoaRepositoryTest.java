package br.com.lucasdev3.api.repositories;

import static br.com.lucasdev3.api.utils.ConstrucaoPessoa.createPessoa;
import static br.com.lucasdev3.api.utils.ConstrucaoPessoa.createPessoaVazia;

import br.com.lucasdev3.api.domain.Endereco;
import br.com.lucasdev3.api.domain.Pessoa;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
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

    Assertions.assertThat(pessoaAtualizada.getDataNascimento())
        .isEqualTo(pessoaSalva.getDataNascimento());

    Assertions.assertThat(pessoaAtualizada.getEnderecos()).isEqualTo(pessoaSalva.getEnderecos());

  }

  @Test
  @DisplayName("Deletando pessoa quando sucesso")
  void deletar_pessoa_quando_sucesso() {

    Pessoa pessoa = createPessoa();

    Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);

    this.pessoaRepository.delete(pessoaSalva);

    Optional<Pessoa> pessoaOptional = this.pessoaRepository.findById(pessoaSalva.getId());

    Assertions.assertThat(pessoaOptional.isEmpty()).isTrue();

  }

  @Test
  @DisplayName("Listando pessoa pelo id quando sucesso")
  void busca_pessoa_pelo_id_quando_sucesso() {

    Pessoa pessoa = createPessoa();

    Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);

    Optional<Pessoa> pessoaEncontradaOptional = this.pessoaRepository.findById(pessoaSalva.getId());

    Assertions.assertThat(pessoaEncontradaOptional.isEmpty()).isFalse();

    Assertions.assertThat(pessoaEncontradaOptional.get().getNome())
        .isEqualTo(pessoaSalva.getNome());

    Assertions.assertThat(pessoaEncontradaOptional.get().getDataNascimento())
        .isEqualTo(pessoaSalva.getDataNascimento());

    Assertions.assertThat(pessoaEncontradaOptional.get().getEnderecos())
        .isEqualTo(pessoaSalva.getEnderecos());

  }

  @Test
  @DisplayName("Lancando exception ConstraintViolationException quando field não for valida")
  void lancando_exception_ConstraintViolationException_quando_sucesso() {

    Pessoa pessoa = createPessoaVazia();

    Assertions.assertThatThrownBy(() -> this.pessoaRepository.save(pessoa))
        .isInstanceOf(ConstraintViolationException.class);

  }

}