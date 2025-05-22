package org.example.parking;

import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EstacionamientoTest {

    @Test
    public void testRetirarVehiculo() throws Exception {
        //TODO HECHO test
        Estacionamiento estacionamiento = new Estacionamiento();
        Vehiculo vehiculo = new Vehiculo("INV128", "Mercedes", Vehiculo.Tipo.AUTO);
        Cliente cliente = new Cliente("39401889", "Bruno Wayne");

        //cliente.agregarVehiculo(vehiculo);
        //Ticket ticket = new Ticket(cliente, vehiculo);

        boolean ingreso = estacionamiento.ingresarVehiculo(cliente.getDni(), cliente.getNombre(), vehiculo);
        assertTrue(ingreso);

        Ticket ticket = estacionamiento.retirarVehiculo(vehiculo.getPatente());

        assertNotNull(ticket);
        assertEquals(vehiculo, ticket.getVehiculo());
        assertNotNull(ticket.getHoraSalida());
        assertTrue(ticket.calcularMinutos() > 0);
        assertTrue(ticket.calcularPrecio() > 0);

        //assertNotNull(ticketRetirado);
        //assertEquals(ticket.getVehiculo().getPatente(), ticketRetirado.getVehiculo().getPatente());

    }

    @Test
    public void testCalcularPrecio() throws Exception {
        // TODO HECHO test

        Cliente cliente = new Cliente("23423423", "Pancho");
        Vehiculo vehiculo = new Vehiculo("asd123", "fiat", Vehiculo.Tipo.AUTO);
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime salida = entrada.plusMinutes(90);
        Ticket ticket = new Ticket(cliente, vehiculo, entrada, salida);
        double precio = ticket.calcularPrecio();
        assertEquals(200.0, precio);


        cliente = new Cliente("23423423", "Pancho");
        vehiculo = new Vehiculo("asd123", "fiat", Vehiculo.Tipo.SUV);
        entrada = LocalDateTime.now();
        salida = entrada.plusMinutes(45);
        ticket = new Ticket(cliente, vehiculo, entrada, salida);
        precio = ticket.calcularPrecio();
        assertEquals(130.0, precio);


        cliente = new Cliente("23423423", "Pancho");
        vehiculo = new Vehiculo("asd123", "fiat", Vehiculo.Tipo.PICKUP);
        entrada = LocalDateTime.now();
        salida = entrada.plusMinutes(125);
        ticket = new Ticket(cliente, vehiculo, entrada, salida);
        precio = ticket.calcularPrecio();
        assertEquals(180.0 * 3, precio);
    }

}