package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import javax.swing.text.html.ListView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public void comenzar(){
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }

    public void terminar(){
        System.out.println("El modelo ha terminado.");
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");
        Cliente clienteexistente = clientes.buscar(revision.getCliente());
        Vehiculo vehiculoexistente = vehiculos.buscar(revision.getVehiculo());
        revisiones.insertar(new Revision(clienteexistente, vehiculoexistente, revision.getFechaInicio()));
    }

    public Cliente buscar(Cliente cliente){
        Objects.requireNonNull(clientes.buscar(cliente), "No puede buscar un cliente nulo.");
        return new Cliente(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculos.buscar(vehiculo), "No se puede buscar un vehículo nulo.");
        return vehiculo;
    }

    public Revision buscar(Revision revision){
        Objects.requireNonNull(revisiones.buscar(revision), "No se puede buscar una revisión nula.");
        return new Revision(revision);
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return clientes.modificar(cliente, nombre, telefono);
    }

    public Revision anadirHoras(Revision revision, int horas ) throws TallerMecanicoExcepcion{
        return revisiones.anadirHoras(revision, horas);
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial)throws  TallerMecanicoExcepcion{
        return revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        return revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion{
        List<Revision> revisionesClientes = revisiones.get(cliente);
        for (Revision revision : revisionesClientes){
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        List<Revision> revisionesVehiculo = revisiones.get(vehiculo);
        for (Revision revision : revisionesVehiculo){
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion{
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes(){
        List<Cliente> copiaCliente = new ArrayList<>();
        for (Cliente cliente : clientes.get()){
            copiaCliente.add(new Cliente(cliente));
        }
        return copiaCliente;
    }

    public List<Vehiculo> getVehiculos(){
        return new ArrayList<>(vehiculos.get());
    }

    public List<Revision> getRevisiones(){
        List<Revision> copiaRevision = new ArrayList<>();
        for (Revision revision : revisiones.get()){
            copiaRevision.add(new Revision(revision));
        }
        return copiaRevision;
    }

    public List<Revision> getRevisiones(Cliente cliente){
        List<Revision> copiaRevision = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)){
            copiaRevision.add(new Revision(revision));
        }
        return copiaRevision;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo){
        List<Revision> copiaRevision = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)){
            copiaRevision.add(new Revision(revision));
        }
        return copiaRevision;
    }

}
