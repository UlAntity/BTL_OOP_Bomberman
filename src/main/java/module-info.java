module com.example.bomberman {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.datatransfer;
    requires java.desktop;

    opens uet.oop.bomberman to javafx.fxml;
    exports uet.oop.bomberman;
}