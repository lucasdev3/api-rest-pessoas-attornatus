package br.com.lucasdev3.api.services.validations;

import br.com.lucasdev3.api.exceptions.DataNascimentoException;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import br.com.lucasdev3.api.repositories.PessoaRepository;
import br.com.lucasdev3.api.services.ValidacaoPessoaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component("DataNascimentoValidacao")
public class DataNascimentoValidacaoImpl implements ValidacaoPessoaService {

  @Override
  public void validacao(PessoaRepository pessoaRepository, SalvarPessoaModel salvarPessoaModel) {

    // Validacao de entrada
    Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
    Matcher matcher = pattern.matcher(salvarPessoaModel.getDataNascimento());
    if (!matcher.matches()) {
      throw new DataNascimentoException(
          "Data de nascimento inválida. O formato deve ser dd/MM/yyyy");
    }

    // Valida se a data de nascimento é menor ou igual ao ano corrente
    LocalDate data = LocalDate.parse(salvarPessoaModel.getDataNascimento(),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    LocalDate hoje = LocalDate.now();
    if (!data.isBefore(hoje)) {
      throw new DataNascimentoException("Data de nascimento deve ser menor que a data corrente!");
    }
  }

}
