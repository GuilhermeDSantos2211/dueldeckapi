package com.dueldeckapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busque o usuário pelo username (ou email) no repositório
        // Exemplo:
        // Usuario usuario = usuarioRepository.findByEmail(username)
        //         .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        //
        // Converta seu objeto Usuario para um objeto que implementa UserDetails.
        // Uma abordagem comum é retornar uma instância de org.springframework.security.core.userdetails.User

        // Exemplo simples:
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password("senhaCodificada")
                .build();
    }
}
