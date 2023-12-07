package com.example.databaseproject;

import UI.AdministratorPage;
import UI.LoginPage;
import UI.ShopOwnerPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
//        LoginPage.LoginPage(primaryStage);
//        AdministratorPage.showAdministratorPage(primaryStage);
        ShopOwnerPage shopOwnerPage = new ShopOwnerPage();
        shopOwnerPage.showShopOwnerPage(primaryStage);
        // 创建标签
    }
    public static void main(String[] args) {
        launch();
    }
}