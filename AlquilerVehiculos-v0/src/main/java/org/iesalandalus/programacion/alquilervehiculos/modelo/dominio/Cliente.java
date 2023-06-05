package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Cliente {
	private String nombre;
	private String telefono;
	private String dni;
	private static final String ER_NOMBRE = "/^[A-Z][a-z]+((\s[A-Z][a-z]+)*)$/";
	private static final String ER_DNI = "/^\\d{8}[A-Za-z]$/";
	private static final String ER_TELEFONO = "/^\\d{9}$/";

	public Cliente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setTelefono(telefono);
		setDni(dni);
	}

	public Cliente(Cliente cliente) { // Constructor copia
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}
		if (!cliente.nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("El formato de nombre no es valido");
		} else
			System.out.println("El nombre del cliente es valido");
	}

	public static Cliente getClienteConDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		} else
			return new Cliente("Bob Esponja", dni, "950112233");

	}

	private boolean comprobarletradni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El dni no puede ser nulo.");
		}
		if (dni.matches(ER_DNI)) {
			return true;
		} else
			throw new IllegalArgumentException("El valor de dni no es valido");
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}

		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		}

		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}

		this.dni = dni;
	}

	@Override
	public String toString() {
		return String.format("%s - %s (%s)", nombre, dni, telefono);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

}
