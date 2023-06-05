package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.OperationNotSupportedException;

public class Alquileres {
	private List<Alquiler> Coleccionalquileres;

	public Alquileres() {
		Coleccionalquileres = new ArrayList<>();
	}

	public List<Alquiler> get() {
		return new ArrayList<>(Coleccionalquileres);
	}

	public List<Alquiler> getPorCliente(Cliente cliente) {
		List<Alquiler> alquileresPorCliente = new ArrayList<>();
		for (Alquiler alquiler : Coleccionalquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				alquileresPorCliente.add(alquiler);
			}
		}
		return alquileresPorCliente;
	}

	public List<Alquiler> getPorTurismo(Turismo turismo) {
		List<Alquiler> alquileresPorTurismo = new ArrayList<>();
		for (Alquiler alquiler : Coleccionalquileres) {
			if (alquiler.getTurismo().equals(turismo)) {
				alquileresPorTurismo.add(alquiler);
			}
		}
		return alquileresPorTurismo;
	}

	public int getCantidad() {
		return Coleccionalquileres.size();
	}

	public boolean comprobarAlquiler(Alquiler alquiler) {
		if (alquiler == null || Coleccionalquileres.contains(alquiler)) {
			return false;
		}
		for (Alquiler a : Coleccionalquileres) {
			if (a.getCliente().equals(alquiler.getCliente()) && a.getFechaDevolucion() == null) {
				return false;
			}
			if (a.getTurismo().equals(alquiler.getTurismo()) && a.getFechaDevolucion() == null) {
				return false;
			}
			if (a.getCliente().equals(alquiler.getCliente()) && a.getTurismo().equals(alquiler.getTurismo())
					&& a.getFechaAlquiler().isBefore(alquiler.getFechaDevolucion()) && (a.getFechaDevolucion() == null
							|| a.getFechaDevolucion().isAfter(alquiler.getFechaAlquiler()))) {
				return false;
			}
		}
		return true;
	}

	public void insertar(Alquiler alquiler) throws Exception {
		if (comprobarAlquiler(alquiler)) {
			Coleccionalquileres.add(alquiler);
		} else {
			throw new Exception("No se puede insertar el alquiler");
		}
	}

	public Alquiler devolver(Alquiler alquiler) throws OperationNotSupportedException {
	    if (alquiler == null || !Coleccionalquileres.contains(alquiler)) {
	        throw new OperationNotSupportedException("No se puede devolver el alquiler");
	    }
	    for (Alquiler a : Coleccionalquileres) {
	        if (a.equals(alquiler)) {
	            a.setFechaDevolucion(LocalDate.now());
	            return a;
	        }
	    }
	    return null;
	}

	public Alquiler buscar(Alquiler alquiler) {
		for (Alquiler a : Coleccionalquileres) {
			if (a.equals(alquiler)) {
				return a;
			}
		}
		return null;
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new OperationNotSupportedException("No se ha encontrado el alquiler");
		}
	}
}
