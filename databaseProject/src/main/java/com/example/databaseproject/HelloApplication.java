package com.example.databaseproject;

import UI.LoginPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginPage.LoginPage(primaryStage);
        // 创建标签
    }
    public static void main(String[] args) {
        launch();
    }
}