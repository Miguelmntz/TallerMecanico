package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static final String ER_MARCA = "^(?!\\s)(?![A-Z]{2}-[A-Z]{2})(?=.*[A-Z])[A-Za-z0-9\\- ]{3,}(?<!\\s)$";
    private static final String ER_MATRICULA = "^\\d{4}(?:(?![AEIOU])[A-Z]){3}$";

    public Vehiculo {
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }

    private void validarMarca(String marca) {
        Objects.requireNonNull(marca, "La marca no puede ser nula.");
        if (!marca.matches(ER_MARCA)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
    }

    private void validarModelo(String modelo) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        if (modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }

    private static void validarMatricula(String matricula) {
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if (!matricula.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }

    }

    public static Vehiculo get(String matricula) {
        return new Vehiculo("Desconocida", "Desconocida", matricula);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vehiculo vehiculo)) return false;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }


    @Override
    public String toString() {
        return String.format("%s %s - %s", marca, modelo, matricula);
    }
}

