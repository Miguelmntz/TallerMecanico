package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Revision extends Trabajo {

    private static final float FACTOR_HORA = 35;
    private float precioMaterial;


    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
    }

    public Revision(Revision revision) {
        super(revision);

    }

    public float getPrecioEspecifico() {
        return (estaCerrado()) ? FACTOR_HORA * getHoras() : 0;}



    @Override
    public String toString() {
            String precioTotal = String.format("%.2f", getPrecio());
            String pantalla;
            if (!estaCerrado()) {
                pantalla = String.format("Revisión -> %s - %s (%s) - %s %s - %s (%s - ): %d horas", cliente.getNombre(), cliente.getDni(), cliente.getTelefono(), vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(), fechaInicio.format(FORMATO_FECHA), horas);
            } else {
                pantalla = String.format("Revisión -> %s - %s (%s) - %s %s - %s (%s - %s): %d horas, %s € total", cliente.getNombre(), cliente.getDni(), cliente.getTelefono(), vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(), fechaInicio.format(FORMATO_FECHA), fechaFin.format(FORMATO_FECHA), horas, precioTotal );
            }
            return pantalla;
        }
}
