package org.iesalandalus.programacion.alquilervehiculos.controlador;

import java.time.LocalDate;

import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class Controlador {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        if (vista == null || modelo == null) {
            throw new IllegalArgumentException("La vista y el modelo no pueden ser nulos.");
        }

        this.vista = vista;
        this.modelo = modelo;

        vista.setControlador(this);
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    public void insertarCliente(String nombre, String dni, String telefono) {
        modelo.insertar(nombre, dni, telefono);
    }

    public void modificarCliente(String dni, String nuevoNombre, String nuevoTelefono) {
        modelo.modificar(dni, nuevoNombre, nuevoTelefono);
    }

    public void eliminarCliente(String dni) {
        modelo.borrar(dni);
    }

    public void mostrarListaClientes() {
        modelo.mostrarListaClientes();
    }

    public void registrarAlquiler(String dniCliente, String codigoVehiculo,LocalDate fechaAlquiler, LocalDate FechaDevolucion) {
        modelo.registrarAlquiler(dniCliente, codigoVehiculo, fechaAlquiler, FechaDevolucion);
    }

    public void finalizarAlquiler(String codigoVehiculo) {
        modelo.finalizarAlquiler(codigoVehiculo);
    }
}

