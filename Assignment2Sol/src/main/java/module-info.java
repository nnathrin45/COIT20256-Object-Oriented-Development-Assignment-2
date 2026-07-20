module cqu.assignment2sol {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens cqu.assignment2sol to javafx.fxml;
    exports cqu.assignment2sol;
}
