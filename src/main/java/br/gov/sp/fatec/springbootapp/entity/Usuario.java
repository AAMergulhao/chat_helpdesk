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
import javax.persistence.OneToMany;
import javax.persistence.Embeddable;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "usr_usuario")
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;
    
    @Column(name = "usr_nome")
    private String nome;

    @Column(name = "usr_avatar")
    private String avatar;

    @Column(name = "usr_senha")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "uau_usuario_autorizacao",
        joinColumns = {@JoinColumn(name = "usr_id")},
        inverseJoinColumns = {@JoinColumn(name = "aut_id")}
        )



    // public Usuario(String nome,String avatar, String senha){
    //     this.nome = nome;
    //     this.avatar = avatar;
    //     this.senha = senha;
    // }
    // public Usuario(){}
    
    

    private Set<Autorizacao> autorizacoes;
    
    @OneToMany(mappedBy="usuario", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Notificacao> notificacoes;

    public Long getId(){
        return  this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNome(){
        return  this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getAvatar(){
        return  this.avatar;
    }
    public void setAvatar(String avatar){
        this.avatar = avatar;
    }
    public String getSenha(){
        return  this.senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public Set<Autorizacao> getAutorizacoes(){
        return  this.autorizacoes;
    }
    public void setAutorizacoes(Set<Autorizacao> autorizacoes){
        this.autorizacoes = autorizacoes;
    }

    public Set<Notificacao> getNotificacoes(){
        return  this.notificacoes;
    }

    public void setNotificacoes(Set<Notificacao> notificacoes){
        this.notificacoes = notificacoes;
    }
    
}