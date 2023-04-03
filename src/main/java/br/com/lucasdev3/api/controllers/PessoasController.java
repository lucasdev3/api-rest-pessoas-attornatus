package br.com.lucasdev3.api.controllers;

import br.com.lucasdev3.api.domain.Endereco;
import br.com.lucasdev3.api.models.ResponseModel;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import br.com.lucasdev3.api.models.pessoas.ListarPessoaModel;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import br.com.lucasdev3.api.services.PessoaService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {

  @Autowired
  private final PessoaService pessoaService;

  public PessoasController(PessoaService pessoaService) {
    this.pessoaService = pessoaService;
  }

  @GetMapping
  public ResponseEntity<ResponseModel<List<ListarPessoaModel>>> buscar() {
    return ResponseEntity.ok(new ResponseModel<>(this.pessoaService.buscar()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseModel<ListarPessoaModel>> buscaPorId(@PathVariable Long id) {
    return ResponseEntity.ok(new ResponseModel<>(this.pessoaService.buscaPorId(id)));
  }

  @GetMapping("/enderecos/{pessoaId}")
  public ResponseEntity<ResponseModel<Map<String, List<Endereco>>>> buscaEnderecos(@PathVariable Long pessoaId) {
    return ResponseEntity.ok(new ResponseModel<>(this.pessoaService.buscaEnderecos(pessoaId)));
  }

  @PostMapping("/salvar")
  public ResponseEntity<ResponseModel<String>> salvar(
      @Valid @RequestBody SalvarPessoaModel salvarPessoaModel) {
    this.pessoaService.salvar(salvarPessoaModel);
    return new ResponseEntity<>(new ResponseModel<>("Pessoa criada com sucesso!"),
        HttpStatus.CREATED);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<ResponseModel<String>> atualizar(
      @Valid @RequestBody SalvarPessoaModel salvarPessoaModel,
      @PathVariable Long id) {
    this.pessoaService.atualizar(salvarPessoaModel, id);
    return new ResponseEntity<>(new ResponseModel<>("Pessoa atualizada com sucesso!"), HttpStatus.OK);
  }

  @PutMapping("/adicionar-endereco/{id}")
  public ResponseEntity<ResponseModel<String>> adicionarEndereco(
      @Valid @RequestBody SalvarEnderecoModel salvarEnderecoModel,
      @PathVariable Long id) {
    this.pessoaService.adicionarEndereco(salvarEnderecoModel, id);
    return new ResponseEntity<>(new ResponseModel<>("Endereço adicionado com sucesso!"),
        HttpStatus.OK);
  }

  @PutMapping("/definir-endereco-principal/{pessoaId}/{enderecoId}")
  public ResponseEntity<ResponseModel<String>> definirEnderecoPrincipal(@PathVariable Long pessoaId,
      @PathVariable Long enderecoId) {
    this.pessoaService.definirEnderecoPrincipal(pessoaId, enderecoId);
    return new ResponseEntity<>(new ResponseModel<>("Endereço principal definido com sucesso!"),
        HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ResponseModel<String>> deletar(@PathVariable Long id) {
    this.pessoaService.deletar(id);
    return new ResponseEntity<>(new ResponseModel<>("Pessoa deletada"), HttpStatus.OK);
  }

}
