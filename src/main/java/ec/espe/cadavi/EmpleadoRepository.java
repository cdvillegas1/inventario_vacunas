package ec.espe.cadavi;

import ec.espe.cadavi.enums.Estado;
import ec.espe.cadavi.enums.Laboratorio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmpleadoRepository implements PanacheRepository<Empleado> {

    public List<Empleado> findByEstado(Estado estado) {
        System.out.println(estado.name());
        return list("SELECT e FROM Empleados e WHERE e.estado = ?1", estado.ordinal());
    }

//    public Empleado addVacuna(Laboratorio laboratorio, LocalDate fecha, Integer numDosis){
//
//    }
}
