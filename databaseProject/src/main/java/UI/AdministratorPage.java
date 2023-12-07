package UI;

import Entity.DetailedCommodity;
import InterfaceImplementation.CommodityInterface;
import InterfaceImplementation.ShopInterface;
import InterfaceImplementation.UserInterface;
import InterfaceImplementation.CollectionInterface;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.DatePicker;

import java.util.Collection;


/**
 * @Description：
 * @Author Huangzisu
 * @date 2023-12-06
 **/
public class AdministratorPage {
    public static void showAdministratorPage(Stage stage) {
        // 创建布局
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // 添加组件
        addButton(gridPane, "修改用户信息", () -> showUpdateUserPopup(stage));
        addButton(gridPane, "修改商家信息", () -> showUpdateShopPopup(stage));
        addButton(gridPane, "修改商品信息", () -> showUpdateCommodityPopup(stage));
        addButton(gridPane, "查询最受欢迎的商品", () -> showMostPopularCommodity(stage));

        // 设置场景和舞台
        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);
        stage.setTitle("管理员界面");
        stage.show();
    }

    private static void addButton(GridPane gridPane, String buttonText, Runnable action) {
        Button button = new Button(buttonText);
        button.setOnAction(event -> action.run());
        gridPane.add(button, 1, gridPane.getRowCount());
    }

    private static void showUpdateUserPopup(Stage stage) {
        // 创建弹出窗口
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(stage);

        // 创建文本框和标签
        Label labelUserId = new Label("用户ID:");
        Label labelUserName = new Label("用户姓名:");
        Label labelUserAge = new Label("用户年龄:");
        Label labelUserGender = new Label("用户性别:");
        Label labelUserPhone = new Label("用户电话:");

        TextField textFieldUserId = new TextField();
        TextField textFieldUserName = new TextField();
        TextField textFieldUserAge = new TextField();
        TextField textFieldUserGender = new TextField();
        TextField textFieldUserPhone = new TextField();

        // 创建确认按钮
        Button confirmButton = new Button("确认修改");

        // 添加确认按钮点击事件
        confirmButton.setOnAction(e -> {
            // 获取用户输入的信息
            String userId = textFieldUserId.getText();
            String userName = textFieldUserName.getText();
            String userAge = textFieldUserAge.getText();
            String userGender = textFieldUserGender.getText();
            String userPhone = textFieldUserPhone.getText();

            // 在此处执行修改用户信息的逻辑，你可以调用相应的方法或写逻辑代码
            Integer result = UserInterface.updateUserInfo(Integer.parseInt(userId), userName, Integer.parseInt(userAge), userPhone, userGender);
            alertUpdateResult(popupStage, result);
            // 关闭弹出窗口
            popupStage.close();
        });

        // 使用 GridPane 布局
        GridPane popupGridPane = new GridPane();
        popupGridPane.setVgap(10);
        popupGridPane.setHgap(10);
        popupGridPane.setAlignment(Pos.CENTER);

        // 添加组件到弹出窗口布局
        popupGridPane.add(labelUserId, 0, 0);
        popupGridPane.add(textFieldUserId, 1, 0);
        popupGridPane.add(labelUserName, 0, 1);
        popupGridPane.add(textFieldUserName, 1, 1);
        popupGridPane.add(labelUserAge, 0, 2);
        popupGridPane.add(textFieldUserAge, 1, 2);
        popupGridPane.add(labelUserGender, 0, 3);
        popupGridPane.add(textFieldUserGender, 1, 3);
        popupGridPane.add(labelUserPhone, 0, 4);
        popupGridPane.add(textFieldUserPhone, 1, 4);
        popupGridPane.add(confirmButton, 1, 5);

        // 设置弹出窗口的场景
        Scene popupScene = new Scene(popupGridPane, 300, 250);
        popupStage.setScene(popupScene);
        popupStage.setTitle("管理员修改用户信息");
        popupStage.showAndWait();
    }

    private static void showUpdateShopPopup(Stage stage) {
        // 创建弹出窗口
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(stage);

        // 创建文本框和标签
        Label labelShopId = new Label("商家ID:");
        Label labelShopName = new Label("商家名称:");
        Label labelShopLocation = new Label("商家地址:");

        TextField textFieldShopId = new TextField();
        TextField textFieldShopName = new TextField();
        TextField textFieldShopLocation = new TextField();

        // 创建确认按钮
        Button confirmButton = new Button("确认修改");

        // 添加确认按钮点击事件
        confirmButton.setOnAction(e -> {
            try {
                // 获取商家输入的信息
                String shopId = textFieldShopId.getText();
                String shopName = textFieldShopName.getText();
                String shopLocation = textFieldShopLocation.getText();

                // 在此处执行修改商家信息的逻辑，你可以调用相应的方法或写逻辑代码
                int result = ShopInterface.updateShopInfo(Integer.parseInt(shopId), shopName, shopLocation);

                // 显示更新结果的消息窗口
                alertUpdateResult(popupStage, result);
                popupStage.close();
            } catch (Exception ex) {
                ex.printStackTrace(); // 这里可以根据实际情况处理异常
            }
        });

        // 使用 GridPane 布局
        GridPane popupGridPane = new GridPane();
        popupGridPane.setVgap(10);
        popupGridPane.setHgap(10);
        popupGridPane.setAlignment(Pos.CENTER);

        // 添加组件到弹出窗口布局
        popupGridPane.add(labelShopId, 0, 0);
        popupGridPane.add(textFieldShopId, 1, 0);
        popupGridPane.add(labelShopName, 0, 1);
        popupGridPane.add(textFieldShopName, 1, 1);
        popupGridPane.add(labelShopLocation, 0, 3);
        popupGridPane.add(textFieldShopLocation, 1, 3);
        popupGridPane.add(confirmButton, 1, 5);

        // 设置弹出窗口的场景
        Scene popupScene = new Scene(popupGridPane, 300, 250);
        popupStage.setScene(popupScene);
        popupStage.setTitle("管理员修改商家信息");
        popupStage.showAndWait();
    }

    private static void showUpdateCommodityPopup(Stage stage) {
        // 创建弹出窗口
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(stage);

        // 创建文本框和标签
        Label labelCommodityId = new Label("商品ID:");
        Label labelCommodityName = new Label("商品名称:");
        Label labelCommodityDescription = new Label("商品描述:");
        Label labelCommodityCategory = new Label("商品类别:");
        Label labelCommodityOrigin = new Label("商品产地:");
        Label labelCommodityProductionDate = new Label("商品生产日期:");
        Label labelShopName = new Label("商家名称:");
        Label labelPlatformName = new Label("平台名称:");

        TextField textFieldCommodityId = new TextField();
        TextField textFieldCommodityName = new TextField();
        TextField textFieldCommodityDescription = new TextField();
        TextField textFieldCommodityCategory = new TextField();
        TextField textFieldCommodityOrigin = new TextField();
        // 使用 DatePicker 选择日期
        DatePicker datePickerCommodityProductionDate = new DatePicker();
        TextField textFieldShopName = new TextField();
        TextField textFieldPlatformName = new TextField();

        // 创建确认按钮
        Button confirmButton = new Button("确认修改");

        // 添加确认按钮点击事件
        confirmButton.setOnAction(e -> {
            try {
                // 获取商品输入的信息
                String commodityId = textFieldCommodityId.getText();
                String commodityName = textFieldCommodityName.getText();
                String commodityDescription = textFieldCommodityDescription.getText();
                String commodityCategory = textFieldCommodityCategory.getText();
                String commodityOrigin = textFieldCommodityOrigin.getText();
                // 获取选择的日期
                String commodityProductionDate = datePickerCommodityProductionDate.getValue().toString();
                String shopName = textFieldShopName.getText();
                String platformName = textFieldPlatformName.getText();

                // 在此处执行修改商品信息的逻辑，你可以调用相应的方法或写逻辑代码
                int result = CommodityInterface.administratorUpdateCommodityInfo(
                        Integer.parseInt(commodityId),
                        commodityName,
                        commodityCategory,
                        commodityDescription,
                        commodityProductionDate,
                        commodityOrigin,
                        shopName,
                        platformName
                );

                // 显示更新结果的消息窗口
                alertUpdateResult(stage, result);
            } catch (Exception ex) {
                ex.printStackTrace(); // 这里可以根据实际情况处理异常
            }
        });

        // 使用 GridPane 布局
        GridPane popupGridPane = new GridPane();
        popupGridPane.setVgap(10);
        popupGridPane.setHgap(10);
        popupGridPane.setAlignment(Pos.CENTER);

        // 添加组件到弹出窗口布局
        popupGridPane.add(labelCommodityId, 0, 0);
        popupGridPane.add(textFieldCommodityId, 1, 0);
        popupGridPane.add(labelCommodityName, 0, 1);
        popupGridPane.add(textFieldCommodityName, 1, 1);
        popupGridPane.add(labelCommodityDescription, 0, 2);
        popupGridPane.add(textFieldCommodityDescription, 1, 2);
        popupGridPane.add(labelCommodityCategory, 0, 3);
        popupGridPane.add(textFieldCommodityCategory, 1, 3);
        popupGridPane.add(labelCommodityOrigin, 0, 4);
        popupGridPane.add(textFieldCommodityOrigin, 1, 4);
        popupGridPane.add(labelCommodityProductionDate, 0, 5);
        popupGridPane.add(datePickerCommodityProductionDate, 1, 5);
        popupGridPane.add(labelShopName, 0, 6);
        popupGridPane.add(textFieldShopName, 1, 6);
        popupGridPane.add(labelPlatformName, 0, 7);
        popupGridPane.add(textFieldPlatformName, 1, 7);
        popupGridPane.add(confirmButton, 1, 8);

        // 设置弹出窗口的场景
        Scene popupScene = new Scene(popupGridPane, 400, 350);
        popupStage.setScene(popupScene);
        popupStage.setTitle("管理员修改商品信息");
        popupStage.showAndWait();
    }

    public static void showMostPopularCommodity(Stage stage) {
        try {
            // 在此处执行查询最受欢迎商品的逻辑，返回商品信息的结果
            Integer mostPopularCommodityId = CollectionInterface.getMostPopularCommodityId();
            if(mostPopularCommodityId == -1){
                alertQueryFailure(stage);
                return;
            }
            DetailedCommodity commodity = CommodityInterface.getDetailedCommodityInfo(mostPopularCommodityId);
            if(commodity == null){
                alertQueryFailure(stage);
                return;
            }
            // 创建弹出窗口
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("最受欢迎的商品");
            alert.setHeaderText("最受欢迎的商品信息");
            StringBuilder contentText = new StringBuilder();
            contentText.append("ID: ").append(commodity.getId()).append("\n");
            contentText.append("名称: ").append(commodity.getName()).append("\n");
            contentText.append("价格: ").append(commodity.getPrice()).append("\n");
            contentText.append("商家: ").append(commodity.getShopName()).append("\n");
            contentText.append("平台: ").append(commodity.getPlatform()).append("\n");
            contentText.append("类别: ").append(commodity.getCategory()).append("\n");
            contentText.append("描述: ").append(commodity.getDescription()).append("\n");
            contentText.append("生产日期: ").append(commodity.getProductionDate()).append("\n");
            contentText.append("产地: ").append(commodity.getOrigin()).append("\n\n");
            alert.setContentText(contentText.toString());
            alert.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace(); // 这里可以根据实际情况处理异常
        }
    }

    private static void alertUpdateResult(Stage stage, int result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);

        if (result == 1) {
            alert.setTitle("成功");
            alert.setHeaderText("用户信息已成功更新");
            alert.setContentText("用户信息已经成功更新到数据库。");
        } else {
            alert.setTitle("失败");
            alert.setHeaderText("更新用户信息失败");
            alert.setContentText("更新用户信息时发生错误。");
        }
        alert.showAndWait();
    }

    private static void alertQueryFailure(Stage stage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("失败");
        alert.setContentText("查询失败");
        alert.showAndWait();
    }
}
