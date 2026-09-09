package com.jcaa.udec.soldados.entrypoint.cli;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoInvalidoException;
import com.jcaa.udec.soldados.domain.core.exception.SoldadoNoExisteException;
import com.jcaa.udec.soldados.domain.core.exception.SoldadoYaExisteException;
import com.jcaa.udec.soldados.domain.core.valueobject.ComponenteMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.NombreCompleto;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import com.jcaa.udec.soldados.domain.core.valueobject.SoldadoId;
import com.jcaa.udec.soldados.entrypoint.controller.SoldadoControlador;
import com.jcaa.udec.soldados.entrypoint.controller.dto.request.ActualizarSoldadoPeticion;
import com.jcaa.udec.soldados.entrypoint.controller.dto.request.RegistrarSoldadoPeticion;
import com.jcaa.udec.soldados.entrypoint.controller.dto.response.ObtenerSoldadoResponse;

import java.util.Scanner;

public class SoldadoCli {
    private static final int OPCION_CREAR = 1;
    private static final int OPCION_BUSCAR = 2;
    private static final int OPCION_LISTAR = 3;
    private static final int OPCION_ACTUALIZAR = 4;
    private static final int OPCION_ELIMINAR = 5;
    private static final int OPCION_VOLVER = 6;
    private static final String TEXTO_TITULO = "** GESTION DE SOLDADOS **";
    private static final String TITULO_REGISTRO = "** INGRESE LOS DATOS DEL NUEVO SOLDADO **";
    private static final String TITULO_ACTUALIZACION = "** INGRESE LOS NUEVOS DATOS DEL SOLDADO **";
    private static final String SEPARADOR = "- - - - - - - - - ";
    private static final String OPCIONES = "Opciones:";
    private static final String TEXTO_OPCION_CREAR = "1 - Crear";
    private static final String TEXTO_OPCION_BUSCAR = "2 - Buscar por Id";
    private static final String TEXTO_OPCION_LISTAR = "3 - Listar todos";
    private static final String TEXTO_OPCION_ACTUALIZAR = "4 - Actualizar";
    private static final String TEXTO_OPCION_ELIMINAR = "5 - Eliminar";
    private static final String TEXTO_OPCION_VOLVER = "6 - Volver al menu principal";
    private static final String TEXTO_SOLICITUD_OPCION = "Ingrese el numero de la opcion: ";
    private static final String SOLICITUD_ID = "ID (documento de identidad): ";
    private static final String SOLICITUD_NOMBRE = "NOMBRE COMPLETO: ";
    private static final String SOLICITUD_RANGO = "RANGO: ";
    private static final String SOLICITUD_COMPONENTE = "COMPONENTE MILITAR: ";
    private static final String MENSAJE_OPCION_INVALIDA = "Opcion [%s] invalida";
    private static final String MENSAJE_ID_INVALIDO = "ID INVALIDO: debe ser un numero de al menos 6 digitos";
    private static final String MENSAJE_NOMBRE_INVALIDO = "NOMBRE INVALIDO: minimo 3 caracteres";
    private static final String MENSAJE_RANGO_INVALIDO = "RANGO INVALIDO: minimo 2 caracteres";
    private static final String MENSAJE_COMPONENTE_INVALIDO = "COMPONENTE INVALIDO: minimo 2 caracteres";
    private static final String MENSAJE_ERROR = "ERROR: ";
    private static final String MENSAJE_REGISTRO_EXITOSO = "Soldado registrado correctamente.";
    private static final String MENSAJE_ACTUALIZACION_EXITOSA = "Soldado actualizado correctamente.";
    private static final String MENSAJE_ELIMINACION_EXITOSA = "Soldado eliminado correctamente.";
    private static final String MENSAJE_LISTA_VACIA = "No hay soldados registrados.";
    private static final String MARCA_ORDEN_BYTES = "\uFEFF";
    private static final String TEXTO_VACIO = "";
    private final SoldadoControlador soldadoControlador;
    private final Scanner entrada;

    public SoldadoCli(SoldadoControlador soldadoControlador) {
        this(soldadoControlador, new Scanner(System.in));
    }

    public SoldadoCli(SoldadoControlador soldadoControlador, Scanner entrada) {
        this.soldadoControlador = soldadoControlador;
        this.entrada = entrada;
    }

