package www.uniquindio.edu.poo;

public interface CRUD<T> {
    T crear(T objeto);

    T leer(String id);

    void actualizar(T objeto);

    void eliminar(String id);
}
