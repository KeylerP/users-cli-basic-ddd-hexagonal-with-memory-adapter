package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.valueobject.Email;
import com.jcaa.udec.collections.domain.core.valueobject.NombreUsuario;
import com.jcaa.udec.collections.domain.core.valueobject.Password;
import com.jcaa.udec.collections.domain.core.valueobject.UsuarioId;
import lombok.Builder;

public class Usuario {
    private final UsuarioId id;
    private final Password password;
    private final NombreUsuario nombre;
    private final Email email;

    @Builder
    public Usuario(String id, String password, String nombre, String email) {
        this.id = new UsuarioId(id);
        this.password = new Password(password);
        this.nombre = new NombreUsuario(nombre);
        this.email = new Email(email);
    }

    public String getId() {
        return id.valor();
    }

    public String getNombre() {
        return nombre.valor();
    }

    public String getEmail() {
        return email.valor();
    }
}
