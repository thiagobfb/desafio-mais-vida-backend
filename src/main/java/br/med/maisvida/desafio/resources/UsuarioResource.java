package br.med.maisvida.desafio.resources;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.med.maisvida.desafio.domain.Usuario;
import br.med.maisvida.desafio.repositories.UsuarioRepository;
import br.med.maisvida.desafio.services.impl.UsuarioService;

@RestController
public class UsuarioResource {

	@Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository repository;

    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public List<Usuario> listar() {
        return this.usuarioService.listaUsuario();
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    public Usuario getById(@PathVariable String id) {
        return this.usuarioService.getById(id);
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.POST)
    public Usuario salvar(@RequestBody Usuario usuario) {
        return this.usuarioService.salvarUsuario(usuario);
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.PUT)
    public Usuario editar(@RequestBody Usuario usuario) {
        return this.usuarioService.salvarUsuario(usuario);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public void deletar(@PathVariable String id) {
        this.usuarioService.deleteUsuario(id);
    }

    @RequestMapping(value = "/usuario/logado", method = RequestMethod.GET)
    @ResponseBody
    public Usuario currentUserName(Principal principal) {
        Usuario usuario = this.repository.findByUsername(principal.getName());
        usuario.setSenha("");
        return usuario;
    }
}
