package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Trabajos implements ITrabajos {
    private final List<Trabajo> coleccionTrabajo;

    public Trabajos() {
        coleccionTrabajo = new ArrayList<>();
    }

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionTrabajo);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> revisionesClientes = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajo){
            if (trabajo.getCliente().equals(cliente)){
                revisionesClientes.add(trabajo);
            }

        }
        return revisionesClientes;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo){
        List<Trabajo> revisionesVehiculos = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajo){
            if (trabajo.getVehiculo().equals(vehiculo)){
                revisionesVehiculos.add(trabajo);
            }
        }
        return revisionesVehiculos;
    }

    @Override
    public void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(trabajo,"No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajo.add(trabajo);
    }

    private void comprobarTrabajo(Cliente cliente , Vehiculo vehiculo , LocalDate fechaRevision) throws TallerMecanicoExcepcion {
        for (Trabajo trabajo : coleccionTrabajo) {
            if (!trabajo.estaCerrado()) {
                if (trabajo.getCliente().equals(cliente)){
                    throw new TallerMecanicoExcepcion("El cliente tiene otro trabajo en curso.");
                } else if (trabajo.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en el taller.");
                }
            } else {
                if (trabajo.getCliente().equals(cliente) && !fechaRevision.isAfter(trabajo.getFechaFin())){
                    throw new TallerMecanicoExcepcion("El cliente tiene otro trabajo posterior.");
                } else if (trabajo.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(trabajo.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El vehículo tiene otro trabajo posterior.");
                }
            }
        }
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo)throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo,"No puedo operar sobre un vehiculo nulo");
        Trabajo trabajoEncontrado = null;
        Iterator<Trabajo> iteradorTrabajos = coleccionTrabajo.iterator();
        while (iteradorTrabajos.hasNext() && trabajoEncontrado == null){
            Trabajo trabajo = iteradorTrabajos.next();
            if (trabajo.getVehiculo().equals(vehiculo) && !trabajo.estaCerrado()){
                trabajoEncontrado = trabajo;
            }
        }
        if (trabajoEncontrado == null){
            throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehiculo.");
        }
       return trabajoEncontrado;
    }

    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(trabajo, "No puedo añadir horas a un trabajo nulo.");
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        Trabajo trabajoExistente = getTrabajoAbierto(trabajo.getVehiculo());
        trabajoExistente.anadirHoras(horas);
        return trabajoExistente;
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(trabajo, "No puedo añadir precio del material a un trabajo nulo.");
        trabajo = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajo instanceof Revision) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio al material para este tipo de trabajos.");
        }else if (trabajo instanceof Mecanico mecanico) {
            mecanico.anadirPrecioMaterial(precioMaterial);
        }
        return trabajo;
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
       Objects.requireNonNull(trabajo, "No puedo cerrar un trabajo nulo.");
       Objects.requireNonNull(fechaFin, "La  fecha de fin no puede ser nula.");
       if (fechaFin.isAfter(LocalDate.now())){
           throw  new IllegalArgumentException("La fecha de fin de revisión no puede ser posterior a la fecha de hoy.");
       }
       Trabajo trabajoExistente = getTrabajoAbierto(trabajo.getVehiculo());
       if (fechaFin.isBefore(trabajoExistente.getFechaInicio())){
           throw new IllegalArgumentException("La fecha de fin de la revisión no puede ser anterior a la de inicio.");
       }
       trabajoExistente.cerrar(fechaFin);
       return trabajoExistente;
    }

    @Override
    public Trabajo buscar(Trabajo trabajo){
    Objects.requireNonNull(trabajo,"No se puede buscar un trabajo nulo.");
    int indice = coleccionTrabajo.indexOf(trabajo);
    return (indice == -1) ? null : coleccionTrabajo.get(indice);
    }

    @Override
    public void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion{
     Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
    if (!coleccionTrabajo.contains(trabajo)){
        throw new TallerMecanicoExcepcion("No existe ningún trabajo igual.");
    }
     coleccionTrabajo.remove(trabajo);
    }

}
