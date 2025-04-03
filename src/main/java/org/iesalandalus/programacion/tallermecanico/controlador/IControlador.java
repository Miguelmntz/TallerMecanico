package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

public interface IControlador {
    void comenzar();

    void terminar();

    void actualizar(Evento evento);
}
