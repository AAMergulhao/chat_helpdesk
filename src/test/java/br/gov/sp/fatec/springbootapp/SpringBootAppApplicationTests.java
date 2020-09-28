package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.entity.Notificacao;
import br.gov.sp.fatec.springbootapp.entity.Conversa;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.repository.NotificacaoRepository;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;
import br.gov.sp.fatec.springbootapp.repository.ConversasRepository;



@SpringBootTest //inicia a aplicacao
//sem nenhuma gravacao usamos transactional e rollback
@Transactional //cada metodo tem uma conexao aberta alem da transacao
@Rollback

class SpringBootAppApplicationTests {
    
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Autowired
    private AutorizacaoRepository autRepo; 

    @Autowired
    private SegurancaService segService;

    @Autowired
    private NotificacaoRepository notRepo;

    @Autowired
    private ConversasRepository conversaRepo;

    
    
    @Test
    void testeBuscarConversasPorIdUsuario(){
        List<Conversa> conversas = conversaRepo.buscaConversasPorUsuario(1L);

        assertFalse(conversas.isEmpty());
    }

    @Test
    void testeCriarConversa(){
        Conversa conversa = new Conversa();
        conversa.setStatus(0L);
        conversa.setOrigem("painel");
        conversa.setConteudo("String json");
        conversa.setData(new Date(2020,11,2));
        conversa.setInicio(new Time(21,11,2));
        conversa.setFim(new Time(21,11,2));
        conversa.setPartipante1Id(1L);
        conversa.setPartipante2Id(2L);
        conversaRepo.save(conversa);
        assertNotNull(conversa.getId());
    }

    @Test
    void testeBuscarConversasVistaDeChat(){
        List<Conversa> conversas = conversaRepo.buscaConversasClienteAtivas();

        assertFalse(conversas.isEmpty());
    }

    @Test
    void testeBuscarConversasHistorico(){
        List<Conversa> conversas = conversaRepo.buscaConversasHistorico();

        assertFalse(conversas.isEmpty());
    }

    @Test
    void testeCriarNotificacao(){
        Usuario usuario = new Usuario();
        usuario.setNome("Teste23");
        usuario.setSenha("senha");
        usuario.setAvatar("base64 image");
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_USUARIO");
        autRepo.save(aut);
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        Notificacao notificacao = new Notificacao();
        notificacao.setTitulo("Titulo teste");
        notificacao.setConteudo("teste teste teste");
        notificacao.setSendBy(2L);
        notificacao.setUsuario(usuario);
        notRepo.save(notificacao);
        assertNotNull(notificacao.getId());
    }
    
    // @Test
    // void testeListarNotificacoesPorUsuario(){
    //     List<Notificacao> notificacoes = usuarioRepo.buscaNotificacoesPorUsuario(1L);
    //     assertFalse(notificacoes.isEmpty());
    // }

    @Test
    void testaInsercao(){
        Usuario usuario = new  Usuario();
        usuario.setNome("Teste23");
        usuario.setSenha("senha");
        usuario.setAvatar("base64 image");
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_USUARIO");
        autRepo.save(aut);
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        assertNotNull(usuario.getAutorizacoes().iterator().next().getId());
    } 
    //autorizacoes devem ser previamente salvas no banco ou referencia alguma existente
    @Test
    void testaInsercaoAutorizacao(){
        Usuario usuario = new  Usuario();
        usuario.setNome("Usuario2");
        usuario.setSenha("senha");
        usuario.setAvatar("base64 image");
        usuarioRepo.save(usuario);
        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_USUARIO2");
        aut.setUsuarios(new HashSet<Usuario>());
        aut.getUsuarios().add(usuario);
        autRepo.save(aut); 
        assertNotNull(aut.getUsuarios().iterator().next().getId());
    } 
    
       
    @Test
    void testaAutorizacao(){
        Usuario usuario = usuarioRepo.findById(1L).get();
        assertEquals("ROLE_ADMIN", usuario.getAutorizacoes().iterator().next().getNome());


    }
    @Test 
    void testaUsuario(){
        Autorizacao aut = autRepo.findById(1L).get();
        assertEquals("Fabiola", aut.getUsuarios().iterator().next().getNome());
    } 

    @Test 
    void testaBuscaUsuarioNomeContains(){
        List<Usuario> usuarios = usuarioRepo.findByNomeContainsIgnoreCase("A");
        assertFalse(usuarios.isEmpty());
    }
    @Test 
    void testaBuscaUsuarioNome(){
        Usuario usuario = usuarioRepo.findByNome("Fabiola");
        assertNotNull(usuario);
    }
     @Test 
    void testaBuscaUsuarioNomeQuery(){
        Usuario usuario = usuarioRepo.buscaUsuarioPorNome("Fabiola");
        assertNotNull(usuario);
    }

    @Test
    void testaBuscaUsuarioNomeSenha(){
        Usuario usuario = usuarioRepo.findByNomeAndSenha("Fabiola", "12345"); 
        assertNotNull(usuario);   
    }
    @Test
    void testaBuscaUsuarioNomeSenhaQuery(){
        Usuario usuario = usuarioRepo.buscaUsuaioPorNomeESenha("Fabiola", "12345"); 
        assertNotNull(usuario);   
    }
    @Test 
    void testaBuscaUsuarioNomeAutorizacao(){
        List<Usuario> usuarios = usuarioRepo.findByAutorizacoesNome("ROLE_ADMIN");
        assertFalse(usuarios.isEmpty());
    }
     @Test 
    void testaBuscaUsuarioNomeAutorizacaoQuery(){
        List<Usuario> usuarios = usuarioRepo.buscaPorNomeAutorizacao("ROLE_ADMIN");
        assertFalse(usuarios.isEmpty());
    }

    @Test
    void testaServicoCriaUsuario(){
        Usuario usuario = segService.criarUsuario("normal", "senha123","base64 image", "ROLE_USUARIO");
        assertNotNull(usuario);
    }

}

