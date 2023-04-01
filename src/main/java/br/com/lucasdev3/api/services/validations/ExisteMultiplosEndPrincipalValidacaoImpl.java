package br.com.lucasdev3.api.services.validations;

import br.com.lucasdev3.api.exceptions.DataNascimentoException;
import br.com.lucasdev3.api.exceptions.LimiteEnderecoPrincipalException;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import br.com.lucasdev3.api.repositories.PessoaRepository;
import br.com.lucasdev3.api.services.ValidacaoPessoaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component("ExisteMultiplosEndPrincipalValidacao")
public class ExisteMultiplosEndPrincipalValidacaoImpl implements ValidacaoPessoaService {
  @Override
  public void validacao(PessoaRepository pessoaRepository, SalvarPessoaModel salvarPessoaModel) {
    List<SalvarEnderecoModel> enderecos = salvarPessoaModel.getEnderecos();
    int totalFiltered = (int) enderecos
        .stream()
        .filter(SalvarEnderecoModel::getEnderecoPrincipal)
        .count();

    if(totalFiltered > 1) {
      throw new LimiteEnderecoPrincipalException("Limite de endereço principal atingido!");
    }
  }


}
