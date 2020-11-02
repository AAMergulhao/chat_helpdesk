package br.gov.sp.fatec.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controller.View;

import javax.persistence.JoinColumn;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "usr_notificacoes")
public class Notificacao implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonView(View.NotificacaoResumo.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "not_id")
    private Long id;

    @JsonView(View.NotificacaoResumo.class)
    @Column(name = "not_titulo")
    private String titulo;

    @JsonView(View.NotificacaoResumo.class)
    @Column(name = "not_conteudo")
    private String conteudo;

    @JsonView(View.NotificacaoResumo.class)
    @Column(name = "not_data_disparo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDisparo;
    
    @JsonView(View.NotificacaoResumo.class)
    @Column(name = "not_data_limite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLimite;

    @JsonView(View.NotificacaoResumo.class)
    @Column(name = "not_status")
    private Integer status;

    @ManyToOne
    @JsonView(View.NotificacaoResumo.class)
    @JoinColumn(name = "not_remetente_id")
    private Usuario notRemetente;
    
    @ManyToOne
    @JoinColumn(name = "not_destinatario_id")
    private Usuario notDestinatario;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getNotRemetente() {
        return notRemetente;
    }

    public void setNotRemetente(Usuario notRemetente) {
        this.notRemetente = notRemetente;
    }

    public Usuario getNotDestinatario() {
        return notDestinatario;
    }

    public void setNotDestinatario(Usuario notDestinatario) {
        this.notDestinatario = notDestinatario;
    }

    public Date getDataDisparo() {
        return dataDisparo;
    }

    public void setDataDisparo(Date dataDisparo) {
        this.dataDisparo = dataDisparo;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}