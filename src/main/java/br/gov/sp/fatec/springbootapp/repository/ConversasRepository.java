package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Conversa;

public interface ConversasRepository extends JpaRepository<Conversa, Long> {
    
    
    @Query("select c from Conversa c where c.participante1_id = ?1 OR c.participante2_id = ?1")
    public List<Conversa> buscaConversasPorUsuario(Long user_id);

    //Aba Vista de CHat
    @Query("select c from Conversa c where origem='cliente' AND status=0")
    public List<Conversa> buscaConversasClienteAtivas();

    //Aba Conversas
    @Query("select c from Conversa c where origem='painel' AND status=3")
    public List<Conversa> buscaConversasPainel();

    //Aba Vista de chat
    @Query("select c from Conversa c where origem='cliente' AND status=0")
    public List<Conversa> buscaConversasVistaDeChat();

    //Aba Historico
    @Query("select c from Conversa c where origem='cliente' AND status=1")
    public List<Conversa> buscaConversasHistorico();
}