package com.example.demoexam;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

public class HelloController {
    @FXML
    private ImageView icon;

    private DBConnection dbConnection = new DBConnection();
    private FMJobDAO memberDAO = new FMJobDAO();

    @FXML
    protected void addNewMember() {

    }

    @FXML
    protected void deleteMember() {

    }

    @FXML
    public void initialize() {
        try {
            dbConnection.getConnection();
            System.out.println("Успешное соединение с БАЗОЙ ДАННЫХ!");

            Image image = new Image(getClass().getResourceAsStream("/com/example/demoexam/icon.png"));
            icon.setImage(image);

        } catch (SQLException e) {
            System.out.println("При запуске приложения возникли проблемы с соединением с БАЗОЙ ДАННЫХ!");
        }

    }
}