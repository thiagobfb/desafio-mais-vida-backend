package br.med.maisvida.desafio.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.med.maisvida.desafio.domain.Perfil;
import br.med.maisvida.desafio.repositories.PerfilRepository;
import br.med.maisvida.desafio.services.exceptions.ObjectNotFoundException;

@Service
public class PerfilService {

	@Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> listaPerfil() {
        return perfilRepository.findAll();
    }

    @SuppressWarnings("deprecation")
	public Page<Perfil> listaPaginada(int count, int page) {
        Pageable pages = new PageRequest(page, count);
        return perfilRepository.findAll(pages);
    }


    public Perfil salvarPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public void deletePerfil(String id) {
        perfilRepository.deleteById(id);
    }

    public Perfil getById(String id) {
    	Optional<Perfil> obj = perfilRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato ! Id: " + id + ", Tipo: " + Perfil.class.getName()));
    }
}
