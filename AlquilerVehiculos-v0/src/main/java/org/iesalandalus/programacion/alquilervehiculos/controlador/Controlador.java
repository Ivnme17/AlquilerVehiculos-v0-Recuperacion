package org.iesalandalus.programacion.alquilervehiculos.controlador;

import java.time.LocalDate;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class Controlador {
	private Vista vista;
	private Modelo modelo;

	public Controlador(Vista vista, Modelo modelo) {
		if (vista == null) {
			throw new IllegalArgumentException("La vista no puede ser nula.");
		}
		if (modelo == null) {
			throw new IllegalArgumentException("El modelo no puede ser nulo.");
		}
		this.vista = vista;
		this.modelo = modelo;
		this.vista.setControlador(this);
	}

	public void comenzar() {
		modelo.comenzar();
		vista.comenzar();
	}

	public void terminar() {
		modelo.terminar();
		vista.terminar();
	}

	public void registrarCliente(String nombre, String dni, String telefono) {
	    Cliente cliente = new Cliente(nombre, dni, telefono);
	    modelo.insertarCliente(cliente);
	}

	public void modificarCliente(String dni, String nuevoNombre, String nuevoTelefono) {
		modelo.modificarCliente(dni, nuevoNombre, nuevoTelefono);
	}

	public void eliminarCliente(String dni) {
		modelo.borrarCliente(dni);
	}

	public List<Cliente> getListaClientes() {
		return modelo.getClientes();
	}

	public void registrarAlquiler(String dniCliente, String matricula) {
	    Cliente cliente = modelo.buscarCliente(dniCliente);
	    Turismo turismo = modelo.buscarTurismo(matricula);
	    if (cliente != null && turismo != null) {
	        Alquiler alquiler = new Alquiler(cliente, turismo, LocalDate.now(), null);
	        modelo.abrirAlquiler(alquiler);
	    }
	}

	public void finalizarAlquiler(String dniCliente, String matricula) {
	    Cliente cliente = modelo.buscarCliente(dniCliente);
	    Turismo turismo = modelo.buscarTurismo(matricula);
	    if (cliente != null && turismo != null) {
	        Alquiler alquiler = new Alquiler(cliente, turismo, null, null);
	        modelo.cerrarAlquiler(alquiler);
	    }
	}
}