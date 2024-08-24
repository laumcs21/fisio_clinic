module www.uniquindio.edu.poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens www.uniquindio.edu.poo to javafx.fxml;
    exports www.uniquindio.edu.poo;
    exports www.uniquindio.edu.poo.Interfaz; 
    exports www.uniquindio.edu.poo.Crud;
    exports www.uniquindio.edu.poo.Abstracciones;
    exports www.uniquindio.edu.poo.Enums;

}

