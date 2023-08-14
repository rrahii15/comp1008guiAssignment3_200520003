module com.example.assign3java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assign3java to javafx.fxml;
    exports com.example.assign3java;
}