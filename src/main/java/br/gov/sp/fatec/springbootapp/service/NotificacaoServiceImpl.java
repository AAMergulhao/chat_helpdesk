package br.gov.sp.fatec.springbootapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.exception.RegistroNaoEncontradoException;
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
    public Notificacao criarNotificacao(String nomeDestinatario, String nomeRemetente, String titulo, String conteudo,
            String dataDisparo, String dataAgendada, Integer status) {

        Usuario usuarioDestinatario = usuarioRepo.buscaUsuarioPorNome(nomeDestinatario);
        Usuario usuarioRemetente = usuarioRepo.buscaUsuarioPorNome(nomeRemetente);

        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        
        Notificacao notificacao = new Notificacao();
        notificacao.setTitulo(titulo);
        notificacao.setConteudo(conteudo);
        notificacao.setNotRemetente(usuarioRemetente);
        notificacao.setNotDestinatario(usuarioDestinatario);

        try {
            notificacao.setDataDisparo(formatoData.parse(dataDisparo));
            notificacao.setDataLimite(formatoData.parse(dataAgendada));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        notificacao.setStatus(status);
        notRepo.save(notificacao);
        return notificacao;
    }

    @Override
    @Transactional
    public void deletarNotificacao(Long notID) {
        notRepo.deleteById(notID);
    }

    @Override
    @Transactional
    public Notificacao atualizarStatusNotificacao(Long notID, Integer status) {

        Optional<Notificacao> notOp = notRepo.findById(notID);

        if(notOp.isPresent()){
            Notificacao notificacao = notOp.get();

            notificacao.setStatus(status);
            notRepo.save(notificacao);
            return notificacao;
        
        }
        throw new RegistroNaoEncontradoException("Atividade não encontrada");
    }
    
}