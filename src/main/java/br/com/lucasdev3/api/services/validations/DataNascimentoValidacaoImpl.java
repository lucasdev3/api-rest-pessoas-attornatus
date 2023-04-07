package br.com.lucasdev3.api.services.validations;

import br.com.lucasdev3.api.exceptions.DataNascimentoException;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import br.com.lucasdev3.api.repositories.PessoaRepository;
import br.com.lucasdev3.api.services.ValidacaoPessoaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component("DataNascimentoValidacao")
public class DataNascimentoValidacaoImpl implements ValidacaoPessoaService {

  @Override
  public void validacao(PessoaRepository pessoaRepository, SalvarPessoaModel salvarPessoaModel) {

    // Validacao de entrada
    Pattern pattern = Pattern.compile(
        "^(?:31([/\\-.])(?:0?[13578]|1[02])\\1|(?:29|30)([/\\-.])(?:0?[13-9]|1[0-2])\\2)(?:1[6-9]|[2-9]\\d)?\\d{2}$|^29([/\\-.])0?2\\3(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$|^(?:0?[1-9]|1\\d|2[0-8])([/\\-.])(?:0?[1-9]|1[0-2])\\4(?:1[6-9]|[2-9]\\d)?\\d{2}$");
    Matcher matcher = pattern.matcher(salvarPessoaModel.getDataNascimento());
    if (!matcher.matches()) {
      throw new DataNascimentoException(
          "Data de nascimento inválida. O formato deve ser dd/MM/yyyy");
    }

    // Valida se a data de nascimento é menor ou igual ao ano corrente
    try {
      LocalDate data = LocalDate.parse(salvarPessoaModel.getDataNascimento(),
          DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      LocalDate hoje = LocalDate.now();
      if (!data.isBefore(hoje)) {
        throw new DataNascimentoException("Data de nascimento deve ser menor que a data corrente!");
      }
    } catch (DateTimeParseException ex) {
      throw new DataNascimentoException("Data inválida");
    }

  }

}
