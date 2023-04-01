package br.com.lucasdev3.api.controllers;

import br.com.lucasdev3.api.models.ResponseModel;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import br.com.lucasdev3.api.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<ResponseModel> buscar() {
    return ResponseEntity.ok(new ResponseModel(this.pessoaService.buscar()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseModel> buscar(@PathVariable Long id) {
    return ResponseEntity.ok(new ResponseModel(this.pessoaService.buscaPorId(id)));
  }

  @PostMapping("/salvar")
  public ResponseEntity<ResponseModel> salvar(@RequestBody SalvarPessoaModel salvarPessoaModel) {
    this.pessoaService.salvar(salvarPessoaModel);
    return new ResponseEntity<>(new ResponseModel("Pessoa criada com sucesso!"),
        HttpStatus.CREATED);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<ResponseModel> atualizar(@RequestBody SalvarPessoaModel salvarPessoaModel,
      @PathVariable Long id) {
    this.pessoaService.atualizar(salvarPessoaModel, id);
    return new ResponseEntity<>(new ResponseModel("Pessoa atualizada com sucesso!"), HttpStatus.OK);
  }

  @PutMapping("/adicionar-endereco/{id}")
  public ResponseEntity<ResponseModel> atualizar(
      @RequestBody SalvarEnderecoModel salvarEnderecoModel, @PathVariable Long id) {
    this.pessoaService.adicionarEndereco(salvarEnderecoModel, id);
    return new ResponseEntity<>(new ResponseModel("Endereço adicionado com sucesso!"),
        HttpStatus.OK);
  }

}
