package br.med.maisvida.desafio.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.med.maisvida.desafio.domain.Usuario;
import br.med.maisvida.desafio.repositories.UsuarioRepository;
import br.med.maisvida.desafio.services.exceptions.ObjectNotFoundException;

@Service("userDetailsService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("Usuário não existe!", username));
        }
        return usuario;
    }
    
    public List<Usuario> listaUsuario() {
        return usuarioRepository.findAll();
    }

    public Usuario salvarUsuario(Usuario usuarioAdd) {
        return usuarioRepository.save(usuarioAdd);
    }

    public void deleteUsuario(String id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario getById(String id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato ! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }
}
