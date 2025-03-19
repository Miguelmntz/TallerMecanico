package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {
    INSERTAR_CLIENTE(1,"Insertar Cliente"),
    BUSCAR_CLIENTE(2,"Buscar Cliente"),
    BORRAR_CLIENTE(3,"Borrar Cliente"),
    LISTAR_CLIENTES(4,"Listar Cliente"),
    MODIFICAR_CLIENTE(5,"Modificar Cliente"),
    INSERTAR_VEHICULO(6,"Insertar Vehiculo"),
    BUSCAR_VEHICULO(7,"Buscar Vehiculo"),
    BORRAR_VEHICULO(8,"Borrar Vehiculo"),
    LISTAR_VEHICULOS(9,"Listar Vehiculo"),
    INSERTAR_REVISION(10,"Insertar Revision"),
    BUSCAR_REVISION(11,"Buscar Revision"),
    BORRAR_REVISION(12,"Borrar Revision"),
    LISTAR_REVISIONES(13,"Listar Revisiones"),
    LISTAR_REVISIONES_CLIENTE(14,"Listar Revisiones Cliente"),
    LISTAR_REVISIONES_VEHICULO(15,"Listar Revisiones Vehiculo"),
    ANADIR_HORAS_REVISION(16,"Añadir Horas Revision"),
    ANADIR_PRECIO_MATERIAL_REVISION(17,"Añadir Precio Material Revision"),
    CERRAR_REVISION(18,"Cerrar Revision"),
    SALIR(19,"Salir");

    private int numeroOpcion;
    private String mensaje;
    private  static Map<Integer, Opcion> opciones = new HashMap<>();

    static {
        for (Opcion opcion : values()){
            opciones.put(opcion.numeroOpcion, opcion);
        }
    }
    private Opcion(int numeroOpcion, String mensaje){
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) throws TallerMecanicoExcepcion {
       return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) throws TallerMecanicoExcepcion{
        if (!esValida(numeroOpcion)){
            throw new IllegalArgumentException("La opción no es válida");
        }
        return opciones.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("[numeroOpcion=%s, mensaje=%s]", numeroOpcion, mensaje);
    }
}
