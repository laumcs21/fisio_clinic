package www.uniquindio.edu.poo.Crud;

import java.util.Optional;
import java.util.function.Predicate;

import www.uniquindio.edu.poo.FisioClinic;
import www.uniquindio.edu.poo.Usuario;
import www.uniquindio.edu.poo.Abstracciones.CRUD;

public class UsuarioCRUD implements CRUD<Usuario> {

    private FisioClinic fisioClinic;

    public UsuarioCRUD(FisioClinic fisioClinic) {
        this.fisioClinic = fisioClinic;
    }

    public Optional<Usuario> buscarUsuarioPorIdentificacion(String identificacion) {
        Predicate<Usuario> condicion = usuario -> usuario.getIdentificacion().equals(identificacion);
        return fisioClinic.getUsuarios().stream().filter(condicion).findAny();
    }

    @Override
    public void actualizar(Usuario usuario) {
        boolean existeUsuario = buscarUsuarioPorIdentificacion(usuario.getIdentificacion()).isPresent();
        if (!existeUsuario) {
            throw new IllegalArgumentException("El usuario no está registrado.");
        }

        fisioClinic.getUsuarios().removeIf(u -> u.getIdentificacion().equals(usuario.getIdentificacion()));
        fisioClinic.getUsuarios().add(usuario);
    }

    @Override
    public Usuario crear(Usuario usuario) {
        boolean existeUsuario = buscarUsuarioPorIdentificacion(usuario.getIdentificacion()).isPresent();
        if (existeUsuario) {
            throw new IllegalArgumentException("El usuario ya está registrado.");
        }
        fisioClinic.getUsuarios().add(usuario);
        return usuario;
    }

    @Override
    public void eliminar(String identificacion) {
        Optional<Usuario> usuario = buscarUsuarioPorIdentificacion(identificacion);
        if (!usuario.isPresent()) {
            throw new IllegalArgumentException("El usuario no está registrado.");
        }
        fisioClinic.getUsuarios().remove(usuario.get());
    }

    @Override
    public Usuario leer(String identificacion) {
        Optional<Usuario> usuario = buscarUsuarioPorIdentificacion(identificacion);
        if (!usuario.isPresent()) {
            throw new IllegalArgumentException("El usuario no está registrado.");
        }
        return usuario.get();
    }
}
