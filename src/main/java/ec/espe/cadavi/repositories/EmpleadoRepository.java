package ec.espe.cadavi.repositories;

import ec.espe.cadavi.entities.Empleado;
import ec.espe.cadavi.enums.Estado;
import ec.espe.cadavi.enums.Laboratorio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class EmpleadoRepository implements PanacheRepository<Empleado> {

    public List<Empleado> findByEstado(Estado estado) {
        if (estado == Estado.VACUNADO)
            return list("estado", Estado.VACUNADO);
        else
            return list("estado", Estado.NO_VACUNADO);
    }

    public List<Empleado> findByVaccineType(Laboratorio laboratorio) {

        switch (laboratorio) {
            case SPUTNIK:
                return list("vacuna.laboratorio", Laboratorio.SPUTNIK);
            case ASTRAZENECA:
                return list("vacuna.laboratorio", Laboratorio.ASTRAZENECA);
            case PFIZER:
                return list("vacuna.laboratorio", Laboratorio.PFIZER);
            case JHONSON_JHONSON:
                return list("vacuna.laboratorio", Laboratorio.JHONSON_JHONSON);
            case SINOVAC:
                return list("vacuna.laboratorio", Laboratorio.SINOVAC);
            case CANSINO:
                return list("vacuna.laboratorio", Laboratorio.CANSINO);
            default:
                throw new IllegalStateException("Unexpected value: " + laboratorio);
        }
    }

    public List<Empleado> findByDateRange(String start, String end) {
        List<Empleado> list = list("vacuna.fecha between ?1 and ?2", LocalDate.parse(start), LocalDate.parse(end));
        return list;
    }
}
