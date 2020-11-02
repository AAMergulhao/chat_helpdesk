package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Notificacao;

public interface NotificacaoService {

    public Notificacao criarNotificacao(String nomeDestinatario, String nomeRemetente, String titulo, String conteudo,
            String dataDisparo, String dataAgendada, Integer status);

    // public LinkedList<Notificacao> criarNotificacaoDupla(String
    // nomeDestinatario1, String nomeDestinatario2, String nomeRemetente, String
    // titulo, String conteudo);
    public void deletarNotificacao(Long notID);
}