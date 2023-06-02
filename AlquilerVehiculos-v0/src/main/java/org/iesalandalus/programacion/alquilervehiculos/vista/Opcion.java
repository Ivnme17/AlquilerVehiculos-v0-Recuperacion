package org.iesalandalus.programacion.alquilervehiculos.vista;

public enum Opcion {
    SALIR("SALIR"),
    INSERTAR_CLIENTE("INSERTAR_CLIENTE"),
    INSERTAR_TURISMO("INSERTAR_TURISMO"),
	INSERTAR_ALQUILER("INSERTAR_ALQUILER"),
	BUSCAR_CLIENTE("BUSCAR_CLIENTE"),
	BUSCAR_TURISMO("BUSCAR_TURISMO"),
	BUSCAR_ALQUILER("BUSCAR_ALQUILER"),
	MODIFICAR_CLIENTE("MODIFICAR_CLIENTE"),
	DEVOLVER_ALQUILER("DEVOLVER_ALQUILER"),
	BORRAR_CLIENTE("BORRAR_CLIENTE"),
	BORRAR_TURISMO("BORRAR_TURISMO"),
	BORRAR_ALQUILER("BORRAR_ALQUILER"),
	LISTAR_CLIENTES("LISTAR_CLIENTES"),
	LISTAR_TURISMOS("LISTAR_TURISMOS"),
	LISTAR_ALQUILERES("LISTAR_ALQUILERES"),
	LISTAR_ALQUILERES_CLIENTE("LISTAR_ALQUILERES_CLIENTE"),
	LISTAR_ALQUILERES_TURISMO("LISTAR_ALQUILERES_TURISMO");

    private final String texto;

    Opcion(String texto) {
        this.texto = texto;
    }

    private static boolean esOrdinalValido(int ordinal) {
        return ordinal >= 0 && ordinal < values().length;
    }

    public static Opcion get(int ordinal) {
        if (esOrdinalValido(ordinal)) {
            return values()[ordinal];
        } else {
            throw new IllegalArgumentException("Ordinal inválido: " + ordinal);
        }
    }

    @Override
    public String toString() {
        return ordinal() + ": " + texto;
    }
}