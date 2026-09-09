package com.jcaa.udec;

import com.jcaa.udec.collections.adapter.persistence.memory.GuardarUsuarioAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.ObtenerUsuariosAdapter;
import com.jcaa.udec.collections.application.service.AgregarUsuarioService;
import com.jcaa.udec.collections.application.service.ObtenerUsuariosService;
import com.jcaa.udec.collections.application.service.ports.in.AgregarUsuarioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerUsuarioUseCase;
import com.jcaa.udec.collections.domain.port.out.GuardarUsuarioPort;
import com.jcaa.udec.collections.domain.port.out.ObtenerUsuariosPort;
import com.jcaa.udec.collections.entrypoint.cli.GuiCli;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControlador;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControladorImpl;

import com.jcaa.udec.soldados.adapter.persistence.memory.ActualizarSoldadoAdapter;
import com.jcaa.udec.soldados.adapter.persistence.memory.BuscarSoldadoAdapter;
import com.jcaa.udec.soldados.adapter.persistence.memory.EliminarSoldadoAdapter;
import com.jcaa.udec.soldados.adapter.persistence.memory.GuardarSoldadoAdapter;
import com.jcaa.udec.soldados.adapter.persistence.memory.ListarSoldadosAdapter;
import com.jcaa.udec.soldados.application.service.ActualizarSoldadoService;
import com.jcaa.udec.soldados.application.service.BuscarSoldadoService;
import com.jcaa.udec.soldados.application.service.CrearSoldadoService;
import com.jcaa.udec.soldados.application.service.EliminarSoldadoService;
import com.jcaa.udec.soldados.application.service.ListarSoldadosService;
import com.jcaa.udec.soldados.application.service.ports.in.ActualizarSoldadoUseCase;
import com.jcaa.udec.soldados.application.service.ports.in.BuscarSoldadoUseCase;
import com.jcaa.udec.soldados.application.service.ports.in.CrearSoldadoUseCase;
import com.jcaa.udec.soldados.application.service.ports.in.EliminarSoldadoUseCase;
import com.jcaa.udec.soldados.application.service.ports.in.ListarSoldadosUseCase;
import com.jcaa.udec.soldados.domain.port.out.ActualizarSoldadoPort;
import com.jcaa.udec.soldados.domain.port.out.BuscarSoldadoPort;
import com.jcaa.udec.soldados.domain.port.out.EliminarSoldadoPort;
import com.jcaa.udec.soldados.domain.port.out.GuardarSoldadoPort;
import com.jcaa.udec.soldados.domain.port.out.ListarSoldadosPort;
import com.jcaa.udec.soldados.entrypoint.cli.SoldadoCli;
import com.jcaa.udec.soldados.entrypoint.controller.SoldadoControlador;
import com.jcaa.udec.soldados.entrypoint.controller.SoldadoControladorImpl;

import java.util.Scanner;

public class Main {
    private static final int OPCION_USUARIOS = 1;
    private static final int OPCION_SOLDADOS = 2;
    private static final int OPCION_SALIR = 3;
    private static final String TEXTO_TITULO = "** MENU PRINCIPAL **";
    private static final String SEPARADOR = "- - - - - - - - - ";
    private static final String TEXTO_OPCION_USUARIOS = "1 - Gestion de Usuarios";
    private static final String TEXTO_OPCION_SOLDADOS = "2 - Gestion de Soldados";
    private static final String TEXTO_OPCION_SALIR = "3 - Salir";
    private static final String TEXTO_SOLICITUD_OPCION = "Ingrese el numero de la opcion: ";
    private static final String MENSAJE_OPCION_INVALIDA = "Opcion [%s] invalida";
    private static final String MENSAJE_DESPEDIDA = "Esperamos tu regreso. Bye, Bye";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        GuiCli guiCli = construirUsuarioCli(entrada);
        SoldadoCli soldadoCli = construirSoldadoCli(entrada);

        boolean continuar = true;
        while (continuar) {
            int opcion = obtenerOpcionMenu(entrada);
            switch (opcion) {
                case OPCION_USUARIOS -> guiCli.ejecutarAccion();
                case OPCION_SOLDADOS -> soldadoCli.ejecutarAccion();
                case OPCION_SALIR -> continuar = false;
            }
        }
        System.out.println(MENSAJE_DESPEDIDA);
    }

    private static int obtenerOpcionMenu(Scanner entrada) {
        do {
            System.out.println();
            System.out.println(TEXTO_TITULO);
            System.out.println(SEPARADOR);
            System.out.println(TEXTO_OPCION_USUARIOS);
            System.out.println(TEXTO_OPCION_SOLDADOS);
            System.out.println(TEXTO_OPCION_SALIR);
            System.out.print(TEXTO_SOLICITUD_OPCION);
            String valorIngresado = entrada.nextLine().trim();
            try {
                int opcion = Integer.parseInt(valorIngresado);
                if (opcion >= OPCION_USUARIOS && opcion <= OPCION_SALIR) {
                    return opcion;
                }
            } catch (NumberFormatException exception) {
                // El flujo informa el valor invalido y vuelve a mostrar el menu.
            }
            System.out.printf(MENSAJE_OPCION_INVALIDA + "%n", valorIngresado);
        } while (true);
    }

    private static GuiCli construirUsuarioCli(Scanner entrada) {
        GuardarUsuarioPort guardarUsuarioPort = new GuardarUsuarioAdapter();
        ObtenerUsuariosPort obtenerUsuariosPort = new ObtenerUsuariosAdapter();
        AgregarUsuarioUseCase agregarUsuarioUseCase = new AgregarUsuarioService(guardarUsuarioPort);
        ObtenerUsuarioUseCase obtenerUsuarioUseCase = new ObtenerUsuariosService(obtenerUsuariosPort);
        UsuarioControlador usuarioControlador =
                new UsuarioControladorImpl(agregarUsuarioUseCase, obtenerUsuarioUseCase);
        return new GuiCli(usuarioControlador, entrada);
    }

    private static SoldadoCli construirSoldadoCli(Scanner entrada) {
        GuardarSoldadoPort guardarSoldadoPort = new GuardarSoldadoAdapter();
        BuscarSoldadoPort buscarSoldadoPort = new BuscarSoldadoAdapter();
        ListarSoldadosPort listarSoldadosPort = new ListarSoldadosAdapter();
        ActualizarSoldadoPort actualizarSoldadoPort = new ActualizarSoldadoAdapter();
        EliminarSoldadoPort eliminarSoldadoPort = new EliminarSoldadoAdapter();

        CrearSoldadoUseCase crearSoldadoUseCase = new CrearSoldadoService(guardarSoldadoPort);
        BuscarSoldadoUseCase buscarSoldadoUseCase = new BuscarSoldadoService(buscarSoldadoPort);
        ListarSoldadosUseCase listarSoldadosUseCase = new ListarSoldadosService(listarSoldadosPort);
        ActualizarSoldadoUseCase actualizarSoldadoUseCase = new ActualizarSoldadoService(actualizarSoldadoPort);
        EliminarSoldadoUseCase eliminarSoldadoUseCase = new EliminarSoldadoService(eliminarSoldadoPort);

        SoldadoControlador soldadoControlador = new SoldadoControladorImpl(
                crearSoldadoUseCase,
                buscarSoldadoUseCase,
                listarSoldadosUseCase,
                actualizarSoldadoUseCase,
                eliminarSoldadoUseCase);
        return new SoldadoCli(soldadoControlador, entrada);
    }
}
