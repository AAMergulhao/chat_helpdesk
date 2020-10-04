package br.gov.sp.fatec.springbootapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.springbootapp.entity.Conversa;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {
    

}