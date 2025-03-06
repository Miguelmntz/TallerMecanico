package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    private final List<Revision> coleccionRevisiones;

    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> revisionesClientes = new ArrayList<>();
        for (Revision revision : coleccionRevisiones){
            if (revision.getCliente().equals(cliente)){
                revisionesClientes.add(revision);
            }

        }
        return revisionesClientes;
    }

    public List<Revision> get(Vehiculo vehiculo){
        List<Revision> revisionesVehiculos = new ArrayList<>();
        for (Revision revision : coleccionRevisiones){
            if (revision.getVehiculo().equals(vehiculo)){
                revisionesVehiculos.add(revision);
            }
        }
        return revisionesVehiculos;
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        coleccionRevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente , Vehiculo vehiculo , LocalDate fechaRevision) throws TallerMecanicoExcepcion {
        for (Revision revision : coleccionRevisiones) {
            if (!revision.estaCerrada()) {
                if (revision.getCliente().equals(cliente)){
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                } else if (revision.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }
            } else {
                if (revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())){
                    throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                } else if (revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                }
            }
        }
    }

    private Revision getRevision(Revision revision)throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"La revisión no puede ser nula");
        int indice = coleccionRevisiones.indexOf(revision);
        if (indice == -1){
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }

        return coleccionRevisiones.get(indice);
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        Revision revisionExistente = getRevision(revision);
        revisionExistente.anadirHoras(horas);
        return revisionExistente;
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision, "No puedo operar una revisión nula.");
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio a añadir debe ser mayor que cero.");
        }
        Revision revisionExistente = getRevision(revision);
        revisionExistente.anadirPrecioMaterial(precioMaterial);
        return revisionExistente;
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
       Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
       Objects.requireNonNull(fechaFin, "La  fecha de fin no puede ser nula.");
       if (fechaFin.isAfter(LocalDate.now())){
           throw  new IllegalArgumentException("La fecha de fin de revisión no puede ser posterior a la fecha de hoy.");
       }
       Revision revisionExistente = getRevision(revision);
       if (fechaFin.isBefore(revisionExistente.getFechaInicio())){
           throw new IllegalArgumentException("La fecha de fin de la revisión no puede ser anterior a la de inicio.");
       }
       revisionExistente.cerrar(fechaFin);
       return revisionExistente;
    }

    public Revision buscar(Revision revision){
    Objects.requireNonNull(revision,"No se puede buscar una revisión nula.");
    int indice = coleccionRevisiones.indexOf(revision);
    return (indice == -1) ? null : coleccionRevisiones.get(indice);
    }

    public void borrar(Revision revision ) throws TallerMecanicoExcepcion{
     Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
    if (!coleccionRevisiones.contains(revision)){
        throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
    }
     coleccionRevisiones.remove(revision);
    }
}
