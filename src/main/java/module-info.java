module www.uniquindio.edu.poo {
    requires javafx.controls;
    requires javafx.fxml;

    opens www.uniquindio.edu.poo to javafx.fxml;
    exports www.uniquindio.edu.poo;
}
