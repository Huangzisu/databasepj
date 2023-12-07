package com.example.databaseproject;

import Entity.User;
import UI.AdministratorPage;
import UI.LoginPage;
import UI.ShopOwnerPage;
import UI.UserPage;

import javafx.application.Application;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
//        LoginPage.LoginPage(primaryStage);
//        AdministratorPage.showAdministratorPage(primaryStage);
        ShopOwnerPage shopOwnerPage = new ShopOwnerPage();
        shopOwnerPage.showShopOwnerPage(primaryStage);
//        UserPage.showUserPage(primaryStage,new User());
        // 创建标签
    }
    public static void main(String[] args) {
        launch();
    }
}