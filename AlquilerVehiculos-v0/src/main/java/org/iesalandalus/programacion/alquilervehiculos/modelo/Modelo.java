package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Modelo {
	private List<Cliente> clientes;
	private List<Turismo> turismos;
	private List<Alquiler> alquileres;

	public Modelo() {
		this.clientes = new ArrayList<>();
		this.turismos = new ArrayList<>();
		this.alquileres = new ArrayList<>();
	}

	public void comenzar() {
		Cliente cliente1 = new Cliente("Bob Esponja", "11223344B", "950112233");
		insertarCliente(cliente1);

		Turismo turismo1 = new Turismo("Seat", "León", 90, "1234BCD");
		insertarTurismo(turismo1);

		LocalDate fechaAlquiler1 = LocalDate.now();
		LocalDate fechaDevolucion1 = fechaAlquiler1.plus(5, ChronoUnit.DAYS);
		Alquiler alquiler1 = new Alquiler(cliente1, turismo1, fechaAlquiler1, fechaDevolucion1);
		abrirAlquiler(alquiler1);

		System.out.println("El modelo ha comenzado.");
	}

	public void terminar() {
		System.out.println("El modelo ha terminado.");
	}

	public void insertarCliente(Cliente cliente) {
	    clientes.add(new Cliente(cliente.getNombre(), cliente.getDni(), cliente.getTelefono()));
	}

	public void insertarTurismo(Turismo turismo) {
		turismos.add(new Turismo(turismo.getMarca(), turismo.getModelo(), turismo.getPrecioPorDia(),
				turismo.getMatricula()));
	}

	public void abrirAlquiler(Alquiler alquiler) {
	    Cliente cliente = buscarCliente(alquiler.getCliente().getDni());
	    Turismo turismo = buscarTurismo(alquiler.getTurismo().getMatricula());
	    if (cliente != null && turismo != null) {
	        alquileres.add(new Alquiler(cliente, turismo, alquiler.getFechaAlquiler(), null));
	    }
	}

	public void cerrarAlquiler(Alquiler alquiler) {
	    for (Alquiler a : alquileres) {
	        if (a.getCliente().equals(alquiler.getCliente()) && a.getTurismo().equals(alquiler.getTurismo())
	                && a.getFechaAlquiler().equals(alquiler.getFechaAlquiler()) && a.getFechaDevolucion() == null) {
	            a.setFechaDevolucion(LocalDate.now());
	            break;
	        }
	    }
	}

	public Cliente buscarCliente(String dni) {
		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(dni)) {
				return new Cliente(cliente.getNombre(), cliente.getDni(), cliente.getTelefono());
			}
		}
		return null;
	}

	public Turismo buscarTurismo(String matricula) {
		for (Turismo turismo : turismos) {
			if (turismo.getMatricula().equals(matricula)) {
				return new Turismo(turismo.getMarca(), turismo.getModelo(), turismo.getPrecioPorDia(),
						turismo.getMatricula());
			}
		}
		return null;
	}

	public void modificarCliente(String dni, String nuevoNombre, String nuevoTelefono) {
		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(dni)) {
				cliente.setNombre(nuevoNombre);
				cliente.setTelefono(nuevoTelefono);
				break;
			}
		}
	}

	public void borrarCliente(String dni) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDni().equals(dni)) {
				clientes.remove(i);
				borrarAlquileresPorCliente(dni);
				break;
			}
		}
	}

	private void borrarAlquileresPorCliente(String dni) {
		List<Alquiler> alquileresCliente = new ArrayList<>();
		for (Alquiler alquiler : alquileres) {
			if (alquiler.getCliente().getDni().equals(dni)) {
				alquileresCliente.add(alquiler);
			}
		}
		alquileres.removeAll(alquileresCliente);
	}

	public List<Cliente> getClientes() {
		List<Cliente> copiaClientes = new ArrayList<>();
		for (Cliente cliente : clientes) {
			copiaClientes.add(new Cliente(cliente.getNombre(), cliente.getDni(), cliente.getTelefono()));
		}
		return copiaClientes;
	}

	public List<Turismo> getTurismos() {
		List<Turismo> copiaTurismos = new ArrayList<>();
		for (Turismo turismo : turismos) {
			copiaTurismos.add(new Turismo(turismo.getMarca(), turismo.getModelo(), turismo.getPrecioPorDia(),
					turismo.getMatricula()));
		}
		return copiaTurismos;
	}

	public List<Alquiler> getAlquileres() {
		List<Alquiler> copiaAlquileres = new ArrayList<>();
		for (Alquiler alquiler : alquileres) {
			copiaAlquileres.add(new Alquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler(),
					alquiler.getFechaDevolucion()));
		}
		return copiaAlquileres;
	}
}