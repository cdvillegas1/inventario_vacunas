package ec.espe.cadavi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import ec.espe.cadavi.enums.Estado;
import ec.espe.cadavi.enums.Laboratorio;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Schema(name = "Empleado", description = "Representación de Empleado")
@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @NotBlank(message = "Los apellidos no pueden estar en blanco")
    @NotNull(message = "El correo es requerido")
    @Email
    @Column
    private String correo;

    //@NotBlank(message = "El estado de vacunación no puede estar en blanco")
    @NotNull(message = "El estado de vacunación es requerido")
    @Column
    private Estado estado;

    @Valid
    @JsonInclude(Include.NON_NULL) // anotación para no mostrar la propiedad nula en el JSON
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_vacuna")
    private Vacuna vacuna;

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

    public Empleado(Long id, String cedula, String nombres, String apellidos, String correo, Estado estado, Laboratorio laboratorio, LocalDate fecha, Integer numDosis) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
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
}

