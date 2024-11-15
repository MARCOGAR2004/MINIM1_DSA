package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.RegistroUsuarioPunto;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class JuegoManagerImpl implements JuegoManager {
    private static JuegoManager instance;
    protected List<Usuario> usuarios;
    protected List<PuntoInteres> puntos;
    protected List<RegistroUsuarioPunto> registros;
    final static Logger logger = Logger.getLogger(JuegoManagerImpl.class);

    private JuegoManagerImpl() {
        this.usuarios = new LinkedList<>();
        this.puntos = new LinkedList<>();
        this.registros = new LinkedList<>();
    }

    public static JuegoManager getInstance() {
        if (instance==null) instance = new JuegoManagerImpl();
        return instance;
    }

    //Añadir un nuevo punto de Interes
    @Override
    public PuntoInteres addPunto(int longitud, int latitud, ElementType tipo) {
        logger.info("addPunto con parametros: longitud=" + longitud + ", latitud=" + latitud + ", tipo=" + tipo);

        PuntoInteres newPunto = new PuntoInteres(longitud, latitud, tipo);
        this.puntos.add(newPunto);

        logger.info("addPunto finished, punto añadido: " + newPunto.getLongitud() + " , " + newPunto.getLatitud() + "," + newPunto.getTipo());
        return newPunto;
    }

    //Obtienes un usuario mediante su id
    @Override
    public Usuario infoUsuario(String id)
    {
        logger.info("infoUsuario con id: " + id);
        for (Usuario u: this.usuarios) {
            if (u.getId().equals(id)) {
                logger.info("infoUsuario("+id+"): " + "nombre: " + u.getNombre() + ", apellido: " + u.getApellido() + ", correo: " + u.getCorreo() + ", fecha: " + u.getNacimiento());
                return u;
            }
        }
        logger.warn("Usuario no encontrado con id: " + id);
        return null;
    }

    //Obtienes la lista de usuarios ordenados alfabeticamente por su apellido y nombre
    @Override
    public List<Usuario> UsuariosOrdenados() {
        logger.info("UsuariosOrdenados");
        this.usuarios.sort((u1, u2) -> {
            int lastNameComparison = u1.getApellido().compareTo(u2.getApellido());
            if (lastNameComparison != 0) {
                return lastNameComparison;
            }
            return u1.getNombre().compareTo(u2.getNombre());
        });
        return this.usuarios;
    }

    //Registra que un Usuario ha pasado por un punto de interes
    @Override
    public RegistroUsuarioPunto Registrar (String idUsuario, int longitud, int latitud) {
        Usuario u = this.infoUsuario(idUsuario);
        PuntoInteres p = this.findPunto(longitud, latitud);
        if (u == null || p == null) {
            logger.warn("Usuario no encontrado con id: " + idUsuario + " o Punto no encontrado con longitud=" + longitud + " y latitud=" + latitud);
            return null;
        }
        else
        {
            RegistroUsuarioPunto Registro = new RegistroUsuarioPunto(idUsuario, longitud, latitud);
            this.registros.add(Registro);
            return Registro;
        }
    }

    @Override
    //Encuentra un punto de interes mediante su longitud y latitud, y si no lo encuentra devuelve null
    public PuntoInteres findPunto(int longitud, int latitud) {
        logger.info("findPunto con parametros: longitud=" + longitud + ", latitud=" + latitud);

        for (PuntoInteres punto : this.puntos) {
            if (punto.getLongitud() == longitud && punto.getLatitud() == latitud) {
                logger.info("Punto encontrado: " + punto);
                return punto;
            }
        }

        logger.warn("Punto no encontrado con longitud=" + longitud + " y latitud=" + latitud);
        return null;
    }

    //Devuelve lista de usuarios que han pasado por un punto de interes
    @Override
    public List<Usuario> listaUsuariosDePunto(int longitud, int latitud) {

        PuntoInteres p = this.findPunto(longitud, latitud);
        if (p == null) {
            logger.warn("Punto no encontrado con longitud=" + longitud + " y latitud=" + latitud);
            return null;
        }
        else
        {
            List<Usuario> usuariosPunto = new LinkedList<>();
            for (RegistroUsuarioPunto r : this.registros) {
                if (r.getLongitud() == longitud && r.getLatitud() == latitud) {
                    Usuario u = this.infoUsuario(r.getIdUsuario());
                    usuariosPunto.add(u);
                }
            }
            return usuariosPunto;
        }

    }
    //Lista los puntos de interes por los que ha pasado un usuario mediante su id
    @Override
    public List<PuntoInteres> listaPuntosDeUsuario(String id) {
        logger.info("listaPuntosDeUsuario con id: " + id);
        Usuario u = this.infoUsuario(id);
        if (u == null) {
            logger.warn("Usuario no encontrado con id: " + id);
            return null;
        }
        else
        {
            List<PuntoInteres> puntosUsuario = new LinkedList<>();
            for (RegistroUsuarioPunto r : this.registros) {
                if (r.getIdUsuario().equals(id)) {
                    PuntoInteres p = this.findPunto(r.getLongitud(), r.getLatitud());
                    puntosUsuario.add(p);
                }
            }
            return puntosUsuario;
        }
    }
    //Añade un nuevo usuario
    @Override
    public Usuario addUsuario(String nombre, String apellido, String correo, String fecha) {
        logger.info("addUsuario con parametros: nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", fecha=" + fecha);

        Usuario newUser = new Usuario(nombre, apellido, correo, fecha);
        this.usuarios.add(newUser);

        logger.info("addUsuario finished, usuario añadido: " + newUser.getNombre() + " " + newUser.getApellido());
        return newUser;
    }
    @Override
    public int sizeUsuarios() {
        int ret = this.usuarios.size();
        logger.info("sizeUsuarios " + ret);

        return ret;
    }
    @Override
    public int sizePuntos() {
        int ret = this.puntos.size();
        logger.info("sizePuntos " + ret);

        return ret;
    }

    public void clear() {
        this.usuarios.clear();
        this.puntos.clear();
        this.registros.clear();
    }

    //Añade un nuevo usuario con el id que queramos
    @Override
    public Usuario addUsuarioId(String id, String nombre, String apellido, String correo, String fecha) {
        logger.info("addUsuario con parametros: id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", fecha=" + fecha);

        Usuario newUser = new Usuario(id, nombre, apellido, correo, fecha);
        this.usuarios.add(newUser);

        logger.info("addUsuario finished, usuario añadido: " + newUser.getNombre() + " " + newUser.getApellido());
        return newUser;
    }
    @Override
    public int sizeRegistros() {
        int ret = this.registros.size();
        logger.info("sizeRegistros " + ret);

        return ret;
    }
    //Devuelve una lista de puntos de interes de un tipo concreto
    @Override
    public List<PuntoInteres> PuntosDeTipo(ElementType tipo) {
        logger.info("PuntosDeTipo con tipo: " + tipo);
        List<PuntoInteres> puntosTipo = new LinkedList<>();
        for (PuntoInteres p : this.puntos) {
            if (p.getTipo() == tipo) {
                puntosTipo.add(p);
            }
        }
        return puntosTipo;
    }


}
