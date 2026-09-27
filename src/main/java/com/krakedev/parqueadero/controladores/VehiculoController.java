package com.krakedev.parqueadero.controladores;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.parqueadero.modelo.Auto;
import com.krakedev.parqueadero.modelo.Motocicleta;
import com.krakedev.parqueadero.modelo.Vehiculo;
import com.krakedev.parqueadero.servicios.ServicioVehiculos;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final ServicioVehiculos servicioVehiculos;

    public VehiculoController(ServicioVehiculos servicioVehiculos) {
        this.servicioVehiculos = servicioVehiculos;
    }

    @PostMapping("/auto")
    public ResponseEntity<String> ingresarAuto(@RequestBody Auto auto) {

        boolean ingresado = servicioVehiculos.ingresarVehiculo(auto);

        if (ingresado) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Auto ingresado correctamente");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("No se pudo ingresar el auto");
    }

    @PostMapping("/moto")
    public ResponseEntity<String> ingresarMoto(@RequestBody Motocicleta moto) {

        boolean ingresado = servicioVehiculos.ingresarVehiculo(moto);

        if (ingresado) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Motocicleta ingresada correctamente");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("No se pudo ingresar la motocicleta");
    }

    @GetMapping
    public ResponseEntity<ArrayList<Vehiculo>> listarVehiculos() {

        return ResponseEntity.ok(servicioVehiculos.listarVehiculos());
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable String placa) {

        Vehiculo vehiculo = servicioVehiculos.buscarPorPlaca(placa);

        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(vehiculo);
    }
}