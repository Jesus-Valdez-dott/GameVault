package com.example.GameVault.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.GameVault.model.Usuario;
import com.example.GameVault.repository.UsuarioRepository;

@Service
public class usarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario obtenerUsuarioLogueado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            String email = auth.getName();
            return usuarioRepository.findByCorreo(email).orElse(null);
        }
        return usuarioRepository.findAll().stream().findFirst().orElse(null);
    }
}
