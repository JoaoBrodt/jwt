package com.example.demo.services;

import com.example.demo.data.DetalherUsuarioData;
import com.example.demo.model.UsuarioModel;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;

    public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuario = repository.findByUsername(username);
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
        }

        return new DetalherUsuarioData(usuario);
    }
}
