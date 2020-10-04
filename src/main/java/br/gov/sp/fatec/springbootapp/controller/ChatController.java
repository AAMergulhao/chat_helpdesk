package br.gov.sp.fatec.springbootapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springbootapp.entity.Conversa;
import br.gov.sp.fatec.springbootapp.entity.Mensagem;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.MensagemRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.service.ChatService;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    @Autowired
    UsuarioRepository usuarioRepo;

    @Autowired
    ChatService chatService;

    @JsonView(View.ConversaResumo.class)
    @PostMapping(value = "/iniciarConversa")
    public Conversa iniciarConversa(@RequestBody ObjectNode body) {
        String nomeRemetente = body.get("nomeRemetente").asText();
        String nomeDestinatario = body.get("nomeDestinatario").asText();
        
        Conversa conversa = chatService.iniciarConversa(nomeRemetente, nomeDestinatario, body.get("dataHora").asText(), body.get("conteudo").asText());
        return conversa;
    }

    @JsonView(View.MensagemResumo.class)
    @PostMapping(value = "/enviarMensagem")
    public Mensagem envarMensagem(@RequestBody ObjectNode body) {
        String nomeRemetente = body.get("nomeRemetente").asText();
        String nomeDestinatario = body.get("nomeDestinatario").asText();
        
        Mensagem mensagem = chatService.enviarMensagem(body.get("chatID").asLong(), nomeRemetente, nomeDestinatario, body.get("dataHora").asText(),body.get("conteudo").asText());
        return mensagem;
    }
}
