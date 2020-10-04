package br.gov.sp.fatec.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Entity
@Table(name = "usr_notificacoes")
public class Notificacao implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "not_id")
    private Long id;

    @Column(name = "not_titulo")
    private String titulo;

    @Column(name = "not_conteudo")
    private String conteudo;

    @Column(name = "not_send_by")
    private Long send_by;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usr_id", nullable = false)
    private Usuario usuario; 


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

    public Long getSendBy() {
        return this.send_by;
    }

    public void setSendBy(Long send_by) {
        this.send_by = send_by;
    }

    
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}