package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

// import br.gov.sp.fatec.springbootapp.controller.View;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private SegurancaService segService;

    @JsonView(View.UsuarioResumo.class)
    @GetMapping(value = "/todos")
    public List<Usuario> buscarTodos(){
        return segService.buscarTodosUsuarios();
    }

    @JsonView(View.UsuarioResumo.class)
    @GetMapping(value = "/{id}")
    public Usuario buscarPorId(@PathVariable("id") Long id){
        return segService.buscarUsuarioPorId(id);
    }

    @JsonView(View.UsuarioResumo.class)
    @GetMapping(value = "/nome")
    public Usuario buscarPorNome(@RequestParam("nome") String nome){
        return segService.buscarUsuarioPorNome(nome);
    }

    @JsonView(View.UsuarioResumo.class)
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario, UriComponentsBuilder uriComponentsBuilder){
        usuario = segService.criarUsuario(usuario.getNome(),usuario.getSenha(), "ROLE_USUARIO");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(
            uriComponentsBuilder.path(
                "/usuario/"+ usuario.getId()).build().toUri());

        return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);
    }

    @JsonView(View.AutorizacaoResumo.class)
    @GetMapping(value = "autorizacao/{autorizacao}")
    public Autorizacao buscarAutorizacaoPorNome(@PathVariable("autorizacao") String autorizacao){
        return segService.buscarAutorizacaoPorNome(autorizacao);
    }
    
}