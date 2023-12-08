package UI;

import Entity.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static InterfaceImplementation.CollectionInterface.addCollectionCommodity;
import static InterfaceImplementation.CommodityInterface.getDetailedCommodityInfo;
import static InterfaceImplementation.CommodityInterface.searchCommodity;
import static InterfaceImplementation.MessageInterface.getMessageByUserId;
import static InterfaceImplementation.PriceInterface.getHistoryPrice;

public class UserPage {
    public static void showUserPage(Stage stage, User user){
        Button btnUserInfo = new Button("用户信息查询");
        Button btnSearchProduct = new Button("商品搜索");
        Button btnViewMessages = new Button("查看消息列表");

        // 为按钮添加事件处理（示例）
        btnUserInfo.setOnAction(event -> showUserInfo(user));
        btnSearchProduct.setOnAction(event -> showSearchProductPage(user));
        btnViewMessages.setOnAction(event->showMessageList(user.getId()));
        // 其他按钮类似...

        // 创建布局
        VBox layout = new VBox(20); // 10是按钮之间的间距
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(btnUserInfo, btnSearchProduct, btnViewMessages);

        // 设置舞台和场景
        Scene scene = new Scene(layout, 400, 300);
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
    
    private static void showSearchProductPage(User user){
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
            showCommodityResults(commodities,user);
        });

        layout.getChildren().addAll(searchField, searchButton);

        Scene scene = new Scene(layout, 400, 300);
        searchStage.setScene(scene);
        searchStage.setTitle("商品搜索");
        searchStage.show();
        }

    private static void showCommodityResults(ArrayList<Commodity> commodities, User user) {
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
                        showDetailedCommodityInfo(getDetailedCommodityInfo(clickedRow.getId()),user);
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
    private static void showDetailedCommodityInfo(DetailedCommodity commodity,User user) {
        Stage infoStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));
        Button historyPriceButton = new Button("查看历史价格");
        Button collectButton = new Button("收藏该商品");
        historyPriceButton.setOnAction(event -> showHistoryPriceOptions(commodity.getId()));
        collectButton.setOnAction(event -> showCollectCommodityDialog(commodity.getId(), user));
        layout.getChildren().addAll(
                new Label("商品名称: " + commodity.getName()),
                new Label("商家: " + commodity.getShop()),
                new Label("平台: " + commodity.getPlatform()),
                new Label("价格: " + commodity.getPrice()),
                new Label("产地: " + commodity.getOrigin()),
                new Label("类别: " + commodity.getCategory()),
                new Label("描述: " + commodity.getDescription()),
                new Label("生产日期: " + commodity.getProductionDate()),
                new Label("商家地址: " + commodity.getShopAddress()),
                historyPriceButton,
                collectButton
        );

        Scene scene = new Scene(layout, 300, 350);
        infoStage.setScene(scene);
        infoStage.setTitle("商品详细信息");
        infoStage.show();
    }
    private static void showHistoryPriceOptions(Integer cId) {
        Stage optionsStage = new Stage();
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        ComboBox<String> timeOptions = new ComboBox<>();
        timeOptions.getItems().addAll("所有时间", "近一周", "近一个月", "近一年");
        timeOptions.getSelectionModel().selectFirst();

        Button searchButton = new Button("查询");
        searchButton.setOnAction(event -> {
            try {
                showHistoryPriceChart(cId, timeOptions.getSelectionModel().getSelectedIndex());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        layout.getChildren().addAll(new Label("选择时间范围"), timeOptions, searchButton);

        Scene scene = new Scene(layout, 300, 200);
        optionsStage.setScene(scene);
        optionsStage.setTitle("选择时间范围");
        optionsStage.show();
    }
    private static void showHistoryPriceChart(Integer cId, int option) throws SQLException, ClassNotFoundException {
        Stage chartStage = new Stage();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("日期");
        yAxis.setLabel("价格");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("历史价格");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("价格变化");

        ArrayList<Price> prices = getHistoryPrice(cId, option);
        Price minPrice = Collections.min(prices, Comparator.comparingDouble(Price::getPrice));

        for (Price price : prices) {

            XYChart.Data<String, Number> data = new XYChart.Data<>(formatDate(price.getTime()), price.getPrice());
            if (price.getPrice() == minPrice.getPrice()) {
                data.setNode(new Circle(5)); // 突出显示最低价格
            }
            series.getData().add(data);
        }

        lineChart.getData().add(series);

        Scene scene = new Scene(lineChart, 800, 600);
        chartStage.setScene(scene);
        chartStage.setTitle("历史价格图表");
        chartStage.show();
    }
    private static String formatDate(String dateTimeStr) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return dateTime.toLocalDate().toString();
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return dateTimeStr;
        }
    }

    private static void showMessageList(int userId) {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        ListView<String> listView = new ListView<>();
        ArrayList<Message> messages = getMessageByUserId(userId);

        for (Message message : messages) {
            String displayText = "时间: " + message.getTime().toString() + "\n内容: " + message.getContent();
            listView.getItems().add(displayText);
        }

        layout.getChildren().add(listView);

        Scene scene = new Scene(layout, 400, 600);
        stage.setScene(scene);
        stage.setTitle("消息列表");
        stage.show();
    }
    private static void showCollectCommodityDialog(int cId, User user) {
        Stage dialogStage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField floorPriceField = new TextField();
        floorPriceField.setPromptText("输入底价");

        Button submitButton = new Button("确认");
        submitButton.setOnAction(event -> {
            try {
                double floorPrice = Double.parseDouble(floorPriceField.getText());
                if (floorPrice > 0) {
                    int result = addCollectionCommodity(cId, user, floorPrice);
                    if (result == 1) {
                        showAlert("收藏成功", Alert.AlertType.INFORMATION);
                    } else {
                        showAlert("收藏失败", Alert.AlertType.ERROR);
                    }
                } else {
                    showAlert("底价必须大于0", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException e) {
                showAlert("请输入有效的底价", Alert.AlertType.ERROR);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        layout.getChildren().addAll(new Label("请输入底价："), floorPriceField, submitButton);

        Scene scene = new Scene(layout, 300, 200);
        dialogStage.setScene(scene);
        dialogStage.setTitle("收藏商品");
        dialogStage.show();
    }
    private static void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, message);
        alert.showAndWait();
    }
}
