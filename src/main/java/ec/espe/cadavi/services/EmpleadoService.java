package ec.espe.cadavi.services;

import ec.espe.cadavi.entities.Empleado;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;

@ApplicationScoped
public class EmpleadoService {

    public void validateEmpleado(@Valid Empleado empleado) {
        // your business logic here
    }
}
