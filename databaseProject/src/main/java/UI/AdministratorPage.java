package UI;

import Entity.DetailedCommodity;
import Entity.User;
import InterfaceImplementation.CommodityInterface;
import InterfaceImplementation.ShopInterface;
import InterfaceImplementation.UserInterface;
import InterfaceImplementation.CollectionInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.DatePicker;

import java.util.ArrayList;
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
        Utils.addButton(gridPane, "修改用户信息", () -> showUpdateUserPopup(stage));
        Utils.addButton(gridPane, "修改商家信息", () -> showUpdateShopPopup(stage));
        Utils.addButton(gridPane, "修改商品信息", () -> showUpdateCommodityPopup(stage));
        Utils.addButton(gridPane, "查询最受欢迎的商品", () -> showMostPopularCommodity(stage));
        Utils.addButton(gridPane, "查看所有用户信息", () -> showAllUsersPopup(stage));
        Utils.addButton(gridPane, "新增用户", () -> showInsertUserPopup(stage));
        Utils.addButton(gridPane, "删除用户", () -> showDeleteUserPopup(stage));
        Utils.addButton(gridPane, "删除商店", () -> showDeleteShopPopup(stage));
        Utils.addButton(gridPane, "新增商店", () -> showInsertShopPopup(stage));

        // 设置场景和舞台
        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);
        stage.setTitle("管理员界面");
        stage.show();
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
            Utils.alertUpdateResult(popupStage, result);
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
                Utils.alertUpdateResult(popupStage, result);
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
                Utils.alertUpdateResult(stage, result);
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
                Utils.alertQueryFailure(stage);
                return;
            }
            DetailedCommodity commodity = CommodityInterface.getDetailedCommodityInfo(mostPopularCommodityId);
            if(commodity == null){
                Utils.alertQueryFailure(stage);
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
            contentText.append("商家: ").append(commodity.getShop()).append("\n");
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

    public static void showInsertUserPopup(Stage primaryStage) {
        // 创建新增用户窗口的布局
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        // 添加标签和文本框
        Label nameLabel = new Label("姓名:");
        TextField nameTextField = new TextField();

        Label ageLabel = new Label("年龄:");
        TextField ageTextField = new TextField();

        Label phoneNumberLabel = new Label("电话号码:");
        TextField phoneNumberTextField = new TextField();

        Label roleLabel = new Label("身份:");
        TextField roleTextField = new TextField();

        Label passwordLabel = new Label("密码:");
        TextField passwordTextField = new TextField();

        Label genderLabel = new Label("性别:");
        TextField genderTextField = new TextField();

        // 添加所有控件到布局中
        root.add(nameLabel, 0, 0);
        root.add(nameTextField, 1, 0);
        root.add(ageLabel, 0, 1);
        root.add(ageTextField, 1, 1);
        root.add(phoneNumberLabel, 0, 2);
        root.add(phoneNumberTextField, 1, 2);
        root.add(roleLabel, 0, 3);
        root.add(roleTextField, 1, 3);
        root.add(passwordLabel, 0, 4);
        root.add(passwordTextField, 1, 4);
        root.add(genderLabel, 0, 5);
        root.add(genderTextField, 1, 5);

        // 创建确认按钮
        Button confirmButton = new Button("确认新增");
        confirmButton.setOnAction(event -> {
            // 获取用户输入的信息
            String name = nameTextField.getText();
            String age = ageTextField.getText();
            String phoneNumber = phoneNumberTextField.getText();
            Utils.alertIsInt(age);
            Integer ageInt = Integer.parseInt(age);
            Utils.alertIsInt(phoneNumber);
            String role = roleTextField.getText();
            String password = passwordTextField.getText();
            String gender = genderTextField.getText();
            Integer roleInt = 0;
            if(role.equals("商家")){
                roleInt = 1;
            } else if(role.equals("管理员")){
                roleInt = 2;
            }

            // 调用新增用户的函数
            Integer userId = UserInterface.insertNewUser(name, ageInt, phoneNumber, roleInt, password, gender);

            // 显示用户新增结果的弹窗
            Utils.alertInsertUserResult(userId);

            // 关闭新增用户窗口
            primaryStage.close();
        });

        // 将确认按钮添加到布局中
        root.add(confirmButton, 1, 6);

        // 创建新增用户窗口的场景
        Scene scene = new Scene(root, 400, 300);

        // 创建新增用户窗口的舞台
        Stage insertUserStage = new Stage();
        insertUserStage.setTitle("新增用户");
        insertUserStage.setScene(scene);

        // 设置新增用户窗口的 Modality 和 Owner
        insertUserStage.initModality(Modality.APPLICATION_MODAL);
        insertUserStage.initOwner(primaryStage);

        // 显示新增用户窗口
        insertUserStage.showAndWait();
    }

    public static void showAllUsersPopup(Stage primaryStage) {
        // 获取所有非管理员用户信息
        ArrayList<User> users = UserInterface.getAllUserNotAdministrator();

        // 创建表格形式的布局
        TableView<User> tableView = new TableView<>();

        // 创建表格的列
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameColumn = new TableColumn<>("姓名");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, Integer> ageColumn = new TableColumn<>("年龄");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<User, String> phoneNumberColumn = new TableColumn<>("电话号码");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<User, Integer> roleColumn = new TableColumn<>("角色");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<User, String> genderColumn = new TableColumn<>("性别");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("密码");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        // 将列添加到表格
        tableView.getColumns().addAll(idColumn, nameColumn, ageColumn, phoneNumberColumn, roleColumn, genderColumn, passwordColumn);

        // 将用户数据添加到表格
        ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
        tableView.setItems(observableUsers);

        // 创建弹出窗口
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(primaryStage);

        // 创建布局
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        // 添加表格到布局
        vBox.getChildren().add(tableView);

        // 创建确认按钮
        Button confirmButton = new Button("确定");
        confirmButton.setOnAction(event -> popupStage.close());

        // 添加确认按钮到布局
        vBox.getChildren().add(confirmButton);

        // 设置布局到场景
        Scene scene = new Scene(vBox);
        popupStage.setScene(scene);

        // 设置弹出窗口标题
        popupStage.setTitle("所有用户信息");

        // 显示弹出窗口
        popupStage.showAndWait();
    }

    public static void showDeleteUserPopup(Stage primaryStage) {
        // 创建弹出窗口
        Stage deletePopupStage = new Stage();
        deletePopupStage.initModality(Modality.APPLICATION_MODAL);
        deletePopupStage.initOwner(primaryStage);
        deletePopupStage.setTitle("删除用户");

        // 创建布局
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        // 添加标签和文本框
        Label idLabel = new Label("需要删除的用户ID:");
        TextField idTextField = new TextField();

        // 创建确认按钮
        Button confirmButton = new Button("确认删除");
        confirmButton.setOnAction(event -> {
            // 获取用户输入的ID
            try {
                Integer userId = Integer.parseInt(idTextField.getText());

                // 显示确认弹窗
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(primaryStage);
                alert.setTitle("确认删除");
                alert.setHeaderText("确认删除用户？");
                alert.setContentText("您确定要删除用户ID为 " + userId + " 的用户吗？");

                // 显示确认弹窗并等待用户响应
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        // 用户点击确认，执行删除用户的操作
                        Integer result = UserInterface.deleteUser(userId);

                        // 显示删除结果
                        Utils.alertDeleteResult(primaryStage, result);
                    }
                });
            } catch (NumberFormatException e) {
                // 处理非法输入，弹窗提示用户输入正确的ID
                Utils.alertIsInt(idTextField.getText());
            }
        });

        // 将标签、文本框和按钮添加到布局
        vBox.getChildren().addAll(idLabel, idTextField, confirmButton);

        // 创建场景和设置弹窗标题
        Scene deletePopupScene = new Scene(vBox);
        deletePopupStage.setScene(deletePopupScene);
        deletePopupStage.setTitle("删除用户");

        // 显示弹窗
        deletePopupStage.showAndWait();
    }

    public static void showDeleteShopPopup(Stage primaryStage) {
        // 创建弹出窗口
        Stage deletePopupStage = new Stage();
        deletePopupStage.initModality(Modality.APPLICATION_MODAL);
        deletePopupStage.initOwner(primaryStage);
        deletePopupStage.setTitle("删除商店");

        // 创建布局
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        // 添加标签和文本框
        Label idLabel = new Label("商店ID:");
        TextField idTextField = new TextField();

        // 创建确认按钮
        Button confirmButton = new Button("确认删除");
        confirmButton.setOnAction(event -> {
            // 获取用户输入的商店ID
            try {
                Integer shopId = Integer.parseInt(idTextField.getText());

                // 创建确认弹窗
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(primaryStage);
                alert.setTitle("确认删除");
                alert.setHeaderText("确认删除商店？");
                alert.setContentText("您确定要删除商店ID为 " + shopId + " 的商店吗？");

                // 显示确认弹窗并等待用户响应
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        // 用户点击确认，执行删除商店的操作
                        Integer result = ShopInterface.deleteShopById(shopId);

                        // 显示删除结果
                        Utils.alertDeleteResult(primaryStage, result);
                    }
                });
            } catch (NumberFormatException e) {
                // 处理非法输入，弹窗提示用户输入正确的商店ID
                Utils.alertIsInt(idTextField.getText());
            }
        });

        // 将标签、文本框和按钮添加到布局
        vBox.getChildren().addAll(idLabel, idTextField, confirmButton);

        // 创建场景和设置弹窗标题
        Scene deletePopupScene = new Scene(vBox);
        deletePopupStage.setScene(deletePopupScene);
        deletePopupStage.setTitle("删除商店");

        // 显示弹窗
        deletePopupStage.showAndWait();
    }
    public static void showInsertShopPopup(Stage primaryStage) {
        // 创建弹出窗口
        Stage insertPopupStage = new Stage();
        insertPopupStage.initModality(Modality.APPLICATION_MODAL);
        insertPopupStage.initOwner(primaryStage);
        insertPopupStage.setTitle("新增商店");

        // 创建布局
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        // 添加标签和文本框
        Label nameLabel = new Label("商店名称:");
        TextField nameTextField = new TextField();

        Label addressLabel = new Label("商店地址:");
        TextField addressTextField = new TextField();

        Label ownerIdLabel = new Label("拥有者ID:");
        TextField ownerIdTextField = new TextField();

        // 创建确认按钮
        Button confirmButton = new Button("确认新增");
        confirmButton.setOnAction(event -> {
            // 获取用户输入的商店信息
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            try {
                Integer ownerId = Integer.parseInt(ownerIdTextField.getText());

                // 调用新增商店的操作
                Integer result = ShopInterface.insertNewShop(name, address, ownerId);

                // 显示新增结果
                Utils.alertInsertShopResult(result);

                // 关闭新增商店窗口
                insertPopupStage.close();
            } catch (NumberFormatException e) {
                // 处理非法输入，弹窗提示用户输入正确的拥有者ID
                Utils.alertIsInt(ownerIdTextField.getText());
            }
        });

        // 将标签、文本框和按钮添加到布局
        vBox.getChildren().addAll(nameLabel, nameTextField, addressLabel, addressTextField, ownerIdLabel, ownerIdTextField, confirmButton);

        // 创建场景和设置弹窗标题
        Scene insertPopupScene = new Scene(vBox);
        insertPopupStage.setScene(insertPopupScene);
        insertPopupStage.setTitle("新增商店");

        // 显示弹窗
        insertPopupStage.showAndWait();
    }
}
