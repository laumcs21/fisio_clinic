package www.uniquindio.edu.poo;

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
    private Usuario usuarioLogueado;  // Variable para guardar el usuario logueado

    public InicioSesionView(Stage secondStage) {

        fisioClinic = FisioClinic.getInstancia();

        TextField identificacionField = new TextField();
        identificacionField.setPromptText("Identificación");

        PasswordField contraseñaField = new PasswordField();
        contraseñaField.setPromptText("Contraseña");

        mensajeLabel = new Label();

        Button iniciarButton = new Button("Iniciar Sesión");
        iniciarButton.setOnAction(e -> {
            mensajeLabel.setVisible(false);
            Usuario usuario = obtenerUsuarioLogueado(identificacionField.getText(), contraseñaField.getText());
            if (usuario != null) {
                mensajeLabel.setText("Inicio de sesión exitoso");
                mensajeLabel.setVisible(true);

                // Mostrar pantalla de usuario después de iniciar sesión exitoso
                mostrarPantallaUsuario(secondStage, usuario);
            } else {
                mensajeLabel.setText("Número de Identificación o contraseña incorrecta");
                mensajeLabel.setVisible(true);
            }
        });

        VBox root = new VBox(10, identificacionField, contraseñaField, iniciarButton, mensajeLabel);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 300);
        secondStage.setScene(scene);
        secondStage.setTitle("Iniciar sesión Usuario");

    }

    private Usuario obtenerUsuarioLogueado(String identificacion, String contraseña) {
        usuarioLogueado = fisioClinic.getUsuarios().stream()
                .filter(usuario -> usuario.getIdentificacion().equals(identificacion)
                        && usuario.getContraseña().equals(contraseña))
                .findFirst().orElse(null);
        return usuarioLogueado;
    }

    private void mostrarPantallaUsuario(Stage stage, Usuario usuarioLogueado) {
        new UsuarioView(stage, usuarioLogueado);
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
}
