package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(10,"Insertar Cliente"),
    BUSCAR_CLIENTE(11,"Buscar Cliente"),
    BORRAR_CLIENTE(12,"Borrar Cliente"),
    LISTAR_CLIENTES(13,"Listar Cliente"),
    MODIFICAR_CLIENTE(14,"Modificar Cliente"),
    INSERTAR_VEHICULO(20,"Insertar Vehiculo"),
    BUSCAR_VEHICULO(21,"Buscar Vehiculo"),
    BORRAR_VEHICULO(22,"Borrar Vehiculo"),
    LISTAR_VEHICULOS(23,"Listar Vehiculo"),
    INSERTAR_REVISION(30,"Insertar Revision"),
    INSERTAR_MECANICO(36, "Insertar mecánico"),
    BUSCAR_TRABAJO(31,"Buscar Revision"),
    BORRAR_TRABAJO(32,"Borrar Revision"),
    LISTAR_TRABAJOS(33,"Listar Revisiones"),
    LISTAR_TRABAJOS_CLIENTE(34,"Listar Revisiones Cliente"),
    LISTAR_TRABAJOS_VEHICULO(35,"Listar Revisiones Vehiculo"),
    ANADIR_HORAS_TRABAJO(50,"Añadir Horas Revision"),
    ANADIR_PRECIO_MATERIAL_TRABAJO(60,"Añadir Precio Material Revision"),
    CERRAR_TRABAJO(70,"Cerrar Revision"),
    SALIR(100,"Salir");

    private int codigo;
    private String texto;
    private  static Map<Integer, Evento> eventos = new HashMap<>();

    static {
        for (Evento codigo : values()){
            eventos.put(codigo.codigo, codigo);
        }
    }
    private Evento(int codigo, String texto){
        this.codigo = codigo;
        this.texto = texto;
    }

    public static boolean esValida(int codigo){
       return eventos.containsKey(codigo);
    }

    public  static Evento get(int codigo) throws TallerMecanicoExcepcion{
        if (!esValida(codigo)){
            throw new TallerMecanicoExcepcion("El código no es correcto.");
        }
        return eventos.get(codigo);
    }

    @Override
    public String toString() {
        return String.format("[código=%s, texto=%s]", codigo, texto);
    }
}
