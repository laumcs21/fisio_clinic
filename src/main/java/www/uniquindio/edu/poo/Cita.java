package www.uniquindio.edu.poo;

import java.time.LocalDate;

import www.uniquindio.edu.poo.Contador;
import www.uniquindio.edu.poo.Enums.HorasDisponibles;
import www.uniquindio.edu.poo.Enums.TipoTerapia;

public class Cita {

    private Usuario usuario;
    private TipoTerapia tipoTerapia;
    private LocalDate fechaCita;
    private HorasDisponibles horaCita;
    private Doctor doctor;
    private String codigo;
    private static Contador contador = new Contador();

    public Cita(Usuario usuario, TipoTerapia tipoTerapia, LocalDate fechaCita, HorasDisponibles horaCita,
            Doctor doctor) {

        this.codigo = Contador.generarCodigo();
        this.usuario = usuario;
        this.tipoTerapia = tipoTerapia;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.doctor = doctor;

        assert usuario != null;
        assert doctor != null;
        assert fechaCita != null && fechaCita.isAfter(LocalDate.now());
        assert horaCita != null;
        assert tipoTerapia != null;
    }

    public TipoTerapia getTipoTerapia() {
        return tipoTerapia;
    }

    public void setTipoTerapia(TipoTerapia tipoTerapia) {
        this.tipoTerapia = tipoTerapia;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public HorasDisponibles getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(HorasDisponibles horaCita) {
        this.horaCita = horaCita;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Cita [codigo=" + codigo + ", usuario=" + usuario.getIdentificacion() + ", tipoTerapia=" + tipoTerapia +
                ", fechaCita=" + fechaCita + ", horaCita=" + horaCita + ", doctor=" + doctor.getNombre() + "]";
    }
}
