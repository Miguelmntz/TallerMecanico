package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

public interface IControlador {
    void comenzar() throws TallerMecanicoExcepcion;

    void terminar();

    void actualizar(Evento evento);
}
