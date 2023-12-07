package UI;

import Entity.Platform;
import Entity.Shop;
import InterfaceImplementation.CommodityInterface;
import InterfaceImplementation.PlatformInterface;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;

/**
 * @Description：
 * @Author Huangzisu
 * @date 2023-12-07
 **/
public class ShopManagementPage {
    private Shop shop;
    public ShopManagementPage(Shop shop){
        this.shop = shop;
    }
    public void showShopManagementPage(Shop shop, Stage primaryStage) {
        // 创建商店管理页面的布局
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        // 创建商店管理页面的场景
        Scene scene = new Scene(root, 300, 200);

        // 创建商店管理页面的舞台
        Stage shopManagementStage = new Stage();
        shopManagementStage.setTitle("商店管理页面 - " + shop.getName());
        shopManagementStage.setScene(scene);

        // 增加按钮
        Utils.addButton(root, "发布新商品", () -> showReleaseNewCommodityPopup(shopManagementStage));

        // 设置商店管理页面的 Modality 和 Owner
        shopManagementStage.initModality(Modality.APPLICATION_MODAL);
        shopManagementStage.initOwner(primaryStage);

        shopManagementStage.show();
    }

    public void showReleaseNewCommodityPopup(Stage primaryStage) {
        // 创建发布商品页面的布局
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        // 添加标签和文本框/选择框
        Label nameLabel = new Label("商品名称:");
        TextField nameTextField = new TextField();

        Label categoryLabel = new Label("商品类别:");
        TextField categoryTextField = new TextField();

        Label originLabel = new Label("商品产地:");
        TextField originTextField = new TextField();

        Label descriptionLabel = new Label("商品描述:");
        TextField descriptionTextField = new TextField();

        Label produceDateLabel = new Label("生产日期:");
        DatePicker produceDatePicker = new DatePicker();

        Label priceLabel = new Label("商品价格:");
        TextField priceTextField = new TextField();

        Label platformLabel = new Label("选择平台:");
        ChoiceBox<Platform> platformChoiceBox = new ChoiceBox<>();

        // 获取所有平台
        ArrayList<Platform> platforms = PlatformInterface.getAllPlatforms();

        // 添加平台选项到ChoiceBox
        platformChoiceBox.getItems().addAll(platforms);
        platformChoiceBox.setConverter(new StringConverter<Platform>() {
            @Override
            public String toString(Platform platform) {
                return platform.getName();
            }

            @Override
            public Platform fromString(String string) {
                return null;
            }
        });
        platformChoiceBox.getSelectionModel().selectFirst(); // 默认选择第一个平台

        // 添加所有控件到布局中
        root.add(nameLabel, 0, 0);
        root.add(nameTextField, 1, 0);
        root.add(categoryLabel, 0, 1);
        root.add(categoryTextField, 1, 1);
        root.add(originLabel, 0, 2);
        root.add(originTextField, 1, 2);
        root.add(descriptionLabel, 0, 3);
        root.add(descriptionTextField, 1, 3);
        root.add(produceDateLabel, 0, 4);
        root.add(produceDatePicker, 1, 4);
        root.add(priceLabel, 0, 5);
        root.add(priceTextField, 1, 5);
        root.add(platformLabel, 0, 6);
        root.add(platformChoiceBox, 1, 6);

        // 创建发布商品页面的场景
        Scene scene = new Scene(root, 400, 300);

        // 创建发布商品页面的舞台
        Stage releaseCommodityStage = new Stage();
        releaseCommodityStage.setTitle("发布新商品");
        releaseCommodityStage.setScene(scene);

        // 设置发布商品页面的 Modality 和 Owner
        releaseCommodityStage.initModality(Modality.APPLICATION_MODAL);
        releaseCommodityStage.initOwner(primaryStage);

        // 添加确认按钮
        Button confirmButton = new Button("确认发布");
        confirmButton.setOnAction(event -> {
            // 获取用户输入的信息
            String name = nameTextField.getText();
            String category = categoryTextField.getText();
            String origin = originTextField.getText();
            String description = descriptionTextField.getText();
            String produceDate = produceDatePicker.getValue().toString();
            Platform selectedPlatform = platformChoiceBox.getValue();
            Integer platformId = selectedPlatform.getId();
            String priceText = priceTextField.getText();
            if (!priceText.matches("\\d+") || Integer.parseInt(priceText) <= 0) {
                // 如果不是正整数或小于等于0，弹出警告提示
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("警告");
                alert.setHeaderText("价格输入错误");
                alert.setContentText("请输入一个大于0的正整数作为商品价格！");
                alert.showAndWait();
                return; // 中断确认发布操作
            }
            Integer price = Integer.parseInt(priceText);

            // 调用发布商品的函数
            Integer result = CommodityInterface.releaseNewCommodity(name, category, description, produceDate, origin, shop.getId(), price, platformId);

            Utils.alertReleaseResult(releaseCommodityStage, result);

            // 关闭发布商品页面
            releaseCommodityStage.close();
        });

        // 将确认按钮添加到布局中
        root.add(confirmButton, 1, 7);

        // 显示发布商品页面
        releaseCommodityStage.showAndWait();
    }
}
