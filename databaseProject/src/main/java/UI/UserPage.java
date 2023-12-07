package UI;

import Entity.Commodity;
import Entity.DetailedCommodity;
import Entity.User;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

import static InterfaceImplementation.CommodityInterface.getDetailedCommodityInfo;
import static InterfaceImplementation.CommodityInterface.searchCommodity;

public class UserPage {
    public static void showUserPage(Stage stage, User user){
        Button btnUserInfo = new Button("用户信息查询");
        Button btnSearchProduct = new Button("商品搜索");
        Button btnPriceChange = new Button("商品价格变化查询");
        Button btnFavoriteProducts = new Button("收藏商品");
        Button btnViewMessages = new Button("查看消息列表");

        // 为按钮添加事件处理（示例）
        btnUserInfo.setOnAction(event -> showUserInfo(user));
        btnSearchProduct.setOnAction(event -> showSearchProductPage(stage));
        // 其他按钮类似...

        // 创建布局
        VBox layout = new VBox(10); // 10是按钮之间的间距
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(btnUserInfo, btnSearchProduct, btnPriceChange, btnFavoriteProducts, btnViewMessages);

        // 设置舞台和场景
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.setTitle("用户页面");
        stage.show();
    }

    private static void showUserInfo(User user) {
        String[] UserType = {"用户","商家","管理员"};
        Stage infoStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        layout.getChildren().addAll(
                new Label("ID: " + user.getId()),
                new Label("Name: " + user.getName()),
                new Label("Age: " + user.getAge()),
                new Label("Phone Number: " + user.getPhoneNumber()),
                new Label("Role: " + UserType[user.getRole()])
        );

        Scene scene = new Scene(layout,300,200);
        infoStage.setScene(scene);
        infoStage.setTitle("用户信息");
        infoStage.show();
    }
    
    private static void showSearchProductPage(Stage stage){
        Stage searchStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

            // 输入框
        TextField searchField = new TextField("请输入商品名称");

            // 确认按钮
        Button searchButton = new Button("搜索");
        searchButton.setOnAction(event -> {
            ArrayList<Commodity> commodities = null; // 假设currentUser是当前用户对象
            try {
                commodities = searchCommodity(searchField.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            showCommodityResults(commodities, searchStage);
        });

        layout.getChildren().addAll(searchField, searchButton);

        Scene scene = new Scene(layout, 400, 300);
        searchStage.setScene(scene);
        searchStage.setTitle("商品搜索");
        searchStage.show();
        }

    private static void showCommodityResults(ArrayList<Commodity> commodities, Stage stage) {
        Stage resultStage = new Stage();
        TableView<Commodity> table = new TableView<>();

        // 商品名称列
        TableColumn<Commodity, String> nameColumn = new TableColumn<>("商品名称");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // 商家列
        TableColumn<Commodity, String> shopColumn = new TableColumn<>("商家");
        shopColumn.setCellValueFactory(new PropertyValueFactory<>("shop"));

        // 平台列
        TableColumn<Commodity, String> platformColumn = new TableColumn<>("平台");
        platformColumn.setCellValueFactory(new PropertyValueFactory<>("platform"));

        // 价格列
        TableColumn<Commodity, Double> priceColumn = new TableColumn<>("价格");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(nameColumn, shopColumn, platformColumn, priceColumn);
        table.setItems(FXCollections.observableArrayList(commodities));

        // 行点击事件
        table.setRowFactory(tv -> {
            TableRow<Commodity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Commodity clickedRow = row.getItem();
                    try {
                        showDetailedCommodityInfo(getDetailedCommodityInfo(clickedRow.getId()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });

        Scene scene = new Scene(new VBox(table), 500, 400);
        resultStage.setScene(scene);
        resultStage.setTitle("搜索结果");
        resultStage.show();
    }
    private static void showDetailedCommodityInfo(DetailedCommodity commodity) {
        Stage infoStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        layout.getChildren().addAll(
                new Label("商品名称: " + commodity.getName()),
                new Label("商家: " + commodity.getShop()),
                new Label("平台: " + commodity.getPlatform()),
                new Label("价格: " + commodity.getPrice()),
                new Label("产地: " + commodity.getOrigin()),
                new Label("类别: " + commodity.getCategory()),
                new Label("描述: " + commodity.getDescription()),
                new Label("生产日期: " + commodity.getProductionDate()),
                new Label("商家地址: " + commodity.getShopAddress())
        );

        Scene scene = new Scene(layout, 300, 350);
        infoStage.setScene(scene);
        infoStage.setTitle("商品信息");
        infoStage.show();
    }
}
