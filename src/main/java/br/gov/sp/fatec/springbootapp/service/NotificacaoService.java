package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Notificacao;

public interface NotificacaoService {

    public Notificacao criarNotificacao(String nomeDestinatario, String nomeRemetente, String titulo, String conteudo);
    
}