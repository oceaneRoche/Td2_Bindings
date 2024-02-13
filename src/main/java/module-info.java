module fr.btsciel.td_binding {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.btsciel.td_binding to javafx.fxml;
    exports fr.btsciel.td_binding;
}