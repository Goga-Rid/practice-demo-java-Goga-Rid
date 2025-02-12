module com.example.demoexam {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.demoexam to javafx.fxml;
    exports com.example.demoexam;
}