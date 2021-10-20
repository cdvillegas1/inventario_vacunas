package ec.espe.cadavi;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(
                title = "Empleados API",
                description = "Esta API permite operaciones CRUD sobre Empleados, además el control de acceso de los endpoints.",
                version = "1.0",
                contact = @Contact(name = "Carlos David Villegas Armijos",
                        url = "https://www.linkedin.com/in/cadavi/",
                        email = "cdvespe@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {@Server(url = "http://localhost:8080")},
        externalDocs = @ExternalDocumentation(url = "https://github.com/quarkusio/quarkus-workshops", description = "Todo de la prueba Técnica Kruger")
)
public class VacunasApplication extends Application {
    // cuerpo basio
}