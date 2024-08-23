package www.uniquindio.edu.poo;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class BuscarCitaView {
    private FisioClinic fisioClinic;
    private Label mensajeLabel;
    private TextField identificacionTextField;
    private TextArea resultadoArea;

    public BuscarCitaView(Stage eigthStage) {
        this.fisioClinic = FisioClinic.getInstancia();

        // Creación de los componentes de la interfaz gráfica
        Label instruccionesLabel = new Label("Ingrese la identificación del usuario para buscar la cita:");
        identificacionTextField = new TextField();
        identificacionTextField.setPromptText("Identificación del usuario");

        Button buscarButton = new Button("Buscar");
        buscarButton.setOnAction(e -> buscarCitaPorIdentificacion(identificacionTextField.getText()));

        mensajeLabel = new Label();
        resultadoArea = new TextArea();
        resultadoArea.setEditable(false); // Hacemos que el área de texto no sea editable
        resultadoArea.setWrapText(true); // Hacemos que el texto se ajuste al área

        VBox root = new VBox(10, instruccionesLabel, identificacionTextField, buscarButton, resultadoArea);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 300);
        eigthStage.setScene(scene);
        eigthStage.setTitle("Buscar Cita");
        eigthStage.show();


        
    }

    public void mostrarDetallesCita(String detalles) {
        // Crear una nueva ventana
        Stage detallesStage = new Stage();
        detallesStage.setTitle("Detalles de la Cita");
    
        // Crear un VBox para contener los detalles
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
    
        // Agregar una etiqueta con los detalles de la cita
        Label detallesLabel = new Label(detalles);
        vbox.getChildren().add(detallesLabel);
    
        // Crear una escena y agregarla a la ventana
        Scene scene = new Scene(vbox, 400, 300);
        detallesStage.setScene(scene);
    
        // Mostrar la ventana
        detallesStage.show();
    }

    public void buscarCitaPorIdentificacion(String identificacionUsuario) {
        System.out.println("Buscando cita para identificación: " + identificacionUsuario);
    
        if (fisioClinic.getCitas() == null) {
            System.out.println("No se pudieron obtener las citas de FisioClinic.");
            return;
        }
    
        Optional<String> resultado = fisioClinic.getCitas().stream()
            .filter(cita -> {
                if (cita.getUsuario() == null) {
                    System.out.println("Cita con usuario nulo.");
                    return false;
                }
                System.out.println("Comparando con identificación: " + cita.getUsuario().getIdentificacion());
                return cita.getUsuario().getIdentificacion().equals(identificacionUsuario);
            })
            .findFirst()
            .map(cita -> {
                Usuario usuario = cita.getUsuario();
                Doctor doctor = cita.getDoctor();
                return "Nombre: " + usuario.getNombre() + "\n" +
                       "Apellido: " + usuario.getApellido() + "\n" +
                       "Identificación: " + usuario.getIdentificacion() + "\n" +
                       "Número de Celular: " + usuario.getNumeroCelular() + "\n" +
                       "Email: " + usuario.getEmail() + "\n" +
                       "Fecha de Nacimiento: " + usuario.getFechaNacimiento() + "\n" +
                       "Tipo de Terapia: " + cita.getTipoTerapia() + "\n" +
                       "Fecha de la Cita: " + cita.getFechaCita() + "\n" +
                       "Hora de la Cita: " + cita.getHoraCita() + "\n" +
                       "Doctor: " + doctor.getNombre() + " " + doctor.getApellido();
            });
    
        if (resultado.isPresent()) {
            System.out.println("Cita encontrada: " + resultado.get());
            mostrarDetallesCita(resultado.get());
        } else {
            System.out.println("No se encontró ninguna cita para la identificación proporcionada.");
            mostrarDetallesCita("No se encontró ninguna cita para la identificación proporcionada.");
        }
    }
    
}
