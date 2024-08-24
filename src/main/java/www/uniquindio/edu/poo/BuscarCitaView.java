package www.uniquindio.edu.poo;

import java.util.NoSuchElementException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuscarCitaView {
    private FisioClinic fisioClinic;
    private Label mensajeLabel;
    private Usuario usuarioLogueado;

    public BuscarCitaView(Stage sixtStage, Usuario usuarioLogueado) {
        fisioClinic = FisioClinic.getInstancia();
        this.usuarioLogueado = usuarioLogueado;

        mensajeLabel = new Label();

        TextField codigoField = new TextField();
        codigoField.setPromptText("Ingrese el Código de la cita");

        Button buscarButton = new Button("Buscar cita");
        buscarButton.setOnAction(e -> {
            try {
                Cita cita = fisioClinic.getCitaCRUD().leer(codigoField.getText());
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("Cita encontrada:\n" +
                "Código: " + cita.getCodigo() + "\n" +
                "Identificación: " + cita.getUsuario().getIdentificacion() + "\n" +
                "Fecha: " + cita.getFechaCita() + "\n" +
                "Hora: " + cita.getHoraCita() + "\n" +
                "Doctor: " + cita.getDoctor().getNombre());
            } catch (NoSuchElementException ex) {
                 mensajeLabel.setText(ex.getMessage());
            } catch (Exception ex) {
                 mensajeLabel.setText("Ocurrió un error inesperado: " + ex.getMessage());
                 System.out.println(""+ ex.getMessage());
            }
});

        Button volverButton = new Button("Volver");
        volverButton.setOnAction(e -> mostrarPantallaUsuario(sixtStage, usuarioLogueado));

        VBox root = new VBox(10, codigoField, buscarButton, mensajeLabel, volverButton);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 300);
        sixtStage.setScene(scene);
        sixtStage.setTitle("Buscar cita");
        sixtStage.show();
    }

    private void mostrarPantallaUsuario(Stage thirdStage, Usuario usuarioLogueado) {
        new UsuarioView(thirdStage, usuarioLogueado);
    }

}
