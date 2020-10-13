package br.gov.sp.fatec.springbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controller.View;

import java.io.Serializable;

@Entity
@Table(name = "usr_usuario")
public class Usuario implements Serializable {

    @JsonView(View.NotificacaoResumo.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @JsonView({ View.UsuarioResumo.class, View.AutorizacaoResumo.class, View.ConversaResumo.class,
            View.MensagemResumo.class })
    @Column(name = "usr_nome")
    private String nome;

    @Column(name = "usr_avatar")
    private String avatar;

    @Column(name = "usr_senha")
    private String senha;

    @JsonView(View.UsuarioResumo.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "uau_usuario_autorizacao", joinColumns = { @JoinColumn(name = "usr_id") }, inverseJoinColumns = {
            @JoinColumn(name = "aut_id") })
    private Set<Autorizacao> autorizacoes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuarios_conversas", joinColumns = { @JoinColumn(name = "usr_id") }, inverseJoinColumns = {
            @JoinColumn(name = "chat_id") })
    private Set<Conversa> conversas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_mensagens", joinColumns = { @JoinColumn(name = "usr_id") }, inverseJoinColumns = {
            @JoinColumn(name = "mensagem_id") })
    private Set<Mensagem> mensagens;

    @OneToOne(mappedBy = "destinatario")
    private Mensagem mensagem;
    
    @JsonView(View.NotificacaoResumo.class)
    @OneToMany(mappedBy = "notRemetente",fetch = FetchType.LAZY)
    private Set<Notificacao> notificacoesEnviadas;

    @OneToMany(mappedBy = "notRemetente", fetch = FetchType.LAZY)
    private Set<Notificacao> notificacoesRecebidas;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Autorizacao> getAutorizacoes() {
        return this.autorizacoes;
    }

    public void setAutorizacoes(Set<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }


    public Set<Conversa> getConversas() {
        return conversas;
    }

    public void setConversas(Set<Conversa> conversas) {
        this.conversas = conversas;
    }

    public Set<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(Set<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Set<Notificacao> getNotificacoesEnviadas() {
        return notificacoesEnviadas;
    }

    public void setNotificacoesEnviadas(Set<Notificacao> notificacoesEnviadas) {
        this.notificacoesEnviadas = notificacoesEnviadas;
    }

    public Set<Notificacao> getNotificacoesRecebidas() {
        return notificacoesRecebidas;
    }

    public void setNotificacoesRecebidas(Set<Notificacao> notificacoesRecebidas) {
        this.notificacoesRecebidas = notificacoesRecebidas;
    }

}