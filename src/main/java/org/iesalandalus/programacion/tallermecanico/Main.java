package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.controlador.IControlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Vista;


public class Main {
    public static void main(String[] args) throws TallerMecanicoExcepcion {
        ModeloCascada modeloCascada = new ModeloCascada(FabricaFuenteDatos.MEMORIA);
        Vista vista = FabricaVista.TEXTO.crear();
        IControlador controlador = new Controlador(modeloCascada, vista);
        controlador.comenzar();
    }
}