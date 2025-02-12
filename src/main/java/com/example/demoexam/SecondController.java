package com.example.demoexam;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.SQLException;


public class SecondController {
    @FXML
    private TextField fioField;

    @FXML
    public DatePicker startDatePick;

    @FXML
    private TextField positionField;

    @FXML
    public TextField orgField;

    @FXML
    private TextField salaryField;


    private HelloController helloController;
    private Stage stage;
    private FMJob selectedPartner;


    private FMJobDAO partnerDAO = new FMJobDAO();


    public void loadMemberData(int memberId, Stage stage) {
        try {
            System.out.println("Загружаем данные для члена семьи с ID: " + memberId);
            selectedPartner = partnerDAO.getMember(memberId);
            this.stage = stage;
            if (selectedPartner != null) {
                fioField.setText(selectedPartner.getFio());
                startDatePick.setValue(selectedPartner.getStart_date().toLocalDate());
                positionField.setText(selectedPartner.getPosition());
                orgField.setText(selectedPartner.getOrg_name());
                salaryField.setText(String.valueOf(selectedPartner.getSalary()));
            } else {
                System.out.println("Член семьи с ID " + memberId + " не найден.");
            }
        } catch (SQLException e) {
            showAlert("Ошибка", "Не удалось загрузить данные члена семьи: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void setFirstController(HelloController helloController) {
        this.helloController = helloController;
    }

    @FXML
    private void saveChanges() {
        try {

            String fio = fioField.getText();
            Date start_date = Date.valueOf(startDatePick.getValue());
            String position = positionField.getText();
            String org_name = orgField.getText();
            int salary = Integer.parseInt(salaryField.getText());

            partnerDAO.update(
                    selectedPartner.getMemberJob_id(),
                    selectedPartner.getMemberId(),
                    fio,
                    position,
                    org_name,
                    salary,
                    start_date
            );

            stage.close();
            showAlert("Успех", "Данные партнера успешно обновлены!", Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            showAlert("Ошибка", "Не удалось обновить Данные партнера: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }




    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
