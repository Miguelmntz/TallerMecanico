package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;


public class Mecanico extends Trabajo {
    private static final float FACTOR_PRECIO_MATERIAL = 1.5F;
    private static final float FACTOR_HORA = 30;
    private float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
    }

    protected Mecanico(Trabajo trabajo) {
        super(trabajo);
        this.precioMaterial = ((Mecanico) trabajo).precioMaterial;
    }
    public float getPrecioMaterial(){
        return precioMaterial ;}

    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrado()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        }
        this.precioMaterial += precioMaterial;
    }


    @Override
    public float getPrecioEspecifico() {
        return (getHoras() * FACTOR_HORA) + FACTOR_PRECIO_MATERIAL * precioMaterial ;
    }

    @Override
    public String toString() {
        String precio = String.format("%.2f", getPrecioMaterial());
        String precioTotal = String.format("%.2f", getPrecio());
        String pantalla;
        if (!estaCerrado()) {
            pantalla = String.format("Mecánico -> %s - %s (%s) - %s %s - %s (%s - ): %d horas, %s € en material", cliente.getNombre(), cliente.getDni(), cliente.getTelefono(), vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(), fechaInicio.format(FORMATO_FECHA), horas, precio);
        } else {
            pantalla = String.format("Mecánico -> %s - %s (%s) - %s %s - %s (%s - %s): %d horas, %s € en material, %s € total", cliente.getNombre(), cliente.getDni(), cliente.getTelefono(), vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(), fechaInicio.format(FORMATO_FECHA), fechaFin.format(FORMATO_FECHA), horas, precio, precioTotal );
        }
        return pantalla;
    }
}
