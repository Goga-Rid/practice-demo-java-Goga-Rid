module com.example.demoexam {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires static lombok;
    requires java.sql;

    opens com.example.demoexam to javafx.fxml;
    exports com.example.demoexam;
}