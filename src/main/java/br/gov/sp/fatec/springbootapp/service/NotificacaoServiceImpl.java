package br.gov.sp.fatec.springbootapp.service;

import java.util.LinkedList;

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
        notificacao.setTitulo(titulo);
        notificacao.setConteudo(conteudo);
        notificacao.setSendBy(usuarioRemetente.getId());
        notificacao.setUsuario(usuarioDestinatario);
        notRepo.save(notificacao);
        return notificacao;
    }

    @Transactional
    public LinkedList<Notificacao> criarNotificacaoDupla(String nomeDestinatario1,String nomeDestinatario2, String nomeRemetente, String titulo, String conteudo) {
        LinkedList<Notificacao> notificacoes = new LinkedList<Notificacao>();

        Usuario usuarioDestinatario1 = usuarioRepo.buscaUsuarioPorNome(nomeDestinatario1);
        Usuario usuarioDestinatario2 = usuarioRepo.buscaUsuarioPorNome(nomeDestinatario1);
        Usuario usuarioRemetente = usuarioRepo.buscaUsuarioPorNome(nomeRemetente);

    
        Notificacao notificacao1 = new Notificacao();
        notificacao1.setTitulo(titulo);
        notificacao1.setConteudo(conteudo);
        notificacao1.setSendBy(usuarioRemetente.getId());
        notificacao1.setUsuario(usuarioDestinatario1);
        notRepo.save(notificacao1);
        notificacoes.push(notificacao1);

        Notificacao notificacao2 = new Notificacao();
        notificacao2.setTitulo(titulo);
        notificacao2.setConteudo(conteudo);
        notificacao2.setSendBy(usuarioRemetente.getId());
        notificacao2.setUsuario(usuarioDestinatario2);
        notRepo.save(notificacao2);
        notificacoes.push(notificacao2);

        return notificacoes;
    }
    
}