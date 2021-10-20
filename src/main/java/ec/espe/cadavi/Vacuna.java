package ec.espe.cadavi;

import ec.espe.cadavi.enums.Laboratorio;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema(name = "Vacuna", description = "Información de la vacuna")
@Entity
@Table(name = "vacunas")
public class Vacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_vacuna")
    private Long id;

    @Schema(required = true)
    @NotNull(message = "El Laboratorio es requerido")
    @Column
    private Laboratorio laboratorio;

    @Schema(required = true)
    @NotNull(message = "El la fecha es requerido")
    @FutureOrPresent(message = "La fecha no puede ser anterior al dia actual")
    @Column
    private LocalDate fecha;

    @Schema(required = true)
    @NotNull(message = "El Laboratorio es requerido")
    @Column
    private Integer numeroDosis;

    public Vacuna() {
    }

    public Vacuna(Laboratorio laboratorio, LocalDate fecha, Integer numDosis) {
        this.laboratorio = laboratorio;
        this.fecha = fecha;
        this.numeroDosis = numDosis;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getNumeroDosis() {
        return numeroDosis;
    }

    public void setNumeroDosis(Integer numeroDosis) {
        this.numeroDosis = numeroDosis;
    }
}

