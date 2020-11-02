package br.gov.sp.fatec.springbootapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.springbootapp.entity.Notificacao;
import br.gov.sp.fatec.springbootapp.service.NotificacaoService;

@RestController
@RequestMapping(value = "/not")
@CrossOrigin
public class NotificacaoController {

    @Autowired
    private NotificacaoService notService;

    @JsonView({ View.NotificacaoResumo.class })
    @PostMapping
    public ResponseEntity<Notificacao> criarNotificacao(@RequestBody ObjectNode body,
            UriComponentsBuilder uriComponentsBuilder) {
        String nomeRemetente = body.get("nomeRemetente").asText();
        String nomeDestinatario = body.get("nomeDestinatario").asText();

        Notificacao notificacao = notService.criarNotificacao(nomeRemetente, nomeDestinatario,
                body.get("titulo").asText(), body.get("conteudo").asText(), body.get("dataDisparo").asText(),
                body.get("dataAgendada").asText(), body.get("status").asInt());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uriComponentsBuilder.path("/not/" + notificacao.getId()).build().toUri());

        return new ResponseEntity<Notificacao>(notificacao, responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping
    public void apagarMensagem(@RequestBody Notificacao notificacao) {
        notService.deletarNotificacao(notificacao.getId());

    }

    @PostMapping(value = "/deletar")
    public void deletarNotificacao(@RequestBody Notificacao notificacao) {
        notService.deletarNotificacao(notificacao.getId());
    }

    @PostMapping(value = "/alterarStatus")
    public Notificacao atualizarStatus(@RequestBody ObjectNode body) {
        return notService.atualizarStatusNotificacao(body.get("id").asLong(), body.get("status").asInt());
    }
}
