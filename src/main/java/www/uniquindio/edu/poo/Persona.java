package www.uniquindio.edu.poo;

import java.time.LocalDate;

public abstract class Persona {

    public String nombre;
    public String apellido;
    public String identificacion;
    public String numeroCelular;
    public String email;
    public LocalDate fechaNacimiento;

    public Persona(String nombre, String apellido, String identificacion, String numeroCelular, String email,
            LocalDate fechaNacimiento) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.numeroCelular = numeroCelular;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;

        assert nombre != null;
        assert apellido != null;
        assert identificacion != null;
        assert numeroCelular != null;
        assert email != null;
        assert fechaNacimiento != null;

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificacion() {
        return this.identificacion;
    }

    public String getNumeroCelular() {
        return this.numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}
