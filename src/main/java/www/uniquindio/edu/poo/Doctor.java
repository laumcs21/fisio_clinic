package www.uniquindio.edu.poo;

import java.time.LocalDate;

import www.uniquindio.edu.poo.Abstracciones.Persona;
import www.uniquindio.edu.poo.Enums.TipoTerapia;

public class Doctor extends Persona {

    public TipoTerapia tipoTerapia;

    public Doctor(String nombre, String apellido, String identificacion, String numeroCelular, String email,
            LocalDate fechaNacimiento, TipoTerapia tipoTerapia) {
        super(nombre, apellido, identificacion, numeroCelular, email, fechaNacimiento);

        this.tipoTerapia = tipoTerapia;

        assert tipoTerapia != null;
    }

    public TipoTerapia getTipoTerapia() {
        return tipoTerapia;
    }

    public void setTipoTerapia(TipoTerapia tipoTerapia) {
        this.tipoTerapia = tipoTerapia;
    }

}
