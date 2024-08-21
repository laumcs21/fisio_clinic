package www.uniquindio.edu.poo;

import java.util.Collection;
import java.util.ArrayList;

public class FisioClinic {

    private static FisioClinic instancia;
    private Collection<Usuario> usuarios;
    private Collection<Doctor> doctor;
    private Collection<Cita> citas;
    private UsuarioCRUD usuarioCRUD;
    private DoctorCRUD doctorCRUD;
    private CitaCRUD citaCRUD;

    private FisioClinic() {
        this.usuarios = new ArrayList<>();
        this.doctor = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    public static FisioClinic getInstancia() {
        if (instancia == null) {
            instancia = new FisioClinic();
            instancia.usuarioCRUD = new UsuarioCRUD(instancia); // Inicialización aquí
            instancia.doctorCRUD = new DoctorCRUD(instancia); // Inicialización aquí
            instancia.citaCRUD = new CitaCRUD(instancia); // Inicialización de citaCRUD
        }
        return instancia;
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Collection<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Collection<Doctor> getDoctores() {
        return doctor;
    }

    public void setDoctores(Collection<Doctor> doctor) {
        this.doctor = doctor;
    }

    public Collection<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Collection<Cita> citas) {
        this.citas = citas;
    }

    public UsuarioCRUD getUsuarioCRUD() {
        return usuarioCRUD;
    }

    public void setUsuarioCRUD(UsuarioCRUD usuarioCRUD) {
        this.usuarioCRUD = usuarioCRUD;
    }

    public DoctorCRUD getDoctorCRUD() {
        return doctorCRUD;
    }

    public void setDoctorCRUD(DoctorCRUD doctorCRUD) {
        this.doctorCRUD = doctorCRUD;
    }

    public static void setInstancia(FisioClinic instancia) {
        FisioClinic.instancia = instancia;
    }

    public CitaCRUD getCitaCRUD() {
        return citaCRUD;
    }

    public void setCitaCRUD(CitaCRUD citaCRUD) {
        this.citaCRUD = citaCRUD;
    }
}
