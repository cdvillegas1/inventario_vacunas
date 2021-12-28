package ec.espe.cadavi.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import ec.espe.cadavi.enums.Estado;
import ec.espe.cadavi.enums.Laboratorio;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Schema(name = "Empleado", description = "Representación de Empleado")
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @SequenceGenerator(name = "employeesSequence", sequenceName = "known_employees_id_seq", allocationSize = 1, initialValue = 15)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "employeesSequence")
    @Column(name = "id_empleado")
    private Long id;

    @NotBlank(message = "La cedula no puede estar en blanco")
    @NotNull(message = "La cedula es requerida")
    @Column
    @Pattern(regexp = "[0-9]{10}", message = "La cedula debe tener 10 digitos numericos")
    private String cedula;

    @NotBlank(message = "Los nombres no pueden estar en blanco")
    @NotNull(message = "Los nombres son requeridos")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Los nombres no debe contener caracteres especiales")
    @Column
    private String nombres;

    @NotBlank(message = "Los nombres no pueden estar en blanco")
    @NotNull(message = "Los apellidos son requeridos")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Los apellidos no debe contener caracteres especiales")
    @Column
    private String apellidos;

    @NotBlank(message = "El correo no pueden estar en blanco")
    @NotNull(message = "El correo es requerido")
    @Email
    @Column
    private String correo;

    @PastOrPresent(message = "La fecha no pude ser anterior al dia de hoy")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column
    private String direccion;

    @Pattern(regexp = "[0-9]{10}", message = "El celular debe tener 10 digitos numericos")
    @Column
    private String celular;


    @NotNull(message = "El estado de vacunación es requerido")
    @Column
    private Estado estado;

    @Valid
    @JsonInclude(Include.NON_NULL) // anotación para no mostrar la propiedad nula en el JSON
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_vacuna")
    public Vacuna vacuna;

    public Empleado() {
    }

    public Empleado(Long id, String cedula, String nombres, String apellidos, String correo, Estado estado) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.estado = estado;
    }

    public Empleado(Long id, String cedula, String nombres, String apellidos, String correo, LocalDate fechaNacimiento, String direccion, String celular, Estado estado, Laboratorio laboratorio, LocalDate fecha, Integer numDosis) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.celular = celular;
        this.estado = estado;
        this.vacuna = new Vacuna(laboratorio, fecha, numDosis);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }


    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", direccion='" + direccion + '\'' +
                ", celular='" + celular + '\'' +
                ", estado=" + estado +
                ", vacuna=" + vacuna +
                '}';
    }

    @Schema(name = "Vacuna", description = "Información de la vacuna")
    @Entity
    @Table(name = "vacunas")
    public static class Vacuna {
        @Id
        @SequenceGenerator(name = "vacunesSequence", sequenceName = "known_vacunes_id_seq", allocationSize = 1, initialValue = 15)
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "vacunesSequence")
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

        private Vacuna(Laboratorio laboratorio, LocalDate fecha, Integer numDosis) {
            this.laboratorio = laboratorio;
            this.fecha = fecha;
            this.numeroDosis = numDosis;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        @Override
        public String toString() {
            return "Vacuna{" +
                    "id=" + id +
                    ", laboratorio=" + laboratorio +
                    ", fecha=" + fecha +
                    ", numeroDosis=" + numeroDosis +
                    '}';
        }
    }
}