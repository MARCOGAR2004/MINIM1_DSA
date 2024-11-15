package edu.upc.dsa.services;

import edu.upc.dsa.JuegoManager;
import edu.upc.dsa.JuegoManagerImpl;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.RegistroUsuarioPunto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/juego", description = "Endpoint to Juego Service")
@Path("/juego")

public class JuegoService {
    private JuegoManager jm;
    public JuegoService() {
        this.jm = JuegoManagerImpl.getInstance();
        if (jm.sizeUsuarios() == 0) {
            this.jm.addUsuarioId("1", "Juan", "Perez", "juan.perez.upc.edu", "2020-05-01");
            this.jm.addUsuario("Marco", "Garrido", "marco.garrido.upc.edu", "2010-11-01");
            this.jm.addUsuario("Pedro", "Garcia", "pedro.garcia.upc.edu", "2015-04-02");
        }
        if (jm.sizePuntos() == 0) {
            this.jm.addPunto(10, 20, ElementType.DOOR);
            this.jm.addPunto(20, 30, ElementType.WALL);
            this.jm.addPunto(30, 40, ElementType.BRIDGE);
        }
        if (jm.sizeRegistros() == 0) {
            this.jm.Registrar("1", 10, 20);
            this.jm.Registrar("1", 20, 30);
            this.jm.Registrar("1", 30, 40);
        }
    }
    @GET
    @ApiOperation(value = "get Usuarios", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/usuarios/ordered")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios() {
        List<Usuario> usuarios = this.jm.UsuariosOrdenados();
        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios) {};
        return Response.status(201).entity(entity).build();
    }
    @POST
    @ApiOperation(value = "add a new Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/usuarios")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario u) {

        if (u.getNombre()==null || u.getApellido()==null || u.getCorreo()==null || u.getNacimiento()==null)
        {
            return Response.status(500).entity(u).build();
        }
        else
        {
            this.jm.addUsuario(u.getNombre(), u.getApellido(), u.getCorreo(), u.getNacimiento());
            return Response.status(201).entity(u).build();
        }
    }
    @GET
    @ApiOperation(value = "Info Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "Usuario not found")
    })
    @Path("/usuarios/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") String id) {
        Usuario u = this.jm.infoUsuario(id);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }

    @POST
    @ApiOperation(value = "add a new Punto", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= PuntoInteres.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/puntos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPunto(PuntoInteres p) {

        if(p.getTipo()==null)
        {
            return Response.status(500).entity(p).build();

        }
            this.jm.addPunto(p.getLongitud(), p.getLatitud(), p.getTipo());
            return Response.status(201).entity(p).build();


    }

    @POST
    @ApiOperation(value = "add a new registro", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= RegistroUsuarioPunto.class),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 404, message = "User or Point Not Found")
    })
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newRegistro(RegistroUsuarioPunto r) {

        if (r.getIdUsuario() == null) {
            return Response.status(500).entity("Validation error").build();
        }
        else if (this.jm.infoUsuario(r.getIdUsuario()) == null || this.jm.findPunto(r.getLongitud(), r.getLatitud()) == null) {
            return Response.status(404).entity("User or Point Not Found").build();
        }
        else {
            this.jm.Registrar(r.getIdUsuario(), r.getLongitud(), r.getLatitud());
            return Response.status(201).entity(r).build();
        }
    }


    @GET
    @ApiOperation(value = "get Puntos de Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntoInteres.class, responseContainer="List"),
    })
    @Path("/puntos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuntosDeUsuario(@PathParam("id") String id) {
        List<PuntoInteres> puntosU = this.jm.listaPuntosDeUsuario(id);
        GenericEntity<List<PuntoInteres>> entity = new GenericEntity<List<PuntoInteres>>(puntosU) {};
        return Response.status(201).entity(entity).build();
    }


    @GET
    @ApiOperation(value = "get Usuarios de Punto", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 404, message = "Point not Found")
    })
    @Path("/registro/usuario/{longitud}/{latitud}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosDePunto(@PathParam("longitud") int longitud, @PathParam("latitud") int latitud) {
        PuntoInteres p = this.jm.findPunto(longitud, latitud);
        if (p == null) {
            return Response.status(404).entity("Point not Found").build();
        }
        else {
            List<Usuario> usuarios = this.jm.listaUsuariosDePunto(longitud, latitud);
            GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios) {};
            return Response.status(201).entity(entity).build();
        }
    }

    @GET
    @ApiOperation(value = "get Puntos de Tipo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntoInteres.class, responseContainer="List"),
    })
    @Path("/puntos/tipo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuntosDeTipo(@QueryParam("tipo") ElementType tipo) {

        if(tipo == null)
        {
            return Response.status(500).entity(tipo).build();

        }
        List<PuntoInteres> puntos = this.jm.PuntosDeTipo(tipo);
        GenericEntity<List<PuntoInteres>> entity = new GenericEntity<List<PuntoInteres>>(puntos) {};
        return Response.status(201).entity(entity).build();
    }

}
