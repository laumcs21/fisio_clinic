package www.uniquindio.edu.poo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;

public class App extends Application {

        private FisioClinic fisioClinic;

        @Override
        public void start(Stage primaryStage) {

                fisioClinic = FisioClinic.getInstancia();

                Usuario usuario1 = new Usuario("Laura", "Cárdenas", "1001198723", "3003710163", "laumcs21@gmail.com",
                                LocalDate.of(2000, 8, 1), "1111");
                Usuario usuario2 = new Usuario("Bryan", "Cárdenas", "1032939385", "3023578426", "bryancsfut@gmail.com",
                                LocalDate.of(2006, 3, 6), "1111");
                Usuario usuario3 = new Usuario("Jefferson", "Rodriguez", "123", "3126240682", "jefferson@gmail.com",
                                LocalDate.of(2006, 7, 2), "1111");

                fisioClinic.getUsuarioCRUD().crear(usuario1);
                fisioClinic.getUsuarioCRUD().crear(usuario2);
                fisioClinic.getUsuarioCRUD().crear(usuario3);

                Doctor doctor1 = new Doctor("Andres", "López", "124", "1234", "andres@gmail.com",
                                LocalDate.of(1990, 4, 1), TipoTerapia.FISIOTERAPIA);
                Doctor doctor2 = new Doctor("Carolina", "Beltrán", "125", "1235", "carolina@gmail.com",
                                LocalDate.of(1991, 4, 1), TipoTerapia.HIDROTERAPIA);

                fisioClinic.getDoctorCRUD().crear(doctor1);
                fisioClinic.getDoctorCRUD().crear(doctor2);

                /*
                 * Cita cita1 = new Cita(usuario1, TipoTerapia.FISIOTERAPIA, LocalDate.of(2024,
                 * 9, 18),
                 * HorasDisponibles.HORA_17, doctor2);
                 * fisioClinic.getCitaCRUD().crear(cita1);
                 * Cita cita2 = new Cita(usuario2, TipoTerapia.HIDROTERAPIA, LocalDate.of(2024,
                 * 9, 18),
                 * HorasDisponibles.HORA_17, doctor1);
                 * fisioClinic.getCitaCRUD().crear(cita2);
                 * 
                 * System.out.println(fisioClinic.getCitas().size());
                 * fisioClinic.getCitaCRUD().eliminar(cita1.getCodigo());
                 * System.out.println(fisioClinic.getCitas().size());
                 * fisioClinic.getCitaCRUD().leer(cita2.getCodigo());
                 */

                Button loginButton = new Button("Iniciar Sesión");
                Label bienvenidaLabel = new Label("Bienvenido a FisioClinic");

                loginButton.setOnAction(e -> mostrarPantallaLogin(primaryStage));

               VBox root = new VBox(20, bienvenidaLabel, loginButton);
                root.setAlignment(Pos.CENTER);
                Scene scene = new Scene(root, 300, 200);

                primaryStage.setScene(scene);
                primaryStage.setTitle("FisioClinic - Inicio");
                primaryStage.show();
        }

        private void mostrarPantallaLogin(Stage stage) {
                new InicioSesionView(stage);
        }

        public static void main(String[] args) {
                launch(args);
        }
}