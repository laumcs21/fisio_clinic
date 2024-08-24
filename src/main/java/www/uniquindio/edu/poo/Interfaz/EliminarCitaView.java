package www.uniquindio.edu.poo.Interfaz;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import www.uniquindio.edu.poo.FisioClinic;
import www.uniquindio.edu.poo.Usuario;

public class EliminarCitaView {
    private FisioClinic fisioClinic;
    private Label mensajeLabel;
    private Usuario usuarioLogueado;

    public EliminarCitaView(Stage sixtStage) {
        fisioClinic = FisioClinic.getInstancia();
        this.usuarioLogueado = usuarioLogueado;

        mensajeLabel = new Label();

        TextField codigoField = new TextField();
        codigoField.setPromptText("Ingrese el Código de la cita");

        Button eliminarButton = new Button("Eliminar cita");
        eliminarButton.setOnAction(e -> {
            try {
                fisioClinic.getCitaCRUD().eliminar(codigoField.getText());
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("Cita eliminada con éxito");
            } catch (Exception ex) {
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("La cita no está registrada, o el código es incorrecto.");
            }
        });

        Button volverButton = new Button("Volver");
        volverButton.setOnAction(e -> mostrarPantallaUsuario(sixtStage, usuarioLogueado));

        VBox root = new VBox(10, codigoField, eliminarButton, mensajeLabel, volverButton);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 300);
        sixtStage.setScene(scene);
        sixtStage.setTitle("Eliminar cita");
        sixtStage.show();
    }

    private void mostrarPantallaUsuario(Stage thirdStage, Usuario usuarioLogueado) {
        new UsuarioView(thirdStage, usuarioLogueado);
    }

}