    public void ejecutarAccion() {
        boolean continuar = true;
        while (continuar) {
            int opcion = obtenerOpcionMenu();
            try {
                switch (opcion) {
                    case OPCION_CREAR -> crearSoldado();
                    case OPCION_BUSCAR -> mostrarSoldadoPorId();
                    case OPCION_LISTAR -> mostrarTodosLosSoldados();
                    case OPCION_ACTUALIZAR -> actualizarSoldado();
                    case OPCION_ELIMINAR -> eliminarSoldado();
                    case OPCION_VOLVER -> continuar = false;
                }
            } catch (SoldadoInvalidoException
                    | SoldadoNoExisteException
                    | SoldadoYaExisteException exception) {
                System.out.println(MENSAJE_ERROR + exception.getMessage());
            }
        }
    }

    private int obtenerOpcionMenu() {
        do {
            mostrarMenu();
            String valorIngresado = limpiarEntrada(entrada.nextLine());
            try {
                int opcion = Integer.parseInt(valorIngresado);
                if (opcion >= OPCION_CREAR && opcion <= OPCION_VOLVER) {
                    return opcion;
                }
            } catch (NumberFormatException exception) {
                // El flujo informa el valor invalido y vuelve a mostrar el menu.
            }
            System.out.printf(MENSAJE_OPCION_INVALIDA + "%n", valorIngresado);
        } while (true);
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println(TEXTO_TITULO);
        System.out.println(SEPARADOR);
        System.out.println(OPCIONES);
        System.out.println(SEPARADOR);
        System.out.println(TEXTO_OPCION_CREAR);
        System.out.println(TEXTO_OPCION_BUSCAR);
        System.out.println(TEXTO_OPCION_LISTAR);
        System.out.println(TEXTO_OPCION_ACTUALIZAR);
        System.out.println(TEXTO_OPCION_ELIMINAR);
        System.out.println(TEXTO_OPCION_VOLVER);
        System.out.print(TEXTO_SOLICITUD_OPCION);
    }

    private void crearSoldado() {
        soldadoControlador.registrar(capturarDatosSoldado());
        System.out.println(MENSAJE_REGISTRO_EXITOSO);
    }

    private void mostrarSoldadoPorId() {
        System.out.println(soldadoControlador.obtenerPorId(capturarId()));
    }

    private void mostrarTodosLosSoldados() {
        ObtenerSoldadoResponse response = soldadoControlador.obtenerTodos();
        if (response.estaVacia()) {
            System.out.println(MENSAJE_LISTA_VACIA);
            return;
        }
        System.out.println(response);
    }

    private void actualizarSoldado() {
        System.out.println();
        System.out.println(TITULO_ACTUALIZACION);
        String id = capturarId();
        String nombre = capturarNombre();
        String rango = capturarRango();
        String componente = capturarComponente();
        soldadoControlador.actualizar(new ActualizarSoldadoPeticion(id, nombre, rango, componente));
        System.out.println(MENSAJE_ACTUALIZACION_EXITOSA);
    }

    private void eliminarSoldado() {
        soldadoControlador.eliminar(capturarId());
        System.out.println(MENSAJE_ELIMINACION_EXITOSA);
    }

    private RegistrarSoldadoPeticion capturarDatosSoldado() {
        System.out.println();
        System.out.println(TITULO_REGISTRO);
        return new RegistrarSoldadoPeticion(
                capturarId(), capturarNombre(), capturarRango(), capturarComponente());
    }

    private String capturarId() {
        do {
            System.out.print(SOLICITUD_ID);
            String id = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new SoldadoId(id))) {
                return id;
            }
            System.out.println(MENSAJE_ID_INVALIDO);
        } while (true);
    }

    private String capturarNombre() {
        do {
            System.out.print(SOLICITUD_NOMBRE);
            String nombre = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new NombreCompleto(nombre))) {
                return nombre;
            }
            System.out.println(MENSAJE_NOMBRE_INVALIDO);
        } while (true);
    }

    private String capturarRango() {
        do {
            System.out.print(SOLICITUD_RANGO);
            String rango = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new Rango(rango))) {
                return rango;
            }
            System.out.println(MENSAJE_RANGO_INVALIDO);
        } while (true);
    }

    private String capturarComponente() {
        do {
            System.out.print(SOLICITUD_COMPONENTE);
            String componente = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new ComponenteMilitar(componente))) {
                return componente;
            }
            System.out.println(MENSAJE_COMPONENTE_INVALIDO);
        } while (true);
    }

    private static String limpiarEntrada(String valor) {
        return valor.replace(MARCA_ORDEN_BYTES, TEXTO_VACIO).trim();
    }

    private static boolean esValido(Runnable validacion) {
        try {
            validacion.run();
            return true;
        } catch (SoldadoInvalidoException exception) {
            return false;
        }
    }
}
