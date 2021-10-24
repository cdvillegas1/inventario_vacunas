package ec.espe.cadavi.repositories;

import ec.espe.cadavi.entities.Empleado;
import ec.espe.cadavi.enums.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EmpleadoRepository implements PanacheRepository<Empleado> {

    public List<Empleado> findByEstado(Estado estado) {
        System.out.println(estado.name());
        return list("SELECT e FROM Empleados e WHERE e.estado = ?1", estado.ordinal());
    }
    public List<Empleado> findVacunados(){
        return list("estado", Estado.VACUNADO);
    }

//    public Empleado addVacuna(Laboratorio laboratorio, LocalDate fecha, Integer numDosis){
//
//    }
}
