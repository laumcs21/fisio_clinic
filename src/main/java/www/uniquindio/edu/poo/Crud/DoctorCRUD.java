package www.uniquindio.edu.poo.Crud;

import java.util.Optional;
import java.util.function.Predicate;

import www.uniquindio.edu.poo.Doctor;
import www.uniquindio.edu.poo.FisioClinic;
import www.uniquindio.edu.poo.Abstracciones.CRUD;

public class DoctorCRUD implements CRUD<Doctor> {

    private FisioClinic fisioClinic;

    public DoctorCRUD(FisioClinic fisioClinic) {
        this.fisioClinic = fisioClinic;
    }

    private Optional<Doctor> buscarDoctorPorIdentificacion(String identificacion) {
        Predicate<Doctor> condicion = doctor -> doctor.getIdentificacion().equals(identificacion);
        return fisioClinic.getDoctores().stream().filter(condicion).findAny();
    }

    @Override
    public void actualizar(Doctor doctor) {
        boolean existeDoctor = buscarDoctorPorIdentificacion(doctor.getIdentificacion()).isPresent();
        if (!existeDoctor) {
            throw new IllegalArgumentException("El doctor no está registrado.");
        }

        fisioClinic.getDoctores().removeIf(d -> d.getIdentificacion().equals(doctor.getIdentificacion()));
        fisioClinic.getDoctores().add(doctor);
    }

    @Override
    public Doctor crear(Doctor doctor) {
        boolean existeDoctor = buscarDoctorPorIdentificacion(doctor.getIdentificacion()).isPresent();
        if (existeDoctor) {
            throw new IllegalArgumentException("El doctor ya está registrado.");
        }
        fisioClinic.getDoctores().add(doctor);
        return doctor;
    }

    @Override
    public void eliminar(String identificacion) {
        Optional<Doctor> doctor = buscarDoctorPorIdentificacion(identificacion);
        if (!doctor.isPresent()) {
            throw new IllegalArgumentException("El doctor no está registrado.");
        }
        fisioClinic.getDoctores().remove(doctor.get());
    }

    @Override
    public Doctor leer(String identificacion) {
        Optional<Doctor> doctor = buscarDoctorPorIdentificacion(identificacion);
        if (!doctor.isPresent()) {
            throw new IllegalArgumentException("El doctor no está registrado.");
        }
        return doctor.get();
    }
}
