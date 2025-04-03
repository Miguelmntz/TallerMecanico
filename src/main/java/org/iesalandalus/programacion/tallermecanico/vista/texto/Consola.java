package org.iesalandalus.programacion.tallermecanico.vista.texto;


import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;


import java.util.regex.Pattern;

public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {
    }

    static void mostrarCabecera(String mensaje) {
        System.out.printf("%s%n%s%n%s%n", "-".repeat(mensaje.length()), mensaje, "-".repeat(mensaje.length()));
    }

    static void mostrarMenu() {
        mostrarCabecera("Aplicación del taller mecánico. Uso para la gestión de clientes, vehículos y trabajos");
        for (Evento evento : Evento.values()) {
            System.out.println(evento.toString());
        }
    }

    static Evento elegirOpcion() {
        return Evento.get(leerEntero("Ingrese el número correspondiente a su opción elegida del menú: "));
    }

    static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    static String leerFecha(String mensaje) {
        Pattern patron = Pattern.compile(CADENA_FORMATO_FECHA);
        String entradaFecha;
        do {
            System.out.print(mensaje);
            entradaFecha = Entrada.cadena();
            if (!patron.matcher(entradaFecha).matches()) {
                System.out.println("ERROR: Formato de fecha(YYYY-MM-DD) no valido.");
            }
        } while (!patron.matcher(entradaFecha).matches());
        return entradaFecha;
    }
}