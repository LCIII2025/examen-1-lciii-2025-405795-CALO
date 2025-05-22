package org.example.parking;

import java.util.*;

public class Estacionamiento {
    private final int capacidadMaxima = 50;
    private final Map<String, Ticket> vehiculosEstacionados = new HashMap<>();
    private final Map<String, Cliente> clientesRegistrados = new HashMap<>();

    public boolean ingresarVehiculo(String dni, String nombre, Vehiculo vehiculo) {
        // TODO HECHO implementar la logica para registrar el ingreso de un nuevo vehiculo en el parking
        // la capacidad maxima del estacionamiento es de 50 vehiculos, si supera esta capacidad retornar FALSE
        // validar que no exista otro vehiculo con la misma patente, es un caso de error, retornar FALSE
        // validar si existe el cliente registrado, agregar el nuevo vehiculo en la lista del cliente existente, caso contrario crear un nuevo registro
        // si el proceso es exitoso retornar TRUE
        if (vehiculosEstacionados.size() >= capacidadMaxima) {
            return false;
        }

        if (vehiculosEstacionados.containsKey(vehiculo.getPatente())) {
            return false;
        }

        Cliente cliente = clientesRegistrados.get(dni);

        if (cliente == null) {
            cliente = new Cliente(dni, nombre);
            cliente.agregarVehiculo(vehiculo);
            clientesRegistrados.put(dni, cliente);
        } else {
            cliente.agregarVehiculo(vehiculo);
        }

        Ticket ticket = new Ticket(cliente, vehiculo);

        vehiculosEstacionados.put(vehiculo.getPatente(), ticket);

        return true;

    }

    public Ticket retirarVehiculo(String patente) throws Exception {
        // TODO HECHO implementar la lógica para retirar un vehiculo del parking
        // validar que exista la patente, caso contrario arrojar la exception "Vehiculo no encontrado"
        // calcular y retornar el ticket del vehiculoEstacionado (ver Ticket.marcarSalida())
        // si el vehiculo no existe retornar NULL
        Ticket ticket = vehiculosEstacionados.get(patente);

        if (ticket == null) {
            throw new Exception("Vehiculo no encontrado");
        }

        ticket.marcarSalida();

        vehiculosEstacionados.remove(patente);

        return ticket;
    }

    public List<Ticket> listarVehiculosEstacionados() {
        return new ArrayList<>(vehiculosEstacionados.values());
    }
}
