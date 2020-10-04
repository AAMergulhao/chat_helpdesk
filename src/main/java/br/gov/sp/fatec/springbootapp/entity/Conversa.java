package br.gov.sp.fatec.springbootapp.entity;


import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conversas")
public class Conversa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Column(name = "status")
    private Long status;

    @Column(name = "origem")
    private String origem;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "data")
    private Date data;

    @Column(name = "inicio")
    private Time inicio;

    @Column(name = "final")
    private Time fim;

    @Column(name = "participante1_id")
    private Long participante1_id;

    @Column(name = "participante2_id")
    private Long participante2_id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatus() {
        return this.status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getOrigem() {
        return this.origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getPartipante1Id() {
        return this.participante1_id;
    }

    public void setPartipante1Id(Long participante1_id) {
        this.participante1_id = participante1_id;
    }

    public Long getPartipante2Id() {
        return this.participante1_id;
    }
    public void setPartipante2Id(Long participante2_id) {
        this.participante2_id = participante2_id;
    }

    public Date getData() {
        return this.data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    public Time getInicio() {
        return this.inicio;
    }

    public void setInicio(Time inicio) {
        this.inicio = inicio;
    }

    public Time getFim() {
        return this.fim;
    }

    public void setFim(Time fim) {
        this.fim = fim;
    }
}