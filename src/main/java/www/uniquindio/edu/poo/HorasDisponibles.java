package www.uniquindio.edu.poo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public enum HorasDisponibles {

    HORA_7(LocalTime.of(7, 0)),
    HORA_8(LocalTime.of(8, 0)),
    HORA_9(LocalTime.of(9, 0)),
    HORA_10(LocalTime.of(10, 0)),
    HORA_11(LocalTime.of(11, 0)),
    HORA_12(LocalTime.of(12, 0)),
    HORA_13(LocalTime.of(13, 0)),
    HORA_14(LocalTime.of(14, 0)),
    HORA_15(LocalTime.of(15, 0)),
    HORA_16(LocalTime.of(16, 0)),
    HORA_17(LocalTime.of(17, 0));

    private final LocalTime hora;
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("h:mm a");

    HorasDisponibles(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return hora.format(FORMATO_HORA);
    }

    public LocalTime getHora() {
        return hora;
    }
}
