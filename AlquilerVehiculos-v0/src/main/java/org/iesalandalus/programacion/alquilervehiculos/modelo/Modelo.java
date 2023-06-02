package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

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
		Vista vista = new Vista();
		Controlador controlador = new Controlador(vista, null);
		controlador.comenzar();
	}

	public void terminar() {
		System.out.println("El modelo ha terminado.");
	}

	public void insertar(Cliente cliente) {
		clientes.add(new Cliente(cliente));
	}

	public void insertar(Turismo turismo) {
		turismos.add(new Turismo(turismo));
	}

	public void insertar(Alquiler alquiler) {
		Cliente cliente = buscar(alquiler.getCliente().getDni());
		Turismo turismo = buscar(alquiler.getTurismo().getMatricula());
		if (cliente != null && turismo != null) {
			alquileres.add(new Alquiler(alquiler));
		}
	}

	public Cliente buscar(Cliente dni) {
		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(dni)) {
				return new Cliente(cliente);
			}
		}
		return null;
	}

	public Turismo buscar(Turismo matricula) {
		for (Turismo turismo : turismos) {
			if (turismo.getMatricula().equals(matricula)) {
				return new Turismo(turismo);
			}
		}
		return null;
	}

	public void modificar(Alquiler alquiler) {
	    for (int i = 0; i < alquileres.size(); i++) {
	        Alquiler a = alquileres.get(i);
	        if (a.getId().equals(alquiler.getId())) {
	            alquileres.set(i, new Alquiler(alquiler));
	            return;
	        }
	    }
	}

	public boolean devolver(Alquiler alquiler) {
	    for (int i = 0; i < alquileres.size(); i++) {
	        Alquiler a = alquileres.get(i);
	        if (a.equals(alquiler) && a.getEstado().equals("En curso")) {
	            a.setEstado("Finalizado");
	            return true;
	        }
	    }
	    return false;
	}

	public void borrar(String dni) {
		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			if (cliente.getDni().equals(dni)) {
				clientes.remove(i);
				borrarAlquileresPorCliente(cliente);
				break;
			}
		}
	}

	private void borrarAlquileresPorCliente(Cliente cliente) {
		List<Alquiler> alquileresCliente = new ArrayList<>();
		for (Alquiler alquiler : alquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				alquileresCliente.add(alquiler);
			}
		}
		alquileres.removeAll(alquileresCliente);
	}

	public void borrarTurismo(String matricula) {
		for (int i = 0; i < turismos.size(); i++) {
			Turismo turismo = turismos.get(i);
			if (turismo.getMatricula().equals(matricula)) {
				turismos.remove(i);
				borrarAlquileresPorTurismo(turismo);
				break;
			}
		}
	}

	private void borrarAlquileresPorTurismo(Turismo turismo) {
		List<Alquiler> alquileresTurismo = new ArrayList<>();
		for (Alquiler alquiler : alquileres) {
			if (alquiler.getTurismo().equals(turismo)) {
				alquileresTurismo.add(alquiler);
			}
		}
		alquileres.removeAll(alquileresTurismo);
	}

	public List<Cliente> getClientes() {
		List<Cliente> listaClientes = new ArrayList<>();
		for (Cliente cliente : clientes) {
			listaClientes.add(new Cliente(cliente));
		}
		return listaClientes;
	}

	public List<Turismo> getTurismos() {
		List<Turismo> listaTurismos = new ArrayList<>();
		for (Turismo turismo : turismos) {
			listaTurismos.add(new Turismo(turismo));
		}
		return listaTurismos;
	}

	public List<Alquiler> getAlquileres() {
		List<Alquiler> listaAlquileres = new ArrayList<>();
		for (Alquiler alquiler : alquileres) {
			listaAlquileres.add(new Alquiler(alquiler));
		}
		return listaAlquileres;
	}
	public void modificar(String dni, String nuevoNombre, String nuevoTelefono) {
	    Cliente cliente = buscar(dni);
	    if (cliente != null) {
	        cliente.setNombre(nuevoNombre);
	        cliente.setTelefono(nuevoTelefono);
	    }
	}

	public void mostrarListaClientes() {
	    for (Cliente cliente : clientes) {
	        System.out.println(cliente);
	    }
	}

	public void registrarAlquiler(String dniCliente, String codigoVehiculo,LocalDate fechaAlquiler, LocalDate FechaDevolucion) {
	    Cliente cliente = buscarCliente(dniCliente);
	    Turismo turismo = buscarTurismo(codigoVehiculo);
	    if (cliente != null && turismo != null) {
	        Alquiler alquiler = new Alquiler(cliente, turismo, fechaAlquiler, FechaDevolucion);
	        insertarAlquiler(alquiler);
	    }
	}

	public void finalizarAlquiler(String codigoVehiculo) {
	    Turismo turismo = buscarTurismo(codigoVehiculo);
	    if (turismo != null) {
	        for (Alquiler alquiler : alquileres) {
	            if (alquiler.getTurismo().equals(turismo) && alquiler.getEstado().equals("En curso")) {
	                alquiler.setEstado("Finalizado");
	                return;
	            }
	        }
	    }
	}
}