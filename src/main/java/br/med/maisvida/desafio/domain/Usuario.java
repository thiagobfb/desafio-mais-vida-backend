package br.med.maisvida.desafio.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = {"username", "senha", "habilitado", "perfis"})
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 8200737693180571139L;

    @Id
    private String id;

    private String username;
    private String senha;
    private Boolean habilitado;

    @DBRef
    private List<Perfil> perfis;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getPerfis();
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public boolean isAccountNonExpired() {
        return getHabilitado();
    }

    @Override
    public boolean isAccountNonLocked() {
        return getHabilitado();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getHabilitado();
    }

    @Override
    public boolean isEnabled() {
        return getHabilitado();
    }

}
