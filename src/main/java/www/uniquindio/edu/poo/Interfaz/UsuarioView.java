package www.uniquindio.edu.poo.Interfaz;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import www.uniquindio.edu.poo.FisioClinic;
import www.uniquindio.edu.poo.Usuario;

public class UsuarioView {

    private FisioClinic fisioClinic;
    private Label mensajeLabel;
    public Usuario usuarioLogueado;

    public UsuarioView(Stage thirdStage, Usuario usuarioLogueado) {

        fisioClinic = FisioClinic.getInstancia();
        this.usuarioLogueado = usuarioLogueado;


        // Inicializamos el mensajeLabel
        mensajeLabel = new Label("Seleccione una opción para gestionar sus citas:");

        Button crearCitaButton = new Button("Crear Cita");
        crearCitaButton.setOnAction(e -> mostrarPantallaCrear(thirdStage, usuarioLogueado));

        Button eliminarCitaButton = new Button("Eliminar Cita");
        eliminarCitaButton.setOnAction(e -> mostrarPantallaEliminar(thirdStage));

        Button actualizarCitaButton = new Button("Actualizar Cita");
        actualizarCitaButton.setOnAction(e -> mostrarPantallaActualizar(thirdStage));

        Button buscarCitaButton = new Button("Buscar Cita");
        buscarCitaButton.setOnAction(e -> mostrarPantallaBuscar(thirdStage, usuarioLogueado));

        Button volverButton = new Button("Volver");
        volverButton.setOnAction(e -> mostrarPantallaInicio(thirdStage));

        VBox root = new VBox(10, mensajeLabel, crearCitaButton, eliminarCitaButton, actualizarCitaButton,
                buscarCitaButton, volverButton);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 300);
        thirdStage.setScene(scene);
        thirdStage.setTitle("Gestionar citas Usuario");
        thirdStage.show();
    }

    private void mostrarPantallaCrear(Stage stage, Usuario usuarioLogueado) {
        new CrearCitaView(stage, usuarioLogueado);
    }

    private void mostrarPantallaEliminar(Stage stage) {
        new EliminarCitaView(stage);
    }

    private void mostrarPantallaActualizar(Stage stage) {
        new ActualizarCitaView(stage);
    }

    private void mostrarPantallaBuscar(Stage stage, Usuario usuarioLogueado) {
        new BuscarCitaView(stage, usuarioLogueado);
    }

    private void mostrarPantallaInicio(Stage stage) {
        new InicioSesionView(stage);
    }
}
