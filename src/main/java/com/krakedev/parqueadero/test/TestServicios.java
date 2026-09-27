package com.krakedev.parqueadero.test;

import com.krakedev.parqueadero.modelo.Auto;
import com.krakedev.parqueadero.modelo.Motocicleta;
import com.krakedev.parqueadero.modelo.Vehiculo;
import com.krakedev.parqueadero.servicios.ServicioCobro;
import com.krakedev.parqueadero.servicios.ServicioVehiculos;

public class TestServicios {

    public static void main(String[] args) {

        // Crear los servicios para la prueba
        ServicioVehiculos servicioVehiculos = new ServicioVehiculos();
        ServicioCobro servicioCobro = new ServicioCobro(servicioVehiculos);

        // Crear vehículos
        Vehiculo auto = new Auto("ABC-123", "Oscar", 4);
        Vehiculo moto = new Motocicleta("XYZ-789", "Juan", 200);

        // Ingresar vehículos
        System.out.println("Ingreso auto: " +
                servicioVehiculos.ingresarVehiculo(auto));

        System.out.println("Ingreso moto: " +
                servicioVehiculos.ingresarVehiculo(moto));

        // Intentar ingresar una placa duplicada
        Vehiculo autoDuplicado = new Auto("ABC-123", "Pedro", 2);

        System.out.println("Ingreso duplicado: " +
                servicioVehiculos.ingresarVehiculo(autoDuplicado));

        // Listar vehículos
        System.out.println("\nVehículos estacionados:");

        for (Vehiculo vehiculo : servicioVehiculos.listarVehiculos()) {
            System.out.println(vehiculo);
        }

        // Procesar salida del auto durante 5 horas
        System.out.println("\nProcesando salida del auto...");

        System.out.println(
                servicioCobro.procesarSalida("ABC-123", 5)
        );

        // Total recaudado
        System.out.println("\nTotal recaudado: " +
                servicioCobro.calcularTotalRecaudado());

        // Vehículos que siguen estacionados
        System.out.println("\nVehículos que siguen estacionados:");

        for (Vehiculo vehiculo : servicioVehiculos.listarVehiculos()) {
            System.out.println(vehiculo);
        }
    }
}