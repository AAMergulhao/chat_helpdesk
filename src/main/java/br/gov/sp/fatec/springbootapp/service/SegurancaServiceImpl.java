package br.gov.sp.fatec.springbootapp.service;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;


@Service("SegurancaService")
public class SegurancaServiceImpl implements SegurancaService {
    
    @Autowired
    private AutorizacaoRepository autRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Transactional
    public Usuario criarUsuario(String nome, String senha, String avatar, String autorizacao) {

        Autorizacao aut = autRepo.findByNome(autorizacao);
        if(aut == null){
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autRepo.save(aut);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setAvatar(avatar);
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        return usuario;
    }
    
}