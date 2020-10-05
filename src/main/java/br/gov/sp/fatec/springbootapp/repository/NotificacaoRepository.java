package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Notificacao;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    

    @Query("select n from Notificacao n where n.user_id = ?1")
    public List<Notificacao> buscaNotificacoesPorUsuario(Long user_id);
}