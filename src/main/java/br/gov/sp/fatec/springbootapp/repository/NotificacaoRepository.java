package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Notificacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
        List<Notificacao> findByUsuario(Usuario Usuario);

}