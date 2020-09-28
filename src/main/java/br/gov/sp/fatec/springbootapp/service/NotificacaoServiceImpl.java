package br.gov.sp.fatec.springbootapp.service;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.entity.Notificacao;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.repository.NotificacaoRepository;


@Service("NotificacaoService")
public class NotificacaoServiceImpl implements NotificacaoService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private NotificacaoRepository notRepo;

    @Transactional
    public Notificacao criarNotificacao(String nomeDestinatario, String nomeRemetente, String titulo, String conteudo) {

        Usuario usuarioDestinatario = usuarioRepo.buscaUsuarioPorNome(nomeDestinatario);
        Usuario usuarioRemetente = usuarioRepo.buscaUsuarioPorNome(nomeRemetente);
        
        Notificacao notificacao = new Notificacao();
        notificacao.setTitulo("Titulo teste");
        notificacao.setConteudo("teste teste teste");
        notificacao.setSendBy(usuarioRemetente.getId());
        notificacao.setUsuario(usuarioDestinatario);
        notRepo.save(notificacao);
        return notificacao;
    }
    
}