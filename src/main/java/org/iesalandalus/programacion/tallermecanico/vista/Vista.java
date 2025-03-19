package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.List;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador){
        Objects.requireNonNull(controlador,"ERROR: El controlador no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() throws TallerMecanicoExcepcion {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
        controlador.terminar();
    }

    public void terminar() {
        System.out.println("Ahii queo !!!");
    }

    private void ejecutar(Opcion opcion) throws TallerMecanicoExcepcion {
        try {
            switch (opcion) {
                case INSERTAR_CLIENTE -> insertarCliente();
                case INSERTAR_VEHICULO -> insertarVehiculo();
                case INSERTAR_REVISION -> insertarRevision();
                case BUSCAR_CLIENTE -> buscarCliente();
                case BUSCAR_VEHICULO -> busarVehiculo();
                case BUSCAR_REVISION -> buscarRevision();
                case MODIFICAR_CLIENTE -> modificarCliente();
                case ANADIR_HORAS_REVISION -> anadirHoras();
                case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
                case CERRAR_REVISION -> cerrarRevision();
                case BORRAR_CLIENTE -> borrarCliente();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case BORRAR_REVISION -> borrarRevision();
                case LISTAR_CLIENTES -> listarClientes();
                case LISTAR_VEHICULOS -> listarVehiculos();
                case LISTAR_REVISIONES -> listarRevisiones();
                case LISTAR_REVISIONES_CLIENTE -> listarRevisionesClientes();
                case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculos();
                case SALIR -> salir();
                default -> System.out.println("Opción invalida.");
            }
        } catch (Exception e) {
            throw new TallerMecanicoExcepcion(e.getMessage());
        }
    }

    private void insertarCliente() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Insertar Cliente");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Cliente insertado correctamente");
    }

    private void insertarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Insertar Vehiculo.");
        controlador.insertar(Consola.leerVehiculo());
        System.out.println("Vehiculo insertado correctamente.");
    }

    private void insertarRevision() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Insertar Revisión");
        controlador.insertar(Consola.leerRevision());
        System.out.println("Revision insertada correctamente.");
    }

    private void buscarCliente() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Busca Cliente");
        Cliente cliente = controlador.buscar(Consola.leerClienteDni());
        System.out.println((cliente != null)  ? cliente : "El cliente que buscas no existe.");
    }

    private void busarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Buscar Vehiculo");
        Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
        System.out.println((vehiculo != null ) ? vehiculo : "El vehiculo que buscas no existe.");
    }

    private void buscarRevision() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Buscar Revision");
        Revision revision = controlador.buscar(Consola.leerRevision());
        System.out.println((revision != null) ? revision : "La revision que buscar no existe.");
    }

    private void modificarCliente() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Modificar Cliente");
        Cliente modificado = controlador.modificar(Consola.leerClienteDni() , Consola.leerNuevoNombre() , Consola.leerNuevoTelefono());
        System.out.println((modificado != null ) ? "El cliente se ha modificado correctamente." : "El cliente no se ha modificado.");
    }

    private void anadirHoras() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Añadir Horas");
        controlador.anadirHoras(Consola.leerRevision() , Consola.leerHoras());
        System.out.println("Horas añadidas correctamente.");
    }

    private void anadirPrecioMaterial() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Añadir Precio Material");
        controlador.anadirPrecioMaterial(Consola.leerRevision() , Consola.leerPrecioMaterial());
        System.out.println("Precio del material añadido correctamente.");
    }

    private void cerrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Cerrar Revision");
        controlador.cerrar(Consola.leerRevision() , Consola.leerFechaCierre());
        System.out.println("Se ha cerrado la revisión.");
    }

    private void borrarCliente() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Borrar Cliente");
        controlador.borrar(Consola.leerClienteDni());
        System.out.println("El cliente ha sido borrado correctamente");
    }

    private void borrarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Borrar vehiculo.");
        controlador.borrar(Consola.leerVehiculoMatricula());
        System.out.println("El vehiculo se ha borrado correctamente.");
    }

    private void borrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostraCabecera("Borrar Revisión");
        controlador.borrar(Consola.leerRevision());
        System.out.println("La revisión se ha borrado correctamente.");
    }

    private void listarClientes() {
        Consola.mostraCabecera("Lista de clientes");
        List<Cliente> clientes = controlador.getClientes();
        if (!clientes.isEmpty()){
            for (Cliente cliente : clientes){
                System.out.println(cliente);
            }
        }else {
            System.out.println("No hay clientes que mostrar.");
        }
    }

    private void listarVehiculos() {
        Consola.mostraCabecera("Lista de vehículos");
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        if (!vehiculos.isEmpty()) {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        } else {
            System.out.println("No hay vehículos que mostrar");
        }
    }

    private void listarRevisiones() {
        Consola.mostraCabecera("Lista de revisiones");
        List<Revision> revisiones = controlador.getRevisiones();
        if (!revisiones.isEmpty()){
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay revisiones que mostrar.");
        }
    }

    private void listarRevisionesClientes() {
        Consola.mostraCabecera("Lista de revisiones clientes");
        List<Revision> revisiones = controlador.getRevisiones(Consola.leerClienteDni());
        if (!revisiones.isEmpty()) {
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay clientes que mostrar.");
        }
    }

    private void listarRevisionesVehiculos() {
        Consola.mostraCabecera("Lista de revisiones Vehículos");
        List<Revision> revisiones = controlador.getRevisiones(Consola.leerVehiculoMatricula());
        if (!revisiones.isEmpty()) {
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        }else {
            System.out.println("No hay vehículos que mostrar.");
        }
    }

    private void salir() {
        System.out.println("Ahí queo");
    }
}
