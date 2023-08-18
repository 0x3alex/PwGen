module de.alex.pwgen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens de.alex.pwgen to javafx.fxml;
    exports de.alex.pwgen;
}