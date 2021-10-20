package ec.espe.cadavi.resources;

import ec.espe.cadavi.Empleado;
import ec.espe.cadavi.EmpleadoRepository;
import ec.espe.cadavi.enums.Estado;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/empleados")
@Tag(name = "Recurso Empleado", description = "RESTful API de Empleados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpleadoResource {

    @Inject
    Validator validator;

    @Inject
    EmpleadoRepository empleadoRepository;

    @Operation(
            operationId = "obtenerEmpleados",
            summary = "Obtener toda la lista de empleados.",
            description = "Endpoint que retorna un arreglos JSON con la información de los empleados."
    )
    @APIResponse(
            responseCode = "200",
            description = "Operación Completada",
            content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Empleado.class, required = true))
    )
    @GET
    public Response obtenerEmpleados() {
        List<Empleado> empleados = empleadoRepository.listAll();
        return Response.ok(empleados).build();
    }

    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        return empleadoRepository.findByIdOptional(id)
                .map(empleado -> Response.ok(empleado).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path("/estado/{estado}")
    public Response obtenerEmpleadosVacunados(@PathParam("estado") Estado estado ) {
        List<Empleado> empleados = empleadoRepository.findByEstado(estado);
        return Response.ok(empleados).build();
    }

    @Operation(
            operationId = "crearEmpleado",
            summary = "Crear un empleado.",
            description = "Endpoint disponible solo para el administrador que permite crear un empleado."
    )
    @APIResponse(
            responseCode = "201",
            description = "Operación Completada, Empleado creado exitosamente",
            content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Result.class, required = true))
    )
    @POST
    @Transactional
    public Result crearEmpleado(
            @RequestBody(description = "Creación de Empleado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Empleado.class))
            ) Empleado empleado) {
        Set<ConstraintViolation<Empleado>> violations = validator.validate(empleado);
        if (violations.isEmpty()) {
            empleadoRepository.persist(empleado);
            return new Result("Employed is valid! It was validated by manual validation.");
        } else {
            return new Result(violations);
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = empleadoRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(NOT_FOUND).build();
    }


    public static class Result {

        private String message;
        private boolean success;

        Result(String message) {
            this.success = true;
            this.message = message;
        }

        Result(Set<? extends ConstraintViolation<?>> violations) {
            this.success = false;
            this.message = violations.stream()
                    .map(cv -> cv.getMessage())
                    .collect(Collectors.joining(", "));
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

    }
}
