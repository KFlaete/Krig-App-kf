module krig {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens krig to javafx.fxml;
    exports krig;
}
