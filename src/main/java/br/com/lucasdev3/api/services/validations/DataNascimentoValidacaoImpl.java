package br.com.lucasdev3.api.services.validations;

import br.com.lucasdev3.api.exceptions.DataNascimentoException;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import br.com.lucasdev3.api.repositories.PessoaRepository;
import br.com.lucasdev3.api.services.ValidacaoPessoaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component("DataNascimentoValidacao")
public class DataNascimentoValidacaoImpl implements ValidacaoPessoaService {

  @Override
  public void validacao(PessoaRepository pessoaRepository, SalvarPessoaModel salvarPessoaModel) {
    LocalDate data = LocalDate.parse(salvarPessoaModel.getDataNascimento(),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    LocalDate hoje = LocalDate.now();
    if (!data.isBefore(hoje)) {
      throw new DataNascimentoException("Data de nascimento deve ser menor que a data corrente!");
    }
  }

}
