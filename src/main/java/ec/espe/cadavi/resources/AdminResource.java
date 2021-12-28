package ec.espe.cadavi.resources;

import ec.espe.cadavi.entities.Empleado;
import ec.espe.cadavi.repositories.EmpleadoRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/admin")
public class AdminResource {

//    @Inject
//    Validator validator;
//
//    @Inject
//    EmpleadoRepository empleadoRepository;
//
//    @Operation(
//            operationId = "obtenerEmpleados",
//            summary = "Obtener toda la lista de empleados.",
//            description = "Endpoint que retorna un arreglos JSON con la información de los empleados."
//    )
//    @APIResponse(
//            responseCode = "200",
//            description = "Operación Completada",
//            content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Empleado.class, required = true))
//    )
//    @GET
//    public Response obtenerEmpleados() {
//        List<Empleado> empleados = empleadoRepository.listAll();
//        return Response.ok(empleados).build();
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String manage() {
        return "granted";
    }
}
