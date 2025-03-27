package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

public interface IFuenteDatos {
    IClientes crearCliente();

    IVehiculos crearVehiculos();

    ITrabajos crearTrabajos();
}
