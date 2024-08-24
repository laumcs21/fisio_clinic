package www.uniquindio.edu.poo.Crud;

import java.util.Optional;
import java.util.function.Predicate;

import www.uniquindio.edu.poo.Cita;
import www.uniquindio.edu.poo.FisioClinic;
import www.uniquindio.edu.poo.Abstracciones.CRUD;

public class CitaCRUD implements CRUD<Cita> {

    private FisioClinic fisioClinic;

    public CitaCRUD(FisioClinic fisioClinic) {
        this.fisioClinic = fisioClinic;
    }

    public Optional<Cita> buscarCita(String codigo) {
        Predicate<Cita> condicion = cita -> cita.getCodigo().equals(codigo);
        return fisioClinic.getCitas().stream().filter(condicion).findAny();
    }

    @Override
    public void actualizar(Cita cita) {
        boolean existeCita = buscarCita(cita.getCodigo()).isPresent();
        if (!existeCita) {
            throw new IllegalArgumentException("La cita no está registrado.");
        }

        fisioClinic.getCitas().removeIf(u -> u.getCodigo().equals(cita.getCodigo()));
        fisioClinic.getCitas().add(cita);
    }

    @Override
    public Cita crear(Cita cita) {

        boolean existeCitaConflicto = fisioClinic.getCitas().stream()
                .anyMatch(existingCita -> existingCita.getFechaCita().equals(cita.getFechaCita()) &&
                        existingCita.getHoraCita().equals(cita.getHoraCita()) &&
                        existingCita.getTipoTerapia().equals(cita.getTipoTerapia()) &&
                        existingCita.getDoctor().equals(cita.getDoctor()));

        if (existeCitaConflicto) {
            throw new IllegalArgumentException("El horario de cita no se encuentra disponible.");
        }

        fisioClinic.getCitas().add(cita);
        return cita;
    }

    @Override
    public void eliminar(String codigo) {
        Optional<Cita> existeCita = buscarCita(codigo);
        if (!existeCita.isPresent()) {
            throw new IllegalArgumentException("La cita no está Asignada.");
        }

        fisioClinic.getCitas().remove(existeCita.get());
    }

    @Override
    public Cita leer(String codigo) {
        Optional<Cita> existeCita = buscarCita(codigo);
        if (!existeCita.isPresent()) {
            throw new IllegalArgumentException("La cita no está Asignada.");
        }

        System.out.println(existeCita.get().toString());

        return existeCita.get();
    }

}
