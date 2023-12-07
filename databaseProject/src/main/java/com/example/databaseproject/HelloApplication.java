package com.example.databaseproject;

import Entity.User;
import UI.LoginPage;
import UI.UserPage;
import javafx.application.Application;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
//        LoginPage.LoginPage(primaryStage);
        UserPage.showUserPage(primaryStage,new User());
        // 创建标签
    }
    public static void main(String[] args) {
        launch();
    }
}