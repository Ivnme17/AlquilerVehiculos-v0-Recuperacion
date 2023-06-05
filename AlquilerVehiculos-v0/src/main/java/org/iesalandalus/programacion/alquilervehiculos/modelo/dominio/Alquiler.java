package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Alquiler {
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int PRECIO_DIA = 20;
    private LocalDate fechaAlquiler;
    private LocalDate fechaDevolucion;
    private Cliente cliente;
    private Turismo turismo;

    public Alquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler, LocalDate fechaDevolucion) {
		setCliente(cliente);
        setTurismo(turismo);
        setFechaAlquiler(fechaAlquiler);
        setFechaDevolucion(fechaDevolucion);
    }

    public Alquiler(Alquiler alquiler) {
        this(new Cliente(alquiler.getCliente()), new Turismo(alquiler.getTurismo()), alquiler.getFechaAlquiler(), alquiler.getFechaDevolucion());
    }

    private void setFechaAlquiler(LocalDate fechaAlquiler) {
        if (fechaAlquiler == null) {
            throw new NullPointerException("ERROR: La fecha no puede ser nula.");
        }
        LocalDate hoy = LocalDate.now();
        if (fechaAlquiler.isAfter(hoy)) {
            throw new IllegalArgumentException("La fecha de alquiler no puede ser posterior a hoy");
        }
        this.fechaAlquiler = fechaAlquiler;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        LocalDate hoy = LocalDate.now();
        if (fechaDevolucion.isBefore(fechaAlquiler) || fechaDevolucion.isAfter(hoy)) {
            throw new IllegalArgumentException("La fecha de devolución no es adecuada");
        }
        this.fechaDevolucion = fechaDevolucion;
    }

    private void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
        }
        this.cliente = cliente;
    }

    private void setTurismo(Turismo turismo) {
        if (turismo == null) {
            throw new NullPointerException("ERROR: El turismo no puede ser nulo.");
        }
        this.turismo = turismo;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Turismo getTurismo() {
        return turismo;
    }

    public double getPrecioAlquiler() {
        long diasAlquiler = ChronoUnit.DAYS.between(fechaAlquiler, fechaDevolucion) + 1;
        return diasAlquiler * PRECIO_DIA;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, fechaAlquiler, fechaDevolucion, turismo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alquiler other = (Alquiler) obj;
        return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
                && Objects.equals(fechaDevolucion, other.fechaDevolucion) && Objects.equals(turismo, other.turismo);
    }

    @Override
    public String toString() {
        return String.format(
                "Alquiler [FORMATO_FECHA=%s, PRECIO_DIA=%s, fechaAlquiler=%s, fechaDevolucion=%s, cliente=%s, turismo=%s, precioAlquiler=%s]",
                FORMATO_FECHA, PRECIO_DIA, FORMATO_FECHA.format(fechaAlquiler), FORMATO_FECHA.format(fechaDevolucion), cliente, turismo, getPrecioAlquiler());
    }
}
