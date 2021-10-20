package ec.espe.cadavi;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;

@ApplicationScoped
public class EmpleadoService {

    public void validateEmpleado(@Valid Empleado empleado) {
        // your business logic here
    }
}
