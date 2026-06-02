package com.jcaa.udec;

import com.jcaa.udec.collections.adapter.persistence.memory.GuardarUsuarioAdapter;
import com.jcaa.udec.collections.application.service.AgregarUsuarioService;
import com.jcaa.udec.collections.application.service.ports.in.AgregarUsuarioUseCase;
import com.jcaa.udec.collections.domain.port.out.GuardarUsuarioPort;
import com.jcaa.udec.collections.entrypoint.cli.GuiCli;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControlador;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControladorImpl;

public class Main {
    public static void main(String[] args) {
        GuardarUsuarioPort guardarUsuarioPort = new GuardarUsuarioAdapter();
        AgregarUsuarioUseCase agregarUsuarioUseCase = new AgregarUsuarioService(guardarUsuarioPort);
        UsuarioControlador usuarioControlador = new UsuarioControladorImpl(agregarUsuarioUseCase);
        GuiCli guiCli = new GuiCli(usuarioControlador);
        guiCli.ejecutarAccion();
    }
}
