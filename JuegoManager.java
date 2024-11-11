package edu.upc.dsa;

import java.util.List;

import edu.upc.dsa.models.RegistroUsuarioPunto;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.PuntoInteres;

public interface JuegoManager {
    public Usuario addUsuario(String nombre, String apellido, String correo, String fecha);
    public List<Usuario> UsuariosOrdenados();
    public Usuario infoUsuario(String id);
    public PuntoInteres addPunto(int longitud, int latitud, ElementType tipo);
    public List<PuntoInteres> listaPuntosDeUsuario(String id);
    public List<Usuario> listaUsuariosDePunto(int longitud, int latitud);
    public RegistroUsuarioPunto Registrar(String idUsuario, int longitud, int latitud);
    public int sizeUsuarios();
    public int sizePuntos();
    public int sizeRegistros();
    public void clear();
    public Usuario addUsuarioId(String id, String nombre, String apellido, String correo, String fecha);
    public List<PuntoInteres> PuntosDeTipo(ElementType tipo);

}
