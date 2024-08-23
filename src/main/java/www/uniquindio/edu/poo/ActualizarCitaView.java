package www.uniquindio.edu.poo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ActualizarCitaView {

    private FisioClinic fisioClinic;
    private Label mensajeLabel;
    private ComboBox<TipoTerapia> tipoTerapiaComboBox;
    private DatePicker fechaCitaDatePicker;
    private ComboBox<HorasDisponibles> horaComboBox;
    private ComboBox<Doctor> doctorTipoComboBox;
    private TextField codigoCitaField;
    private Usuario usuarioLogueado;
    private Cita citaEncontrada;

    public ActualizarCitaView(Stage seventhStage) {

        fisioClinic = FisioClinic.getInstancia();
        this.usuarioLogueado = usuarioLogueado;

        codigoCitaField = new TextField();
        codigoCitaField.setPromptText("Ingrese el código de la cita");

        Button buscarCitaButton = new Button("Buscar cita");
        buscarCitaButton.setOnAction(e -> buscarCita(codigoCitaField.getText()));

        tipoTerapiaComboBox = new ComboBox<>();
        tipoTerapiaComboBox.setPromptText("Seleccione el tipo de terapia");

        fechaCitaDatePicker = new DatePicker();
        fechaCitaDatePicker.setPromptText("Seleccione la fecha de la cita");
        fechaCitaDatePicker.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #FF0000;");
                }
            }
        });

        horaComboBox = new ComboBox<>();
        horaComboBox.setPromptText("Seleccione la hora");

        doctorTipoComboBox = new ComboBox<>();
        doctorTipoComboBox.setPromptText("Seleccione el doctor");

        tipoTerapiaComboBox.setOnAction(e -> {
            TipoTerapia terapiaSeleccionada = tipoTerapiaComboBox.getValue();
            if (terapiaSeleccionada != null) {
                doctorTipoComboBox.getItems().clear();
                doctorTipoComboBox.getItems().addAll(buscarDoctorPorTerapia(terapiaSeleccionada));
            }
        });

        doctorTipoComboBox.setCellFactory(lv -> new ListCell<Doctor>() {
            @Override
            protected void updateItem(Doctor doctor, boolean empty) {
                super.updateItem(doctor, empty);
                setText(empty ? null : doctor.getNombre() + " " + doctor.getApellido());
            }
        });

        doctorTipoComboBox.setButtonCell(new ListCell<Doctor>() {
            @Override
            protected void updateItem(Doctor doctor, boolean empty) {
                super.updateItem(doctor, empty);
                setText(empty ? null : doctor.getNombre() + " " + doctor.getApellido());
            }
        });

        mensajeLabel = new Label();

        Button actualizarCitaButton = new Button("Actualizar");
        actualizarCitaButton.setOnAction(e -> {
            mensajeLabel.setVisible(false);
            if (validarCampos()) {
                actualizarCita();
            }
        });

        Button volverButton = new Button("Volver");
        volverButton.setOnAction(e -> mostrarPantallaUsuario(seventhStage, usuarioLogueado));

        VBox root = new VBox(10, codigoCitaField, buscarCitaButton, tipoTerapiaComboBox, fechaCitaDatePicker, horaComboBox, doctorTipoComboBox,
                mensajeLabel, actualizarCitaButton, volverButton);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 400);
        seventhStage.setScene(scene);
        seventhStage.setTitle("Actualizar cita");
    }

    private void buscarCita(String codigoCita) {
        Optional<Cita> optionalCita = fisioClinic.getCitaCRUD().buscarCita(codigoCita);
        if (optionalCita.isPresent()) {
            citaEncontrada = optionalCita.get();
            cargarDatosCita(citaEncontrada);
        } else {
            mensajeLabel.setText("Cita no encontrada.");
            mensajeLabel.setVisible(true);
        }
    }

    private void cargarDatosCita(Cita cita) {
        tipoTerapiaComboBox.setValue(cita.getTipoTerapia());
        fechaCitaDatePicker.setValue(cita.getFechaCita());
        horaComboBox.setValue(cita.getHoraCita());
        doctorTipoComboBox.getItems().clear();
        doctorTipoComboBox.getItems().addAll(buscarDoctorPorTerapia(cita.getTipoTerapia()));
        doctorTipoComboBox.setValue(cita.getDoctor());
    }

    private boolean validarCampos() {
        if (tipoTerapiaComboBox.getValue() == null) {
            mensajeLabel.setText("Por favor seleccione un tipo de terapia.");
            mensajeLabel.setVisible(true);
            return false;
        }

        if (fechaCitaDatePicker.getValue() == null) {
            mensajeLabel.setText("Por favor seleccione una fecha.");
            mensajeLabel.setVisible(true);
            return false;
        }

        if (horaComboBox.getValue() == null) {
            mensajeLabel.setText("Por favor seleccione una hora.");
            mensajeLabel.setVisible(true);
            return false;
        }

        if (doctorTipoComboBox.getValue() == null) {
            mensajeLabel.setText("Por favor seleccione un doctor.");
            mensajeLabel.setVisible(true);
            return false;
        }

        return true;
    }

    private void actualizarCita() {
        if (citaEncontrada != null) {
            try {
                citaEncontrada.setTipoTerapia(tipoTerapiaComboBox.getValue());
                citaEncontrada.setFechaCita(fechaCitaDatePicker.getValue());
                citaEncontrada.setHoraCita(horaComboBox.getValue());
                citaEncontrada.setDoctor(doctorTipoComboBox.getValue());

                fisioClinic.getCitaCRUD().actualizar(citaEncontrada);

                mensajeLabel.setText("Cita actualizada con éxito.");
                mensajeLabel.setVisible(true);
            } catch (Exception ex) {
                mensajeLabel.setText("Error al actualizar la cita.");
                mensajeLabel.setVisible(true);
            }
        } else {
            mensajeLabel.setText("No se ha cargado ninguna cita para actualizar.");
            mensajeLabel.setVisible(true);
        }
    }

    private void mostrarPantallaUsuario(Stage seventhStage, Usuario usuarioLogueado) {
        new UsuarioView(seventhStage, usuarioLogueado);
    }

    public List<Doctor> buscarDoctorPorTerapia(TipoTerapia tipo) {
        Predicate<Doctor> condicion = doctor -> doctor.getTipoTerapia().equals(tipo);
        return fisioClinic.getDoctores().stream().filter(condicion).collect(Collectors.toList());
    }
}
