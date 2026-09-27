package com.krakedev.parqueadero.servicios;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.parqueadero.modelo.TicketCobro;
import com.krakedev.parqueadero.modelo.Vehiculo;

@Service
public class ServicioCobro {

    private final ServicioVehiculos servicioVehiculos;

    private ArrayList<TicketCobro> historicoTickets = new ArrayList<>();

    public ServicioCobro(ServicioVehiculos servicioVehiculos) {
        this.servicioVehiculos = servicioVehiculos;
    }

    public TicketCobro procesarSalida(String placa, int horas) {

        Vehiculo vehiculo = servicioVehiculos.retirarVehiculo(placa);

        if (vehiculo == null) {
            return null;
        }

        double total = vehiculo.calcularTarifa(horas);

        String codigoTicket = "TCK-" + (int) (Math.random() * 900 + 100);

        TicketCobro ticket = new TicketCobro(
                codigoTicket,
                vehiculo,
                horas,
                total,
                LocalDate.now()
        );

        historicoTickets.add(ticket);

        return ticket;
    }

    public double calcularTotalRecaudado() {

        double total = 0;

        for (TicketCobro ticket : historicoTickets) {
            total = total + ticket.getTotalPagar();
        }

        return total;
    }

    public ArrayList<TicketCobro> listarTickets() {
        return historicoTickets;
    }
}