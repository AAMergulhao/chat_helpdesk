package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Notificacao;

import java.util.LinkedList;

public interface NotificacaoService {

    public Notificacao criarNotificacao(String nomeDestinatario, String nomeRemetente, String titulo, String conteudo);

    public LinkedList<Notificacao> criarNotificacaoDupla(String nomeDestinatario1, String nomeDestinatario2, String nomeRemetente, String titulo, String conteudo);
    
}