package www.uniquindio.edu.poo;

import java.time.LocalDate;

public class Usuario extends Persona {

    private String contrasena;

    public Usuario(String nombre, String apellido, String identificacion, String numeroCelular, String email,
            LocalDate fechaNacimiento, String contrasena) {
        super(nombre, apellido, identificacion, numeroCelular, email, fechaNacimiento);

        this.contrasena = contrasena;

        assert contrasena != null;
    }

    public String getContraseña() {
        return this.contrasena;
    }

    public void setContraseña(String contrasena) {
        this.contrasena = contrasena;
    }

}
