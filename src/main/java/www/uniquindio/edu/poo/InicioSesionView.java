package www.uniquindio.edu.poo;

import java.util.function.Predicate;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InicioSesionView {

    private FisioClinic fisioClinic;
    private Label mensajeLabel;
    private Usuario usuarioLogueado;

    public InicioSesionView(Stage secondStage) {

        fisioClinic = FisioClinic.getInstancia();

        TextField identificacionField = new TextField();
        identificacionField.setPromptText("Identificación");

        TextField contraseñaField = new PasswordField();
        contraseñaField.setPromptText("Contraseña");

        mensajeLabel = new Label();

        Button IniciarButton = new Button("Iniciar Sesión");
        IniciarButton.setOnAction(e -> {
            mensajeLabel.setVisible(false);
            boolean usuarioEncontrado = buscarUsuarioPorIdentificacionContraseña(identificacionField.getText(),
                    contraseñaField.getText());
            if (usuarioEncontrado) {
                mensajeLabel.setText("Inicio de sesión exitoso");
                mensajeLabel.setVisible(true);

                // Mostrar pantalla de usuario después de iniciar sesión exitoso
                mostrarPantallaUsuario(secondStage, usuarioLogueado);
            } else {
                mensajeLabel.setText("Número de Identificación o contraseña incorrecta");
                mensajeLabel.setVisible(true);
            }
        });

        VBox root = new VBox(10, identificacionField, contraseñaField, IniciarButton, mensajeLabel);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 300);
        secondStage.setScene(scene);
        secondStage.setTitle("Iniciar sesión Usuario");

    }

    public boolean buscarUsuarioPorIdentificacionContraseña(String identificacion, String contraseña) {
        Predicate<Usuario> condicion = usuario -> usuario.getIdentificacion().equals(identificacion)
                && usuario.getContraseña().equals(contraseña);
        return fisioClinic.getUsuarios().stream().anyMatch(condicion);

    }

    private void manejarInicioSesion(Stage stage, String identificacion, String contraseña) {
        boolean usuarioEncontrado = buscarUsuarioPorIdentificacionContraseña(identificacion, contraseña);
        if (usuarioEncontrado) {
            usuarioLogueado = fisioClinic.getUsuarios().stream()
                    .filter(u -> u.getIdentificacion().equals(identificacion))
                    .findFirst().orElse(null);
            mostrarPantallaUsuario(stage, usuarioLogueado);
        } else {
            mensajeLabel.setText("Número de Identificación o contraseña incorrecta");
            mensajeLabel.setVisible(true);
        }
    }

    private void mostrarPantallaUsuario(Stage stage, Usuario usuarioLogueado) {
        new UsuarioView(stage, usuarioLogueado);
    }

}
