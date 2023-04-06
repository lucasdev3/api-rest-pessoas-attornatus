package br.com.lucasdev3.api.services.validations;

import br.com.lucasdev3.api.exceptions.DataNascimentoException;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class DataNascimentoValidacaoImplTest {

  @Test
  public void testValidaPatternDataNascimento() {
    SalvarPessoaModel pessoa = new SalvarPessoaModel();
    pessoa.setDataNascimento("99-99-9999");

    // Cria uma instância da classe DataNascimentoValidacaoImpl
    DataNascimentoValidacaoImpl validacao = new DataNascimentoValidacaoImpl();

    // Verifica se a exceção é lançada
    Assertions.assertThrows(DataNascimentoException.class, () -> validacao.validacao(null, pessoa),
        "Data de nascimento deve ser menor que a data corrente!");
  }

  @Test
  public void testValidaExistenciaDataNascimento() {
    SalvarPessoaModel pessoa = new SalvarPessoaModel();
    pessoa.setDataNascimento("99/99/9999");

    // Cria uma instância da classe DataNascimentoValidacaoImpl
    DataNascimentoValidacaoImpl validacao = new DataNascimentoValidacaoImpl();

    // Verifica se a exceção é lançada
    Assertions.assertThrows(DataNascimentoException.class, () -> validacao.validacao(null, pessoa),
        "Data de nascimento deve ser menor que a data corrente!");
  }

  @Test
  public void testValidaSucessoDataNascimento() {
    SalvarPessoaModel pessoa = new SalvarPessoaModel();
    pessoa.setDataNascimento("20/06/1996");

    // Cria uma instância da classe DataNascimentoValidacaoImpl
    DataNascimentoValidacaoImpl validacao = new DataNascimentoValidacaoImpl();

    validacao.validacao(null, pessoa);

    // Nenhuma exception deve ser lançada

  }

}