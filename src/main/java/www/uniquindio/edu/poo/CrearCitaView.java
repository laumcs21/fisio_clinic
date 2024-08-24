package www.uniquindio.edu.poo;

import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CrearCitaView {
    private FisioClinic fisioClinic;
    private Label mensajeLabel;
    private ComboBox<TipoTerapia> tipoTerapiaComboBox;
    private DatePicker fechaCitaDatePicker;
    private ComboBox<HorasDisponibles> horaComboBox;
    private ComboBox<Doctor> doctorTipoComboBox;
    private Usuario usuarioLogueado;

    public CrearCitaView(Stage fifthStage, Usuario usuarioLogueado) {

        fisioClinic = FisioClinic.getInstancia();
        this.usuarioLogueado = usuarioLogueado;

        tipoTerapiaComboBox = new ComboBox<>();
        tipoTerapiaComboBox.getItems().addAll(TipoTerapia.values());
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
        horaComboBox.getItems().addAll(HorasDisponibles.values());
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

        Button crearCitaButton = new Button("Agendar");
        crearCitaButton.setOnAction(e -> {
            mensajeLabel.setVisible(false);
            if (tipoTerapiaComboBox.getValue() == null) {
                mensajeLabel.setText("Por favor seleccione un tipo de terapia.");
                mensajeLabel.setVisible(true);
                return;
            }

            if (fechaCitaDatePicker.getValue() == null) {
                mensajeLabel.setText("Por favor seleccione una fecha.");
                mensajeLabel.setVisible(true);
                return;
            }

            if (horaComboBox.getValue() == null) {
                mensajeLabel.setText("Por favor seleccione una hora.");
                mensajeLabel.setVisible(true);
                return;
            }

            if (doctorTipoComboBox.getValue() == null) {
                mensajeLabel.setText("Por favor seleccione un doctor.");
                mensajeLabel.setVisible(true);
                return;
            }
            try {
                Cita cita = new Cita(usuarioLogueado, tipoTerapiaComboBox.getValue(), fechaCitaDatePicker.getValue(),
                        horaComboBox.getValue(), doctorTipoComboBox.getValue());
                fisioClinic.getCitaCRUD().crear(cita);
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("Cita creada con éxito. Código de la cita: " + cita.getCodigo() + cita.getUsuario());
            } catch (Exception ex) {
                mensajeLabel.setText("El horario de la cita no está disponible");
                mensajeLabel.setVisible(true);
            }
        });

        Button volverButton = new Button("Volver");
        volverButton.setOnAction(e -> mostrarPantallaUsuario(fifthStage, usuarioLogueado));

        VBox root = new VBox(10, tipoTerapiaComboBox, fechaCitaDatePicker, horaComboBox, doctorTipoComboBox,
                mensajeLabel, crearCitaButton, volverButton);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 300);
        fifthStage.setScene(scene);
        fifthStage.setTitle("Agendar cita");
    }

    private void mostrarPantallaUsuario(Stage thirdStage, Usuario usuarioLogueado) {
        new UsuarioView(thirdStage, usuarioLogueado);
    }

    public List<Doctor> buscarDoctorPorTerapia(TipoTerapia tipo) {
        Predicate<Doctor> condicion = doctor -> doctor.getTipoTerapia().equals(tipo);
        return fisioClinic.getDoctores().stream().filter(condicion).collect(Collectors.toList());
    }
}
